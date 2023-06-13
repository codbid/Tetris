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

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Tetris {
    private static int globalX, globalY = 1;
    public static String defaultColor = "0x3c3e3cff";
    private static int typeOfFigure;
    private static int nRotates;
    private static Stage stage;
    private static Figures.Figure.Point[] figure;
    public static int randint(int min, int max)
    {
        return (int)(Math.random()*((max-min)+1))+min;
    }
    public static int writeKeyCode(KeyCode key){
        if(key == KeyCode.W){
            if(Figures.Figure.get_up(figure) > 1)
                return 1;
        } else if(key == KeyCode.S){
            if(Figures.Figure.get_down(figure) < 20)
                return 3;
        } else if(key == KeyCode.A){
            if(Figures.Figure.get_left(figure) > 1)
                return 2;
        } else if(key == KeyCode.D){
            if(Figures.Figure.get_right(figure) < 10)
                return 4;
        } else if(key == KeyCode.R){
            return 5;
        }
        return 0;
    }
    static void set_scene(Stage stage_g) throws IOException {
        stage = stage_g;
        Parent root;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tetris.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setScene(scene);
        stage.show();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                KeyCode key = event.getCode();
                if (collision_down())
                {
                    try {
                        switch (writeKeyCode(key)) {
                            case (1) -> {
                                move_up();
                            }
                            case (2) -> {
                                move_left();
                            }
                            case (3) -> {
                                  move_down();
                            }
                            case (4) -> {
                                move_right();
                            }
                            case (5) -> {
                                rotate();
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                else
                    check_line();
            }
        });

    }

    public static Rectangle get_rectangle(int x, int y)
    {
        Scene scene = stage.getScene();
        if(y >= 0)
            return (Rectangle) scene.lookup("#x"+x+"y"+y);
        else
            return (Rectangle) scene.lookup("#x"+x+"ym"+y*-1);
    }

    public static boolean rectangle_in_figure(Rectangle rectangle)
    {
        for(Figures.Figure.Point point : figure)
        {
            if(Objects.equals(get_rectangle(point.x, point.y), rectangle))
                return true;
        }
        return false;
    }
    public static boolean figure_intersect(Figures.Figure.Point[] figure)
    {
        for(Figures.Figure.Point point : figure)
        {
            Rectangle temp = get_rectangle(point.x, point.y);
            if(!Objects.equals(temp.getFill().toString(), defaultColor) && !rectangle_in_figure(temp))
                return true;
        }
        return false;
    }
    public static void change_color(int x0, int y0, String color) {
        Rectangle rectangle = get_rectangle(x0, y0);
        rectangle.setFill(Color.web(color));
    }
    public static void swap(int x_start, int y_start, int x_dest, int y_dest) {
        Scene scene = stage.getScene();
        Rectangle rectangle_start = get_rectangle(x_start, y_start);
        Rectangle rectangle_dest = get_rectangle(x_dest, y_dest);
        System.out.println(x_start+" "+y_start+" -> "+x_dest+" " +y_dest);
        Color buffer = (Color) rectangle_dest.getFill();
        rectangle_dest.setFill((Color) rectangle_start.getFill());
        if(Objects.equals(buffer.toString(), defaultColor))
            rectangle_start.setFill(buffer);
    }

    public static boolean collision_down() {
        for (Figures.Figure.Point point : figure)
        {
            if (point.y < 20) {
                Rectangle rectangle_down = get_rectangle(point.x, point.y+1);
                if (!Objects.equals(rectangle_down.getFill().toString(), defaultColor) && !rectangle_in_figure(rectangle_down))
                    return false;
            }
            else
                return false;
        }
        return true;
    }

    public static boolean collision_right() {
        for (Figures.Figure.Point point : figure)
        {
            if (point.x < 10) {
                Rectangle rectangle_down = get_rectangle(point.x+1, point.y);
                if (!Objects.equals(rectangle_down.getFill().toString(), defaultColor) && !rectangle_in_figure(rectangle_down))
                    return false;
            }
            else
                return false;
        }
        return true;
    }

    public static boolean collision_left() {
        for (Figures.Figure.Point point : figure)
        {
            if (point.x > 1) {
                Rectangle rectangle_down = get_rectangle(point.x-1, point.y);
                if (!Objects.equals(rectangle_down.getFill().toString(), defaultColor) && !rectangle_in_figure(rectangle_down))
                    return false;
            }
            else
                return false;
        }
        return true;
    }

    public static void new_figure()
    {
        Pair<Integer, Figures.Figure.Point[]> temp = Figures.get_figure();
        typeOfFigure = temp.getA();
        figure = temp.getB();
        nRotates = 0;
        int x0 = 5; // поменять
        int y0 = Figures.Figure.get_down(figure) - Figures.Figure.get_up(figure) + 1;
        for(int i = 0; i < 4; i++)
        {
            change_color(figure[i].x + x0, figure[i].y - y0, "0x0000FF");
            figure[i].x += x0;
            figure[i].y -= y0;
        }

    }

    public static void move_down()
    {
        int yMax = Figures.Figure.get_down(figure);
        for(; yMax != -4; yMax--) {
            for (int i = 0; i < 4; i++) {
                if (figure[i].y == yMax) {
                    int temp_y = figure[i].y;
                    figure[i].y++;
                    swap(figure[i].x, temp_y, figure[i].x, figure[i].y);
                }
            }
        }
    }

    public static void move_up()
    {
        int yMin = Figures.Figure.get_up(figure);
        System.out.println(yMin);
        for(int k = 0; k < 4; k++, yMin++) {
            for (int i = 0; i < 4; i++) {
                if (figure[i].y == yMin) {
                    int temp_y = figure[i].y;
                    figure[i].y--;
                    swap(figure[i].x, temp_y, figure[i].x, figure[i].y);
                }
            }
        }
    }

    public static void move_right()
    {
        if(collision_right()) {
            int xMax = Figures.Figure.get_right(figure);
            for (; xMax != -4; xMax--) {
                for (int i = 0; i < 4; i++) {
                    if (figure[i].x == xMax) {
                        int temp_x = figure[i].x;
                        figure[i].x++;
                        swap(temp_x, figure[i].y, figure[i].x, figure[i].y);
                    }
                }
            }
        }
    }

    public static void move_left() {
        if (collision_left()) {
            int xMin = Figures.Figure.get_left(figure);
            System.out.println(xMin);
            for (int k = 0; k < 4; k++, xMin++) {
                for (int i = 0; i < 4; i++) {
                    if (figure[i].x == xMin) {
                        int temp_x = figure[i].x;
                        figure[i].x--;
                        swap(temp_x, figure[i].y, figure[i].x, figure[i].y);
                    }
                }
            }
        }
    }

    public static void rotate()
    {
        Figures.Figure.Point[] rotatedFigure = Figures.get_rotated_figure(figure, typeOfFigure, nRotates);
        if(Figures.Figure.get_down(rotatedFigure) <= 20 && Figures.Figure.get_right(rotatedFigure) <= 10 && Figures.Figure.get_left(rotatedFigure) >= 1 && !figure_intersect(rotatedFigure))
        {
            for(Figures.Figure.Point point : figure)
                change_color(point.x, point.y, defaultColor);
            for(Figures.Figure.Point point : rotatedFigure)
                change_color(point.x, point.y, "0x0000FF");
            figure = rotatedFigure;
            nRotates = nRotates == 3 ? 0 : nRotates + 1;
        }
        else
        {
            System.out.println(Figures.Figure.get_down(rotatedFigure) <= 20);
            System.out.println(Figures.Figure.get_right(rotatedFigure) <= 10);
            System.out.println(Figures.Figure.get_left(rotatedFigure) >= 1);
            System.out.println(!figure_intersect(rotatedFigure));
        }
    }

    public static void check_line()
    {
        for(int line = 1; line <= 20; line++)
        {
            boolean filled = true;
            for(int row = 1; row <= 10; row++)
            {
                if(Objects.equals(get_rectangle(row, line).getFill().toString(), defaultColor))
                {
                    filled = false;
                    break;
                }
            }
            if(filled)
            {
                for(int row = 1; row <= 10; row++)
                    change_color(row, line, defaultColor);
                for(int i = line; i >= 1; i--) {
                    for (int row = 1; row <= 10; row++) {
                        swap(row, i - 1, row, i);
                    }
                }
            }
        }
    }

    public static class Game implements Runnable
    {
        @Override
        public void run() {

        }
    }
}
