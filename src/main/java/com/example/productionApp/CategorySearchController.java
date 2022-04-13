package com.example.productionApp;

import database.Database;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import production.model.Category;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CategorySearchController {
    @FXML
    public TextField enterCategoryName;

    @FXML
    public TableView<Category> categoriesTableView;

    @FXML
    public TableColumn<Category, String> nameTableColumn;

    @FXML
    public TableColumn<Category, String> descriptionTableColumn;


    List<Category> categories = new ArrayList<>();

    @FXML
    public void initialize() {

        try {
            categories = Database.getCategories();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        nameTableColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getName()));

        descriptionTableColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getDescription()));


        categoriesTableView.setItems(FXCollections.observableList(categories));
    }

    @FXML
    public void onSearchButtonClick() {
        String enteredName = enterCategoryName.getText();
        List<Category> filteredList = new ArrayList<>(categories);

        if (!enteredName.isEmpty()) {
            filteredList = filteredList.stream()
                    .filter(category -> category.getName().toLowerCase().contains(enteredName.toLowerCase()))
                    .collect(Collectors.toList());
        }

        categoriesTableView.setItems(FXCollections.observableList(filteredList));
    }
}
