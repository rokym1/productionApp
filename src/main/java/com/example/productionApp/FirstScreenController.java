package com.example.productionApp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

public class FirstScreenController {

    @FXML
    public void showItemSearchScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(
                "itemSearch.fxml"
        ));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(),750, 600);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        } catch (IOException e) {
            e.printStackTrace();
        }

        HelloApplication.getStage().setScene(scene);
        HelloApplication.getStage().show();
    }

    @FXML
    public void showCategorySearchScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(
                "categorySearch.fxml"
        ));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(),750, 600);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        } catch (IOException e) {
            e.printStackTrace();
        }

        HelloApplication.getStage().setScene(scene);
        HelloApplication.getStage().show();
    }

    @FXML
    public void showFactorySearchScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(
                "factoriesSearch.fxml"
        ));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(),750, 600);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        } catch (IOException e) {
            e.printStackTrace();
        }

        HelloApplication.getStage().setScene(scene);
        HelloApplication.getStage().show();
    }

    @FXML
    public void showStoreSearchScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(
                "storesSearch.fxml"
        ));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(),750, 600);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        } catch (IOException e) {
            e.printStackTrace();
        }

        HelloApplication.getStage().setScene(scene);
        HelloApplication.getStage().show();
    }

    @FXML
    public void showAddItemScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(
                "addNewItemScreen.fxml"
        ));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(),750, 600);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        } catch (IOException e) {
            e.printStackTrace();
        }

        HelloApplication.getStage().setScene(scene);
        HelloApplication.getStage().show();
    }

    @FXML
    public void showAddCategoryScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(
                "addCategoryScreen.fxml"
        ));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(),750, 600);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        } catch (IOException e) {
            e.printStackTrace();
        }

        HelloApplication.getStage().setScene(scene);
        HelloApplication.getStage().show();
    }

    @FXML
    public void showAddFactoryScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(
                "addFactoryScreen.fxml"
        ));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(),750, 600);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        } catch (IOException e) {
            e.printStackTrace();
        }

        HelloApplication.getStage().setScene(scene);
        HelloApplication.getStage().show();
    }

    public void showAddStoreScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(
                "addStoreScreen.fxml"
        ));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(),750, 600);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        } catch (IOException e) {
            e.printStackTrace();
        }

        HelloApplication.getStage().setScene(scene);
        HelloApplication.getStage().show();
    }
}

