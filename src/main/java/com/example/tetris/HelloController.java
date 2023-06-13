package com.example.tetris;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    public Button start;
    public Button Change;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws Exception {
        Stage stage;
        stage = (Stage) start.getScene().getWindow();
        Tetris.set_scene(stage);
    }

    @FXML
    protected void change() throws Exception {
        Tetris.start_game();
    }

    public void Test2(ActionEvent actionEvent) {
    }
}