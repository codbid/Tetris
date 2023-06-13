package com.example.tetris;

import eu.hansolo.toolbox.tuples.Pair;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Tetris {
    private static int globalX, globalY = 1;
    public static String defaultColor = "0x3c3e3cff";
    public static int writeKeyCode(KeyCode key){
        if(key == KeyCode.W){
            if(globalY > 1)
                return 1;
        } else if(key == KeyCode.S){
            if(globalY < 20)
                return 3;
        } else if(key == KeyCode.A){
            if(globalX > 1)
                return 2;
        } else if(key == KeyCode.D){
            if(globalX < 10)
                return 4;
        }
        return 0;
    }
    static void set_scene(Stage stage) throws IOException {
        Parent root;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tetris.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setScene(scene);
        stage.show();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                KeyCode key = event.getCode();
                int startX = globalX;
                int startY = globalY;
                System.out.println(writeKeyCode(key));
                if (check_collision(stage, globalX, globalY))
                {
                    try {
                        switch (writeKeyCode(key)) {
                            case (1) -> {
                                globalY--;
                                swap(stage, startX, startY, globalX, globalY);
                            }
                            case (2) -> {
                                globalX--;
                                swap(stage, startX, startY, globalX, globalY);
                            }
                            case (3) -> {
                                globalY++;
                                swap(stage, startX, startY, globalX, globalY);
                            }
                            case (4) -> {
                                globalX++;
                                swap(stage, startX, startY, globalX, globalY);
                            }
                        }
                    } catch (Exception e) {
                        globalX = startX;
                        globalY = startY;
                    }
                }
            }
        });

    }


    public static void change_color(Stage stage) {
        Scene scene = stage.getScene();
        Rectangle rectangle = (Rectangle) scene.lookup("#x"+"1"+"y"+"1");
        globalX = 1;
        globalY = 1;
        Figures.Figure.Point[] f = Figures.get_figure();
        System.out.println(f[0].x);
        System.out.print(f[0].y);
        rectangle.setFill(Color.web("0x0000FF"));
    }
    public static void swap(Stage stage, int x_start, int y_start, int x_dest, int y_dest) {
        Scene scene = stage.getScene();
        Rectangle rectangle_start = (Rectangle) scene.lookup("#x"+x_start+"y"+y_start);
        Rectangle rectangle_dest = (Rectangle) scene.lookup("#x"+x_dest+"y"+y_dest);
        if(Objects.equals(rectangle_dest.getFill().toString(), rectangle_start.getFill().toString()))
        {
            globalX = x_start;
            globalY = y_start;
            return;
        }
        System.out.println(x_start+" "+y_start+" -> "+x_dest+" " +y_dest);
        Color buffer = (Color) rectangle_dest.getFill();
        System.out.println(buffer);
        rectangle_dest.setFill((Color) rectangle_start.getFill());
        rectangle_start.setFill(buffer);
        System.out.println(rectangle_dest.getFill());
    }

    public static boolean check_collision(Stage stage, int x, int y)
    {
        if(y < 20) {
            Scene scene = stage.getScene();
            Rectangle rectangle_down = (Rectangle) scene.lookup("#x" + x + "y" + (y + 1));
            return Objects.equals(rectangle_down.getFill().toString(), defaultColor);
        }
        return false;
    }
}
