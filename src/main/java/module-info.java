module com.example.snakladeraccio {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.snakladeraccio to javafx.fxml;
    exports com.example.snakladeraccio;
}