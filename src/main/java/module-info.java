module com.example.weatherforecast {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.weatherforecast to javafx.fxml;
    exports com.example.weatherforecast;
}