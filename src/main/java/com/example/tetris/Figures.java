package com.example.tetris;

import eu.hansolo.toolbox.tuples.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Figures {
    private static int globalLen;
    public static class Figure
    {
        public static class Point {
            int x;
            int y;
        }

        public static int get_down(Point[] figure)
        {
            int max = 0;
            for (Point point : figure) {
                if (point.y > max)
                    max = point.y;
            }
            return max;
        }

        public static int get_up(Point[] figure)
        {
            int min = 20;
            for (Point point : figure) {
                if (point.y < min)
                    min = point.y;
            }
            return min;
        }

        public static int get_right(Point[] figure)
        {
            int max = 0;
            for (Point point : figure) {
                if (point.x > max)
                    max = point.x;
            }
            return max;
        }

        public static int get_left(Point[] figure)
        {
            int min = 20;
            for (Point point : figure) {
                if (point.x < min)
                    min = point.x;
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
        Figure.add_point(figures[1].figure, 2, 1);
        Figure.add_point(figures[1].figure, 3, 1);
        Figure.add_point(figures[1].figure, 4, 1);
        // S
        figures[2] = new Figure();
        globalLen = 0;
        Figure.add_point(figures[2].figure, 1, 2);
        Figure.add_point(figures[2].figure, 2, 2);
        Figure.add_point(figures[2].figure, 2, 1);
        Figure.add_point(figures[2].figure, 3, 1);
        // Z
        figures[3] = new Figure();
        globalLen = 0;
        Figure.add_point(figures[3].figure, 1, 1);
        Figure.add_point(figures[3].figure, 2, 1);
        Figure.add_point(figures[3].figure, 2, 2);
        Figure.add_point(figures[3].figure, 3, 2);
        // L
        figures[4] = new Figure();
        globalLen = 0;
        Figure.add_point(figures[4].figure, 1, 2);
        Figure.add_point(figures[4].figure, 2, 2);
        Figure.add_point(figures[4].figure, 3, 2);
        Figure.add_point(figures[4].figure, 3, 1);
        // J
        figures[5] = new Figure();
        globalLen = 0;
        Figure.add_point(figures[5].figure, 1, 1);
        Figure.add_point(figures[5].figure, 1, 2);
        Figure.add_point(figures[5].figure, 2, 2);
        Figure.add_point(figures[5].figure, 3, 2);
        // T
        figures[6] = new Figure();
        globalLen = 0;
        Figure.add_point(figures[6].figure, 1, 2);
        Figure.add_point(figures[6].figure, 2, 2);
        Figure.add_point(figures[6].figure, 2, 1);
        Figure.add_point(figures[6].figure, 3, 2);
    }

    public static Pair<Integer, Figure.Point[]> get_figure() {
        Figures keep = new Figures();
        int a = 6;
        Figure.Point[] figure;
        figure = figures[a].figure;
        return new Pair<>(a, figure);
    }

    public static Figure.Point[] get_rotated_figure(Figure.Point[] figure, int typeOfFigure, int nRotates)
    {
        Figures keep = new Figures();
        Figure.Point[] figure_temp = new Figure.Point[4];
        globalLen = 0;
        System.out.println(nRotates);
        switch (typeOfFigure)
        {
            case (0):
                return figure;
            case (1):
                switch (nRotates) {
                    case (3) -> {
                        Figure.add_point(figure_temp, figure[2].x - 1, figure[1].y);
                        Figure.add_point(figure_temp, figure[2].x, figure[1].y);
                        Figure.add_point(figure_temp, figure[2].x + 1, figure[1].y);
                        Figure.add_point(figure_temp, figure[2].x + 2, figure[1].y);
                    }
                    case (0) -> {
                        Figure.add_point(figure_temp, figure[2].x, figure[2].y - 1);
                        Figure.add_point(figure_temp, figure[2].x, figure[2].y);
                        Figure.add_point(figure_temp, figure[2].x, figure[2].y + 1);
                        Figure.add_point(figure_temp, figure[2].x, figure[2].y + 2);
                    }
                    case (1) -> {
                        Figure.add_point(figure_temp, figure[1].x - 2, figure[2].y);
                        Figure.add_point(figure_temp, figure[1].x - 1, figure[2].y);
                        Figure.add_point(figure_temp, figure[1].x, figure[2].y);
                        Figure.add_point(figure_temp, figure[1].x + 1, figure[2].y);
                    }
                    case (2) -> {
                        Figure.add_point(figure_temp, figure[1].x, figure[2].y - 2);
                        Figure.add_point(figure_temp, figure[1].x, figure[2].y - 1);
                        Figure.add_point(figure_temp, figure[1].x, figure[2].y);
                        Figure.add_point(figure_temp, figure[1].x, figure[2].y + 1);
                    }
                }
                break;
            case (2):
                switch (nRotates) {
                    case (3) -> {
                        Figure.add_point(figure_temp, figure[0].x - 1, figure[0].y - 1);
                        Figure.add_point(figure_temp, figure[1].x, figure[1].y);
                        Figure.add_point(figure_temp, figure[2].x + 1, figure[2].y - 1);
                        Figure.add_point(figure_temp, figure[3].x + 2, figure[3].y);
                    }
                    case (0) -> {
                        Figure.add_point(figure_temp, figure[0].x + 1, figure[0].y - 1);
                        Figure.add_point(figure_temp, figure[1].x, figure[1].y);
                        Figure.add_point(figure_temp, figure[2].x + 1, figure[2].y + 1);
                        Figure.add_point(figure_temp, figure[3].x, figure[3].y + 2);
                    }
                    case (1) -> {
                        Figure.add_point(figure_temp, figure[0].x + 1, figure[0].y + 1);
                        Figure.add_point(figure_temp, figure[1].x, figure[1].y);
                        Figure.add_point(figure_temp, figure[2].x - 1, figure[2].y + 1);
                        Figure.add_point(figure_temp, figure[3].x - 2, figure[3].y);
                    }
                    case (2) -> {
                        Figure.add_point(figure_temp, figure[0].x - 1, figure[0].y + 1);
                        Figure.add_point(figure_temp, figure[1].x, figure[1].y);
                        Figure.add_point(figure_temp, figure[2].x - 1, figure[2].y - 1);
                        Figure.add_point(figure_temp, figure[3].x, figure[2].y - 2);
                    }
                }
                break;
            case (3):
                switch (nRotates) {
                    case (3) -> {
                        Figure.add_point(figure_temp, figure[0].x, figure[0].y - 2);
                        Figure.add_point(figure_temp, figure[1].x + 1, figure[1].y - 1);
                        Figure.add_point(figure_temp, figure[2].x, figure[2].y);
                        Figure.add_point(figure_temp, figure[3].x + 1, figure[3].y + 1);
                    }
                    case (0) -> {
                        Figure.add_point(figure_temp, figure[0].x + 2, figure[0].y);
                        Figure.add_point(figure_temp, figure[1].x + 1, figure[1].y + 1);
                        Figure.add_point(figure_temp, figure[2].x, figure[2].y);
                        Figure.add_point(figure_temp, figure[3].x - 1, figure[2].y + 1);
                    }
                    case (1) -> {
                        Figure.add_point(figure_temp, figure[0].x, figure[0].y + 2);
                        Figure.add_point(figure_temp, figure[1].x - 1, figure[1].y + 1);
                        Figure.add_point(figure_temp, figure[2].x, figure[2].y);
                        Figure.add_point(figure_temp, figure[3].x - 1, figure[3].y - 1);
                    }
                    case (2) -> {
                        Figure.add_point(figure_temp, figure[0].x - 2, figure[0].y);
                        Figure.add_point(figure_temp, figure[1].x - 1, figure[1].y - 1);
                        Figure.add_point(figure_temp, figure[2].x, figure[2].y);
                        Figure.add_point(figure_temp, figure[3].x + 1, figure[2].y - 1);
                    }
                }
                break;
            case (4):
                switch (nRotates) {
                    case (3) -> {
                        Figure.add_point(figure_temp, figure[0].x - 1, figure[0].y - 1);
                        Figure.add_point(figure_temp, figure[1].x, figure[1].y);
                        Figure.add_point(figure_temp, figure[2].x + 1, figure[2].y + 1);
                        Figure.add_point(figure_temp, figure[3].x + 2, figure[3].y);
                    }
                    case (0) -> {
                        Figure.add_point(figure_temp, figure[0].x + 1, figure[0].y - 1);
                        Figure.add_point(figure_temp, figure[1].x, figure[1].y);
                        Figure.add_point(figure_temp, figure[2].x - 1, figure[2].y + 1);
                        Figure.add_point(figure_temp, figure[3].x, figure[3].y + 2);
                    }
                    case (1) -> {
                        Figure.add_point(figure_temp, figure[0].x + 1, figure[0].y + 1);
                        Figure.add_point(figure_temp, figure[1].x, figure[1].y);
                        Figure.add_point(figure_temp, figure[2].x - 1, figure[2].y - 1);
                        Figure.add_point(figure_temp, figure[3].x - 2, figure[3].y);
                    }
                    case (2) -> {
                        Figure.add_point(figure_temp, figure[0].x - 1, figure[0].y + 1);
                        Figure.add_point(figure_temp, figure[1].x, figure[1].y);
                        Figure.add_point(figure_temp, figure[2].x + 1, figure[2].y - 1);
                        Figure.add_point(figure_temp, figure[3].x, figure[3].y - 2);
                    }
                }
                break;
            case (5):
                switch (nRotates) {
                    case (3) -> {
                        Figure.add_point(figure_temp, figure[0].x, figure[0].y - 2);
                        Figure.add_point(figure_temp, figure[1].x - 1, figure[1].y - 1);
                        Figure.add_point(figure_temp, figure[2].x, figure[2].y);
                        Figure.add_point(figure_temp, figure[3].x + 1, figure[3].y + 1);
                    }
                    case (0) -> {
                        Figure.add_point(figure_temp, figure[0].x + 2, figure[0].y);
                        Figure.add_point(figure_temp, figure[1].x + 1, figure[1].y - 1);
                        Figure.add_point(figure_temp, figure[2].x, figure[2].y);
                        Figure.add_point(figure_temp, figure[3].x - 1, figure[2].y + 1);
                    }
                    case (1) -> {
                        Figure.add_point(figure_temp, figure[0].x, figure[0].y + 2);
                        Figure.add_point(figure_temp, figure[1].x + 1, figure[1].y + 1);
                        Figure.add_point(figure_temp, figure[2].x, figure[2].y);
                        Figure.add_point(figure_temp, figure[3].x - 1, figure[3].y - 1);
                    }
                    case (2) -> {
                        Figure.add_point(figure_temp, figure[0].x - 2, figure[0].y);
                        Figure.add_point(figure_temp, figure[1].x - 1, figure[1].y + 1);
                        Figure.add_point(figure_temp, figure[2].x, figure[2].y);
                        Figure.add_point(figure_temp, figure[3].x + 1, figure[2].y - 1);
                    }
                }
                break;
            case (6):
                switch (nRotates) {
                    case (3) -> {
                        Figure.add_point(figure_temp, figure[0].x - 1, figure[0].y - 1);
                        Figure.add_point(figure_temp, figure[1].x, figure[1].y);
                        Figure.add_point(figure_temp, figure[2].x + 1, figure[2].y - 1);
                        Figure.add_point(figure_temp, figure[3].x + 1, figure[3].y + 1);
                    }
                    case (0) -> {
                        Figure.add_point(figure_temp, figure[0].x + 1, figure[0].y - 1);
                        Figure.add_point(figure_temp, figure[1].x, figure[1].y);
                        Figure.add_point(figure_temp, figure[2].x + 1, figure[2].y + 1);
                        Figure.add_point(figure_temp, figure[3].x - 1, figure[3].y + 1);
                    }
                    case (1) -> {
                        Figure.add_point(figure_temp, figure[0].x + 1, figure[0].y + 1);
                        Figure.add_point(figure_temp, figure[1].x, figure[1].y);
                        Figure.add_point(figure_temp, figure[2].x - 1, figure[2].y + 1);
                        Figure.add_point(figure_temp, figure[3].x - 1, figure[3].y - 1);
                    }
                    case (2) -> {
                        Figure.add_point(figure_temp, figure[0].x - 1, figure[0].y + 1);
                        Figure.add_point(figure_temp, figure[1].x, figure[1].y);
                        Figure.add_point(figure_temp, figure[2].x - 1, figure[2].y - 1);
                        Figure.add_point(figure_temp, figure[3].x + 1, figure[3].y - 1);
                    }
                }
                break;
        }
        return figure_temp;
    }

}
