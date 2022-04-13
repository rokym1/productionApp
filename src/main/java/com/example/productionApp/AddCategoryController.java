package com.example.productionApp;

import database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import production.model.Category;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddCategoryController {

    @FXML
    public TextField enterCategoryName;

    @FXML
    public TextArea enterCategoryDescription;

    List<Category> categories = new ArrayList<>();

    @FXML
    public void initialize() {
        try {
            categories = Database.getCategories();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    public void onSaveButtonClick(ActionEvent actionEvent) {

        Long id = (long) categories.size();
        StringBuilder errorMsg = new StringBuilder();

        String name = enterCategoryName.getText();
        String description = enterCategoryDescription.getText();

        if (name.isEmpty()) {
            errorMsg.append("Name field empty!\n");
        }

        if (description.isEmpty()) {
            errorMsg.append("Description field is empty!\n");
        }

        if (errorMsg.isEmpty()) {
            Category newCategory = new Category(id, name, description);

            try {
                Database.saveCategory(newCategory);
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Saved!");
            alert.setHeaderText("Category saved!");
            alert.setContentText("Category " + name.toUpperCase() + " saved!");
            alert.showAndWait();

            new FirstScreenController().showCategorySearchScreen();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Saving failed!");
            alert.setHeaderText("Category not saved");
            alert.setContentText(errorMsg.toString());
            alert.showAndWait();

            enterCategoryName.clear();
            enterCategoryDescription.clear();
        }
    }
}
