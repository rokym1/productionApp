package com.example.productionApp;

import database.Database;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import production.model.Address;
import production.model.Factory;
import production.model.Item;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddFactoryController {

    @FXML
    public TextField enterFactoryName;

    @FXML
    public ChoiceBox<String> chooseAddress;

    @FXML
    public ListView<String > itemsListView;


    List<Address> addresses = new ArrayList<>();
    List<Factory> factories = new ArrayList<>();
    List<Item> items = new ArrayList<>();

    @FXML
    public void initialize() {
        try {
            items = Database.getItems();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        try {
            addresses = Database.getAddresses();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        try {
            factories = Database.getFactories();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        addresses.forEach(address -> chooseAddress.getItems().add(address.getCity()));
        chooseAddress.getSelectionModel().select(0);

        items.forEach(item -> itemsListView.getItems().add(item.getName().toUpperCase()));
        itemsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        itemsListView.getSelectionModel().selectIndices(0);
    }

    @FXML
    public void onSaveButtonClick(ActionEvent actionEvent) {

        Long id = (long) factories.size();

        String name = enterFactoryName.getText();

        if (name.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Saving failed!");
            alert.setHeaderText("Factory not saved");
            alert.setContentText("Name field empty!");
            alert.showAndWait();

            enterFactoryName.clear();
            itemsListView.getSelectionModel().clearSelection();
            itemsListView.getSelectionModel().selectIndices(0);
            chooseAddress.getSelectionModel().select(0);
        }
        else {
            Factory newFactory = new Factory(id, name, addresses.get(chooseAddress.getSelectionModel().getSelectedIndex()));

            try {
                Database.saveFactory(newFactory);
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }

            ObservableList<Integer> selectedItems = itemsListView.getSelectionModel().getSelectedIndices();
            List<Long> idOfItemsForFactory = new ArrayList<>();
            selectedItems.forEach(selIt -> idOfItemsForFactory.add(items.get(selIt).getId()));

            try {
                factories = Database.getFactories();
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }

            Long facId = factories.get(factories.size() - 1).getId();

            idOfItemsForFactory.forEach(itemId -> {
               try {
                   Database.addFactoryItem(facId, itemId);
               } catch (SQLException | IOException throwables) {
                   throwables.printStackTrace();
               }
            });

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Saved!");
            alert.setHeaderText("Factory saved!");
            alert.setContentText("Factory " + name.toUpperCase() + " saved!");
            alert.showAndWait();

            new FirstScreenController().showFactorySearchScreen();
        }
    }
}
