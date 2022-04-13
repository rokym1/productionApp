module com.example.ivancic7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.productionApp to javafx.fxml;
    exports com.example.productionApp;
}