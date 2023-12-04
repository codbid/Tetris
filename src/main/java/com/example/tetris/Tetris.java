package com.example.tetris;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.concurrent.TimeUnit;

import java.io.IOException;
import java.util.Objects;

public class Tetris {
    public static String defaultColor = "0x3c3e3cff";
    private static Stage stage;
    private static Scene scene;
    private static int playerScore = 0;

    private static Figures.Figure figure;

    public static int randint(int min, int max) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }

    private static int writeKeyCode(KeyCode key) {
        if (key == KeyCode.W) {
            return 0;
        } else if (key == KeyCode.S) {
            if (figure.getDown() < 20)
                return 3;
        } else if (key == KeyCode.A) {
            if (figure.getLeft() > 1)
                return 2;
        } else if (key == KeyCode.D) {
            if (figure.getRight() < 10)
                return 4;
        } else if (key == KeyCode.R) {
            return 5;
        }
        return 0;
    }

    static void setScene(Stage stage_g) throws IOException {
        stage = stage_g;
        FXMLLoader fxmlLoader = new FXMLLoader(TetrisApplication.class.getResource("tetris.fxml"));
        scene = new Scene(fxmlLoader.load());
        TetrisController controller = fxmlLoader.getController();
        scene.setUserData(controller);
        stage.setScene(scene);
        stage.show();
    }

    private static Rectangle getRectangle(int x, int y) {
        Scene scene = stage.getScene();
        if (y > 0)
            return (Rectangle) scene.lookup("#x" + x + "y" + y);
        else
            return (Rectangle) scene.lookup("#x" + x + "ym" + y * -1);
    }

    private static boolean rectangleInFigure(Rectangle rectangle) {
        for (Figures.Figure.Point point : figure.getFigure()) {
            if (Objects.equals(getRectangle(point.x, point.y), rectangle))
                return true;
        }
        return false;
    }

    private static boolean figureIntersect() {
        for (Figures.Figure.Point point : figure.getFigure()) {
            Rectangle temp = getRectangle(point.x, point.y);
            if (!Objects.equals(temp.getFill().toString(), defaultColor) && !rectangleInFigure(temp))
                return true;
        }
        return false;
    }

    private static void changeColor(int x0, int y0, String color) {
        Rectangle rectangle = getRectangle(x0, y0);
        rectangle.setFill(Color.web(color));
    }

    private static void swap(int x_start, int y_start, int x_dest, int y_dest) {
        Rectangle rectangle_start = getRectangle(x_start, y_start);
        Rectangle rectangle_dest = getRectangle(x_dest, y_dest);
        Color buffer = (Color) rectangle_dest.getFill();
        rectangle_dest.setFill(rectangle_start.getFill());
        if (Objects.equals(buffer.toString(), defaultColor) && !Objects.equals((Color) rectangle_start.getFill(), buffer))
            rectangle_start.setFill(buffer);
    }

    private static boolean collisionDown() {
        for (Figures.Figure.Point point : figure.getFigure()) {
            if (point.y < 20) {
                Rectangle rectangle_down = getRectangle(point.x, point.y + 1);
                if (!Objects.equals(rectangle_down.getFill().toString(), defaultColor) && !rectangleInFigure(rectangle_down)) {
                    return false;
                }
            } else
                return false;
        }
        return true;
    }

    private static boolean collisionRight() {
        for (Figures.Figure.Point point : figure.getFigure()) {
            if (point.x < 10) {
                Rectangle rectangle_down = getRectangle(point.x + 1, point.y);
                if (!Objects.equals(rectangle_down.getFill().toString(), defaultColor) && !rectangleInFigure(rectangle_down))
                    return false;
            } else
                return false;
        }
        return true;
    }

    private static boolean collisionLeft() {
        for (Figures.Figure.Point point : figure.getFigure()) {
            if (point.x > 1) {
                Rectangle rectangle_down = getRectangle(point.x - 1, point.y);
                if (!Objects.equals(rectangle_down.getFill().toString(), defaultColor) && !rectangleInFigure(rectangle_down))
                    return false;
            } else
                return false;
        }
        return true;
    }

    private static void newFigure() {
        new Figures();
        figure = Figures.getRandomFigure();
        int x0 = randint(1, 6); // поменять
        int y0 = figure.getDown() - figure.getUp() + 1;
        for (int i = 0; i < 4; i++) {
            changeColor(figure.getFigure()[i].x + x0, figure.getFigure()[i].y - y0, figure.getColor());
            figure.getFigure()[i].x += x0;
            figure.getFigure()[i].y -= y0;
        }

    }

    private static void moveDown() {
        int yMax = figure.getDown();
        for (; yMax != -4; yMax--) {
            for (int i = 0; i < 4; i++) {
                if (figure.getFigure()[i].y == yMax) {
                    int temp_y = figure.getFigure()[i].y;
                    figure.getFigure()[i].y++;
                    swap(figure.getFigure()[i].x, temp_y, figure.getFigure()[i].x, figure.getFigure()[i].y);
                }
            }
        }
    }

    private static void moveRight() {
        if (collisionRight()) {
            int xMax = figure.getRight();
            for (; xMax != -4; xMax--) {
                for (int i = 0; i < 4; i++) {
                    if (figure.getFigure()[i].x == xMax) {
                        int temp_x = figure.getFigure()[i].x;
                        figure.getFigure()[i].x++;
                        swap(temp_x, figure.getFigure()[i].y, figure.getFigure()[i].x, figure.getFigure()[i].y);
                    }
                }
            }
        }
    }

    private static void moveLeft() {
        if (collisionLeft()) {
            int xMin = figure.getLeft();
            for (int k = 0; k < 4; k++, xMin++) {
                for (int i = 0; i < 4; i++) {
                    if (figure.getFigure()[i].x == xMin) {
                        int temp_x = figure.getFigure()[i].x;
                        figure.getFigure()[i].x--;
                        swap(temp_x, figure.getFigure()[i].y, figure.getFigure()[i].x, figure.getFigure()[i].y);
                    }
                }
            }
        }
    }

    private static void rotate() {
        Figures.Figure oldFigure = new Figures.Figure(figure);
        figure.rotateFigure();
        if (figure.getDown() <= 20 && figure.getRight() <= 10 && figure.getLeft() >= 1 && !figureIntersect()) {
            for (Figures.Figure.Point point : oldFigure.getFigure())
                changeColor(point.x, point.y, defaultColor);
            for (Figures.Figure.Point point : figure.getFigure())
                changeColor(point.x, point.y, figure.getColor());
        }
        else
            figure = oldFigure;
    }

    private static boolean keepUnderBlock(int x, int y) {
        if (y != 20)
            return Objects.equals(getRectangle(x, y + 1).getFill().toString(), defaultColor) && !Objects.equals(getRectangle(x, y).getFill().toString(), defaultColor);
        return false;
    }

    private static void checkLine() {
        for (int line = 1; line <= 20; line++) {
            boolean filled = true;
            for (int row = 1; row <= 10; row++) {
                if (Objects.equals(getRectangle(row, line).getFill().toString(), defaultColor)) {
                    filled = false;
                    break;
                }
            }
            if (filled) {
                playerScore += 1000;
                for (int row = 1; row <= 10; row++)
                    changeColor(row, line, defaultColor);
                for (int i = line; i >= 1; i--) {
                    for (int row = 1; row <= 10; row++) {
                        int y = i;
                        while (keepUnderBlock(row, y)) {
                            swap(row, y, row, y + 1);
                            y++;
                        }
                    }
                }
            }
        }
    }

    public static void startGame() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                Platform.runLater(() -> {
                    KeyCode key = event.getCode();
                    if (collisionDown()) {
                        try {
                            switch (writeKeyCode(key)) {
                                case 2 -> moveLeft();
                                case 3 -> moveDown();
                                case 4 -> moveRight();
                                case 5 -> rotate();
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                });
            }
        });
        Game game = new Game();
        Thread thread = new Thread(game);
        thread.start();
    }

    private static void setLabelText(String text) {
        TetrisController controller = (TetrisController) stage.getScene().getUserData();
        Label label;
        label = controller.getPlayerScoreLabel();
        if (label != null) {
            Platform.runLater(() -> label.setText(text));
        }
    }
    private static void setVisibleButtonStart(boolean mode) {
        TetrisController controller = (TetrisController) stage.getScene().getUserData();
        Button button;
        button = controller.getStartButton();
        if (button != null) {
            Platform.runLater(() -> button.setVisible(mode));
        }
    }


    private static class Game implements Runnable
    {
        @Override
        public void run() {
            setVisibleButtonStart(false);
            while (true)
            {
                newFigure();
                setLabelText(String.valueOf(playerScore));
                while (collisionDown())
                {
                    moveDown();
                    try {
                        TimeUnit.MILLISECONDS.sleep(500 - playerScore / 100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                checkLine();
                if(figure.getUp() <= 1) {
                    setVisibleButtonStart(true);
                    playerScore = 0;
                    return;
                }
            }
        }
    }
}
