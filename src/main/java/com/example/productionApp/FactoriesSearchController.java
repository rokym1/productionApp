package com.example.productionApp;

import database.Database;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import production.model.Factory;
import production.model.Item;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FactoriesSearchController {
    @FXML
    public TextField enterCity;

    @FXML
    public TableView<Factory> factoriesTableView;

    @FXML
    public TableColumn<Factory, String> nameTableColumn;

    @FXML
    public TableColumn<Factory, String> addressTableColumn;

    @FXML
    public TableColumn<Factory, String> numOfItemsTableColumn;

    List<Item> items = new ArrayList<>();
    List<Factory> factories = new ArrayList<>();

    @FXML
    public void initialize() {

        try {
            items = Database.getItems();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        try {
            factories = Database.getFactories();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        factories.forEach(factory -> {
            Set<Item> itemSet = new HashSet<>();
            List<Long> listOfItemsId = new ArrayList<>();
            try {
                listOfItemsId = Database.getFactoryItems(factory.getId());
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
            listOfItemsId.forEach(itemId -> {
                for (Item item : items) {
                    if (item.getId().equals(itemId)) {
                        itemSet.add(item);
                    }
                }
            });
            factory.setItems(itemSet);
        });

        nameTableColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getName()));

        addressTableColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getAddress().toString()));

        numOfItemsTableColumn.setCellValueFactory(data ->
                new SimpleStringProperty(String.valueOf(data.getValue().getItems().size())));

        factoriesTableView.setItems(FXCollections.observableList(factories));
    }



    @FXML
    public void onSearchButtonClick(ActionEvent actionEvent) {
        String enteredCity = enterCity.getText();
        List<Factory> filteredList = new ArrayList<>(factories);
        if (!enteredCity.isEmpty()) {
            filteredList = filteredList.stream()
                    .filter(factory -> factory.getAddress().getCity().toLowerCase()
                            .contains(enteredCity.toLowerCase()))
                    .collect(Collectors.toList());
        }
        factoriesTableView.setItems(FXCollections.observableList(filteredList));
    }
}
