package com.example.productionApp;

import database.Database;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import production.model.Item;
import production.model.Store;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddStoreController {

    @FXML
    public TextField enterStoreName;

    @FXML
    public TextField enterWebAddress;

    @FXML
    public ListView<String> itemsListView;

    List<Store> stores = new ArrayList<>();
    List<Item> items = new ArrayList<>();

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

        items.forEach(item -> itemsListView.getItems().add(item.getName().toUpperCase()));
        itemsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        itemsListView.getSelectionModel().selectIndices(0);
    }

    @FXML
    public void onSaveButtonClick(ActionEvent actionEvent) {

        Long id = (long) stores.size();

        String name = enterStoreName.getText();
        String webAddress = enterWebAddress.getText();
        StringBuilder errorMsg = new StringBuilder();

        if (name.isEmpty()) {
            errorMsg.append("Name field empty!\n");
        }

        if (webAddress.isEmpty()) {
            errorMsg.append("Web address field empty!\n");
        }

        if (errorMsg.isEmpty()) {
            Store newStore = new Store(id, name, webAddress);

            try {
                Database.saveStore(newStore);
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }

            ObservableList<Integer> selectedItems = itemsListView.getSelectionModel().getSelectedIndices();
            List<Long> idOfItemsForStore = new ArrayList<>();
            selectedItems.forEach(selIt -> idOfItemsForStore.add(items.get(selIt).getId()));

            try {
                stores = Database.getStores();
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }

            Long storeId = stores.get(stores.size() - 1).getId();

            idOfItemsForStore.forEach(itemId -> {
                try {
                    Database.addStoreItem(storeId, itemId);
                } catch (SQLException | IOException throwables) {
                    throwables.printStackTrace();
                }
            });

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Saved!");
            alert.setHeaderText("Store saved!");
            alert.setContentText("Store " + name.toUpperCase() + " saved!");
            alert.showAndWait();

            new FirstScreenController().showStoreSearchScreen();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Saving failed!");
            alert.setHeaderText("Store not saved");
            alert.setContentText(errorMsg.toString());
            alert.showAndWait();

            enterStoreName.clear();
            enterWebAddress.clear();
            itemsListView.getSelectionModel().clearSelection();
            itemsListView.getSelectionModel().selectIndices(0);
        }
    }
}
