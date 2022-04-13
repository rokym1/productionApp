package com.example.productionApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {

    private static Stage mainStage;
    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        stage.initStyle(StageStyle.UNIFIED);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("firstScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 600);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        stage.setTitle("Production application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }

    public static Stage getStage() { return mainStage;}
}