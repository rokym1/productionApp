package com.example.productionApp;

import database.Database;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import production.model.Category;
import production.model.Discount;
import production.model.Item;

import java.io.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddNewItemController {
    @FXML
    public TextField enterItemName;

    @FXML
    public ChoiceBox<String> chooseCategory;

    @FXML
    public TextField enterWidth;

    @FXML
    public TextField enterHeight;

    @FXML
    public TextField enterLength;

    @FXML
    public TextField enterProductionCost;

    @FXML
    public TextField enterSellingPrice;

    @FXML
    public TextField enterDiscount;

    List<Category> categories = new ArrayList<>();
    List<Item> items = new ArrayList<>();

    @FXML
    public void initialize() {

        try {
            categories = Database.getCategories();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        categories.forEach(category -> chooseCategory.getItems().add(category.getName()));
        chooseCategory.getSelectionModel().select(0);

        try {
            items = Database.getItems();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    public void onSaveButtonClick() {

        Long id = (long) items.size();

        StringBuilder errorMsg = new StringBuilder();

        String name = enterItemName.getText();

        if (name.isEmpty()) {
            errorMsg.append("Name field empty!\n");
        }

        String widthSt = enterWidth.getText();
        String heightSt = enterHeight.getText();
        String lengthSt = enterLength.getText();
        String productionCostSt = enterProductionCost.getText();
        String sellingPriceSt = enterSellingPrice.getText();
        String discountSt = enterDiscount.getText();

        BigDecimal width = BigDecimal.ZERO;
        BigDecimal height = BigDecimal.ZERO;
        BigDecimal length = BigDecimal.ZERO;
        BigDecimal productionCost = BigDecimal.ZERO;
        BigDecimal sellingPrice = BigDecimal.ZERO;
        int discountValue = 0;

        if (widthSt.isEmpty()) {
            errorMsg.append("Width field is empty!\n");
        }
        else {
            try {
                width = new BigDecimal(widthSt);
            }
            catch (NumberFormatException ex) {
                errorMsg.append("Width: invalid format!\n");
            }
        }

        if (heightSt.isEmpty()) {
            errorMsg.append("Height field is empty!\n");
        }
        else {
            try {
                height = new BigDecimal(heightSt);
            }
            catch (NumberFormatException ex) {
                errorMsg.append("Height: invalid format!\n");
            }
        }

        if (lengthSt.isEmpty()) {
            errorMsg.append("Length field is empty!\n");
        }
        else {
            try {
                length = new BigDecimal(lengthSt);
            }
            catch (NumberFormatException ex) {
                errorMsg.append("Length: invalid format!\n");
            }
        }

        if (productionCostSt.isEmpty()) {
            errorMsg.append("Production cost field is empty!\n");
        }
        else {
            try {
                productionCost = new BigDecimal(productionCostSt);
            }
            catch (NumberFormatException ex) {
                errorMsg.append("Production cost: invalid format!\n");
            }
        }

        if (sellingPriceSt.isEmpty()) {
            errorMsg.append("Selling price field is empty!\n");
        }
        else {
            try {
                sellingPrice = new BigDecimal(sellingPriceSt);
            }
            catch (NumberFormatException ex) {
                errorMsg.append("Selling price: invalid format!\n");
            }
        }

        try {
            discountValue = Integer.parseInt(discountSt);
        }
        catch (NumberFormatException ex) {
            errorMsg.append("Discount: invalid format!\n");
        }

        if (discountValue < 0 || discountValue > 99) {
            errorMsg.append("Disconut: invalid value!\n");
        }

        Discount discount = new Discount(discountValue);

        if (errorMsg.isEmpty()) {
            Item newItem = new Item(id, name, categories.get(chooseCategory.getSelectionModel().getSelectedIndex()),
                    width, height, length, productionCost, sellingPrice, discount);

            items.add(newItem);

            try {
                Database.saveItem(newItem);
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Saved!");
            alert.setHeaderText("Item saved!");
            alert.setContentText("Item " + name.toUpperCase() + " saved!");
            alert.showAndWait();

            new FirstScreenController().showItemSearchScreen();
        }

        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Saving failed!");
            alert.setHeaderText("Item not saved");
            alert.setContentText(errorMsg.toString());
            alert.showAndWait();

            enterItemName.clear();
            enterWidth.clear();
            enterHeight.clear();
            enterLength.clear();
            enterProductionCost.clear();
            enterSellingPrice.clear();
            enterDiscount.clear();
        }
    }
}
