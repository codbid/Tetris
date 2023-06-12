package com.example.tetris;

import java.util.ArrayList;
import java.util.List;

public class Figures {
    public static class Figure
    {
        public static class Point {
            int x;
            int y;
        }
        public static void add_point(List<Point> figure, int x, int y)
        {
            Point point = new Point();
            point.x = x;
            point.y = y;
            figure.add(point);
        }
        List<Figure.Point> figure = new ArrayList<Figures.Figure.Point>();
    }
    Figure[] figures = new Figure[7];
    public Figures() {
        // O
        Figure.add_point(figures[0].figure, 1, 1);
        Figure.add_point(figures[0].figure, 2, 1);
        Figure.add_point(figures[0].figure, 1, 2);
        Figure.add_point(figures[0].figure, 2, 2);
        // I
        Figure.add_point(figures[1].figure, 1, 1);
        Figure.add_point(figures[1].figure, 1, 2);
        Figure.add_point(figures[1].figure, 1, 3);
        Figure.add_point(figures[1].figure, 1, 4);
        // S
        Figure.add_point(figures[2].figure, 2, 1);
        Figure.add_point(figures[2].figure, 3, 1);
        Figure.add_point(figures[2].figure, 1, 2);
        Figure.add_point(figures[2].figure, 2, 2);
        // Z
        Figure.add_point(figures[3].figure, 1, 1);
        Figure.add_point(figures[3].figure, 2, 1);
        Figure.add_point(figures[3].figure, 2, 2);
        Figure.add_point(figures[3].figure, 2, 3);
        // L
        Figure.add_point(figures[4].figure, 1, 1);
        Figure.add_point(figures[4].figure, 1, 2);
        Figure.add_point(figures[4].figure, 1, 3);
        Figure.add_point(figures[4].figure, 2, 3);
        // J
        Figure.add_point(figures[5].figure, 2, 1);
        Figure.add_point(figures[5].figure, 2, 2);
        Figure.add_point(figures[5].figure, 2, 3);
        Figure.add_point(figures[5].figure, 1, 3);
        // T
        Figure.add_point(figures[6].figure, 1, 1);
        Figure.add_point(figures[6].figure, 2, 1);
        Figure.add_point(figures[6].figure, 3, 1);
        Figure.add_point(figures[6].figure, 2, 2);
    }
}
