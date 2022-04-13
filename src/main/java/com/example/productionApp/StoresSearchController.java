package com.example.productionApp;

import database.Database;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import production.model.Item;
import production.model.NamedEntity;
import production.model.Store;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StoresSearchController {

    @FXML
    public ChoiceBox<String> chooseItem;

    @FXML
    public TableView<Store> storesTableView;

    @FXML
    public TableColumn<Store, String> nameTableColumn;

    @FXML
    public TableColumn<Store, String> addressTableColumn;

    @FXML
    public TableColumn<Store, String> itemsTableColumn;

    List<Item> items = new ArrayList<>();
    List<Store> stores = new ArrayList<>();

    @FXML
    public void initialize() {

        try {
            items = Database.getItems();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        try {
            stores = Database.getStores();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        stores.forEach(store -> {
            Set<Item> itemSet = new HashSet<>();
            List<Long> listOfItemsId = new ArrayList<>();
            try {
                listOfItemsId = Database.getStoreItems(store.getId());
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
            store.setItems(itemSet);
        });

        chooseItem.getItems().add("All");
        chooseItem.getItems().addAll(items.stream().map(NamedEntity::getName).collect(Collectors.toList()));
        chooseItem.getSelectionModel().select(0);

        nameTableColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getName()));

        addressTableColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getWebAddress()));

        itemsTableColumn.setCellValueFactory(data -> {
            String string = data.getValue().getItems().stream()
                    .map(NamedEntity::getName)
                    .collect(Collectors.joining("\n"));
            return new SimpleStringProperty(string);
        });

        storesTableView.setItems(FXCollections.observableList(stores));
    }


    @FXML
    public void onSearchButtonClick() {

        int itemId = chooseItem.getSelectionModel().getSelectedIndex();

        List<Store> filteredList = new ArrayList<>(stores);

        if (itemId > 0) {
            filteredList = filteredList.stream()
                    .filter(store -> store.getItems().contains(items.get(itemId-1)))
                    .collect(Collectors.toList());
        }

        storesTableView.setItems(FXCollections.observableList(filteredList));
    }
}

