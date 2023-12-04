module com.example.tetris {
    requires javafx.controls;
    requires javafx.fxml;

    requires eu.hansolo.tilesfx;


    opens com.example.tetris to javafx.fxml;
    exports com.example.tetris;
}