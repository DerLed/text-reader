module com.example.textreader {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;



    opens com.example.textreader to javafx.fxml;
    exports com.example.textreader;
}