package com.example.productionApp;

import database.Database;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import production.model.Category;
import production.model.Item;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ItemSearchController {

    @FXML
    public TextField enterItemName;

    @FXML
    private ChoiceBox<String> chooseCategory;

    @FXML
    public TableView<Item> itemsTableView;

    @FXML
    public TableColumn<Item, String> nameTableColumn;

    @FXML
    public TableColumn<Item, String> categoryTableColumn;

    @FXML
    public TableColumn<Item, String> widthTableColumn;

    @FXML
    public TableColumn<Item, String> heightTableColumn;

    @FXML
    public TableColumn<Item, String> lengthTableColumn;

    @FXML
    public TableColumn<Item, String> productionCostsTableColumn;

    @FXML
    public TableColumn<Item, String> sellingPriceTableColumn;

    List<Category> categories = new ArrayList<>();
    List<Item> items = new ArrayList<>();


    @FXML
    public void initialize() {

        try {
            categories = Database.getCategories();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        chooseCategory.getItems().add("All");
        categories.forEach(category -> chooseCategory.getItems().add(category.getName()));
        chooseCategory.getSelectionModel().select("All");


        try {
            items = Database.getItems();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        nameTableColumn.setCellValueFactory(dataFeatures ->
                new SimpleStringProperty(dataFeatures.getValue().getName()));

        categoryTableColumn.setCellValueFactory(dataFeatures ->
                new SimpleStringProperty(dataFeatures.getValue().getCategory().getName()));

        widthTableColumn.setCellValueFactory(dataFeatures ->
                new SimpleStringProperty(dataFeatures.getValue().getWidth().toString()));

        heightTableColumn.setCellValueFactory(dataFeatures ->
                new SimpleStringProperty(dataFeatures.getValue().getHeight().toString()));

        lengthTableColumn.setCellValueFactory(dataFeatures ->
                new SimpleStringProperty(dataFeatures.getValue().getLength().toString()));

        productionCostsTableColumn.setCellValueFactory(dataFeatures ->
                new SimpleStringProperty(dataFeatures.getValue().getProductionCost().toString()));

        sellingPriceTableColumn.setCellValueFactory(dataFeatures ->
                new SimpleStringProperty(dataFeatures.getValue().getSellingPrice().toString()));

        itemsTableView.setItems(FXCollections.observableList(items));

        items = items.stream()
                .sorted(Comparator.comparing(Item::getSellingPrice).reversed())
                .collect(Collectors.toList());

        Timeline clock = new Timeline(new KeyFrame(Duration.seconds(2), e ->
                HelloApplication.getStage().setTitle("Item with the highest price: "
                        + items.get(0).getName().toUpperCase())), new KeyFrame(Duration.seconds(10)));

        clock.play();
    }

    @FXML
    public void onSearchButtonClick() {

        String enteredName = enterItemName.getText();

        List<Item> filteredList = new ArrayList<>(items);

        if (chooseCategory.getSelectionModel().getSelectedIndex() > 0) {
            Long catId = categories.get(chooseCategory.getSelectionModel().getSelectedIndex()-1).getId();
            filteredList = filteredList.stream()
                    .filter(item -> item.getCategory().getId().equals(catId))
                    .collect(Collectors.toList());
        }

        if (!enteredName.isEmpty()) {
            filteredList = filteredList.stream()
                    .filter(item -> item.getName().toLowerCase().contains(enteredName.toLowerCase()))
                    .collect(Collectors.toList());
        }

        itemsTableView.setItems(FXCollections.observableList(filteredList));
    }
}
