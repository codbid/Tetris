package com.example.tetris;

import java.util.ArrayList;
import java.util.List;

public class Figures {
    private static int globalLen;
    public static class Figure
    {
        public static class Point {
            int x;
            int y;
        }

        public static int get_down(List<Point> figure)
        {
            int max = 0;
            for (Point point : figure) {
                if (point.y > max)
                    max = point.y;
            }
            return max;
        }

        public static int get_up(List<Point> figure)
        {
            int min = 0;
            for (Point point : figure) {
                if (point.y < min)
                    min = point.y;
            }
            return min;
        }

        public static void add_point(Point[] figure, int x, int y)
        {
            Point point = new Point();
            point.x = x;
            point.y = y;
            figure[globalLen++] = point;
        }
        Point[] figure = new Point[4];
    }
    public static Figure[] figures = new Figure[7];
    public Figures() {
        // O
        figures[0] = new Figure();
        globalLen = 0;
        Figure.add_point(figures[0].figure, 1, 1);
        Figure.add_point(figures[0].figure, 2, 1);
        Figure.add_point(figures[0].figure, 1, 2);
        Figure.add_point(figures[0].figure, 2, 2);
        // I
        figures[1] = new Figure();
        globalLen = 0;
        Figure.add_point(figures[1].figure, 1, 1);
        Figure.add_point(figures[1].figure, 1, 2);
        Figure.add_point(figures[1].figure, 1, 3);
        Figure.add_point(figures[1].figure, 1, 4);
        // S
        figures[2] = new Figure();
        globalLen = 0;
        Figure.add_point(figures[2].figure, 2, 1);
        Figure.add_point(figures[2].figure, 3, 1);
        Figure.add_point(figures[2].figure, 1, 2);
        Figure.add_point(figures[2].figure, 2, 2);
        // Z
        figures[3] = new Figure();
        globalLen = 0;
        Figure.add_point(figures[3].figure, 1, 1);
        Figure.add_point(figures[3].figure, 2, 1);
        Figure.add_point(figures[3].figure, 2, 2);
        Figure.add_point(figures[3].figure, 2, 3);
        // L
        figures[4] = new Figure();
        globalLen = 0;
        Figure.add_point(figures[4].figure, 1, 1);
        Figure.add_point(figures[4].figure, 1, 2);
        Figure.add_point(figures[4].figure, 1, 3);
        Figure.add_point(figures[4].figure, 2, 3);
        // J
        figures[5] = new Figure();
        globalLen = 0;
        Figure.add_point(figures[5].figure, 2, 1);
        Figure.add_point(figures[5].figure, 2, 2);
        Figure.add_point(figures[5].figure, 2, 3);
        Figure.add_point(figures[5].figure, 1, 3);
        // T
        figures[6] = new Figure();
        globalLen = 0;
        Figure.add_point(figures[6].figure, 1, 1);
        Figure.add_point(figures[6].figure, 2, 1);
        Figure.add_point(figures[6].figure, 3, 1);
        Figure.add_point(figures[6].figure, 2, 2);
    }

    public static Figure.Point[] get_figure()
    {
        Figures keep = new Figures();
        int a = 2;
        Figure.Point[] figure;
        figure = figures[a].figure;
        return figure;
    }
}
