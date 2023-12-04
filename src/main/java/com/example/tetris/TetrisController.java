package com.example.tetris;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class TetrisController implements Initializable {
    public Button start;
    @FXML
    private Label playerScore;

    @FXML
    private Pane rectanglesPane;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initializing...");
        for (int y = -3; y < 21; y++) {
            for (int x = 1; x < 11; x++) {
                Rectangle rectangle = new Rectangle(32, 32, Color.web(Tetris.defaultColor));
                rectangle.setX(x * 32);
                if(y <= 0) {
                    rectangle.setId("x" + x + "ym" + y * -1);
                    rectangle.setY(32);
                }
                else {
                    rectangle.setId("x" + x + "y" + y);
                    rectangle.setY(y * 32);
                }
                rectangle.setStroke(Color.BLACK);
                rectanglesPane.getChildren().add(rectangle);

            }
        }
    }

    @FXML
    protected void start() throws Exception {
        Stage stage;
        stage = (Stage) start.getScene().getWindow();
        Tetris.setScene(stage);
        Tetris.startGame();
    }

    public Label getPlayerScoreLabel() {
        return playerScore;
    }

    public Button getStartButton() {
        return start;
    }
}