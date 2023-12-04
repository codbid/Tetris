package com.example.tetris;

public class Figures {
    private static final String[] colors = {"0x1aafd9", "0x0a24c9", "0xe3af14", "0xc8e607", "0x51e607", "0x9a1be3", "0xe01010"};
    public static Figure[] figures = new Figure[7];
    public Figures() {
        // O
        figures[0] = new Figure(0);
        figures[0].addPoint(1, 1);
        figures[0].addPoint(2, 1);
        figures[0].addPoint(1, 2);
        figures[0].addPoint(2, 2);
        // I
        figures[1] = new Figure(1);
        figures[1].addPoint(1, 1);
        figures[1].addPoint(2, 1);
        figures[1].addPoint(3, 1);
        figures[1].addPoint(4, 1);
        // S
        figures[2] = new Figure(2);
        figures[2].addPoint(1, 2);
        figures[2].addPoint(2, 2);
        figures[2].addPoint(2, 1);
        figures[2].addPoint(3, 1);
        // Z
        figures[3] = new Figure(3);
        figures[3].addPoint(1, 1);
        figures[3].addPoint(2, 1);
        figures[3].addPoint(2, 2);
        figures[3].addPoint(3, 2);
        // L
        figures[4] = new Figure(4);
        figures[4].addPoint(1, 2);
        figures[4].addPoint(2, 2);
        figures[4].addPoint(3, 2);
        figures[4].addPoint(3, 1);
        // J
        figures[5] = new Figure(5);
        figures[5].addPoint(1, 1);
        figures[5].addPoint(1, 2);
        figures[5].addPoint(2, 2);
        figures[5].addPoint(3, 2);
        // T
        figures[6] = new Figure(6);
        figures[6].addPoint(1, 2);
        figures[6].addPoint(2, 2);
        figures[6].addPoint(2, 1);
        figures[6].addPoint(3, 2);
    }


    public static class Figure {
        private Point[] figure = new Point[4];
        private int typeOfFigure;
        private int nRotates;
        private String color;
        private int globalLen;

        Figure(int t) {
            typeOfFigure = t;
            color = colors[Tetris.randint(0, 6)];
            nRotates = 0;
            globalLen = 0;
        }

        Figure(Figure temp) {
            System.arraycopy(temp.figure, 0, figure, 0, 4);
            typeOfFigure = temp.typeOfFigure;
            nRotates = temp.nRotates;
            globalLen = 0;
            color = temp.color;
        }

        public static class Point {
            int x;
            int y;
        }

        public int getDown() {
            int max = 0;
            for (Point point : figure) {
                if (point.y > max)
                    max = point.y;
            }
            return max;
        }

        public int getUp() {
            int min = 20;
            for (Point point : figure) {
                if (point.y < min)
                    min = point.y;
            }
            return min;
        }

        public int getRight() {
            int max = 0;
            for (Point point : figure) {
                if (point.x > max)
                    max = point.x;
            }
            return max;
        }

        public int getLeft() {
            int min = 20;
            for (Point point : figure) {
                if (point.x < min)
                    min = point.x;
            }
            return min;
        }
        public void addPoint(int x, int y) {
            if (globalLen < 4)
            {
                Point point = new Point();
                point.x = x;
                point.y = y;
                figure[globalLen++] = point;
            }
        }
        public Point[] getFigure() {
            return figure;
        }
        public String getColor() {
            return color;
        }
        public void rotateFigure()
        {
            globalLen = 0;
            System.out.println(typeOfFigure);
            System.out.println(nRotates);
            switch (typeOfFigure) {
                case (0) -> {
                }
                case (1) -> {
                    switch (nRotates) {
                        case (3) -> {
                            int tempX = figure[2].x;
                            addPoint(tempX - 1, figure[1].y);
                            addPoint(tempX, figure[1].y);
                            addPoint(tempX + 1, figure[1].y);
                            addPoint(tempX + 2, figure[1].y);
                        }
                        case (0) -> {
                            int tempY = figure[2].y;
                            addPoint(figure[2].x, tempY - 1);
                            addPoint(figure[2].x, tempY);
                            addPoint(figure[2].x, tempY + 1);
                            addPoint(figure[2].x, tempY + 2);
                        }
                        case (1) -> {
                            int tempX = figure[1].x;
                            addPoint(tempX - 2, figure[2].y);
                            addPoint(tempX - 1, figure[2].y);
                            addPoint(tempX, figure[2].y);
                            addPoint(tempX + 1, figure[2].y);
                        }
                        case (2) -> {
                            int tempY = figure[2].y;
                            addPoint(figure[1].x, tempY - 2);
                            addPoint(figure[1].x, tempY - 1);
                            addPoint(figure[1].x, tempY);
                            addPoint(figure[1].x, tempY + 1);
                        }
                    }
                }
                case (2) -> {
                    switch (nRotates) {
                        case (3) -> {
                            addPoint(figure[0].x - 1, figure[0].y - 1);
                            addPoint( figure[1].x, figure[1].y);
                            addPoint(figure[2].x + 1, figure[2].y - 1);
                            addPoint(figure[3].x + 2, figure[3].y);
                        }
                        case (0) -> {
                            addPoint(figure[0].x + 1, figure[0].y - 1);
                            addPoint(figure[1].x, figure[1].y);
                            addPoint(figure[2].x + 1, figure[2].y + 1);
                            addPoint(figure[3].x, figure[3].y + 2);
                        }
                        case (1) -> {
                            addPoint(figure[0].x + 1, figure[0].y + 1);
                            addPoint(figure[1].x, figure[1].y);
                            addPoint(figure[2].x - 1, figure[2].y + 1);
                            addPoint(figure[3].x - 2, figure[3].y);
                        }
                        case (2) -> {
                            addPoint(figure[0].x - 1, figure[0].y + 1);
                            addPoint( figure[1].x, figure[1].y);
                            addPoint(figure[2].x - 1, figure[2].y - 1);
                            addPoint(figure[3].x, figure[3].y - 2);
                        }
                    }
                }
                case (3) -> {
                    switch (nRotates) {
                        case (3) -> {
                            addPoint(figure[0].x, figure[0].y - 2);
                            addPoint(figure[1].x + 1, figure[1].y - 1);
                            addPoint(figure[2].x, figure[2].y);
                            addPoint(figure[3].x + 1, figure[3].y + 1);
                        }
                        case (0) -> {
                            addPoint(figure[0].x + 2, figure[0].y);
                            addPoint(figure[1].x + 1, figure[1].y + 1);
                            addPoint(figure[2].x, figure[2].y);
                            addPoint(figure[3].x - 1, figure[2].y + 1);
                        }
                        case (1) -> {
                            addPoint(figure[0].x, figure[0].y + 2);
                            addPoint(figure[1].x - 1, figure[1].y + 1);
                            addPoint(figure[2].x, figure[2].y);
                            addPoint(figure[3].x - 1, figure[3].y - 1);
                        }
                        case (2) -> {
                            addPoint(figure[0].x - 2, figure[0].y);
                            addPoint(figure[1].x - 1, figure[1].y - 1);
                            addPoint(figure[2].x, figure[2].y);
                            addPoint(figure[3].x + 1, figure[2].y - 1);
                        }
                    }
                }
                case (4) -> {
                    switch (nRotates) {
                        case (3) -> {
                            addPoint(figure[0].x - 1, figure[0].y - 1);
                            addPoint(figure[1].x, figure[1].y);
                            addPoint(figure[2].x + 1, figure[2].y + 1);
                            addPoint(figure[3].x + 2, figure[3].y);
                        }
                        case (0) -> {
                            addPoint(figure[0].x + 1, figure[0].y - 1);
                            addPoint(figure[1].x, figure[1].y);
                            addPoint(figure[2].x - 1, figure[2].y + 1);
                            addPoint(figure[3].x, figure[3].y + 2);
                        }
                        case (1) -> {
                            addPoint(figure[0].x + 1, figure[0].y + 1);
                            addPoint(figure[1].x, figure[1].y);
                            addPoint(figure[2].x - 1, figure[2].y - 1);
                            addPoint(figure[3].x - 2, figure[3].y);
                        }
                        case (2) -> {
                            addPoint(figure[0].x - 1, figure[0].y + 1);
                            addPoint(figure[1].x, figure[1].y);
                            addPoint(figure[2].x + 1, figure[2].y - 1);
                            addPoint(figure[3].x, figure[3].y - 2);
                        }
                    }
                }
                case (5) -> {
                    switch (nRotates) {
                        case (3) -> {
                            addPoint(figure[0].x, figure[0].y - 2);
                            addPoint(figure[1].x - 1, figure[1].y - 1);
                            addPoint(figure[2].x, figure[2].y);
                            addPoint(figure[3].x + 1, figure[3].y + 1);
                        }
                        case (0) -> {
                            addPoint(figure[0].x + 2, figure[0].y);
                            addPoint(figure[1].x + 1, figure[1].y - 1);
                            addPoint(figure[2].x, figure[2].y);
                            addPoint(figure[3].x - 1, figure[2].y + 1);
                        }
                        case (1) -> {
                            addPoint(figure[0].x, figure[0].y + 2);
                            addPoint(figure[1].x + 1, figure[1].y + 1);
                            addPoint(figure[2].x, figure[2].y);
                            addPoint(figure[3].x - 1, figure[3].y - 1);
                        }
                        case (2) -> {
                            addPoint(figure[0].x - 2, figure[0].y);
                            addPoint(figure[1].x - 1, figure[1].y + 1);
                            addPoint(figure[2].x, figure[2].y);
                            addPoint(figure[3].x + 1, figure[2].y - 1);
                        }
                    }
                }
                case (6) -> {
                    switch (nRotates) {
                        case (3) -> {
                            addPoint(figure[0].x - 1, figure[0].y - 1);
                            addPoint(figure[1].x, figure[1].y);
                            addPoint(figure[2].x + 1, figure[2].y - 1);
                            addPoint(figure[3].x + 1, figure[3].y + 1);
                        }
                        case (0) -> {
                            addPoint(figure[0].x + 1, figure[0].y - 1);
                            addPoint(figure[1].x, figure[1].y);
                            addPoint(figure[2].x + 1, figure[2].y + 1);
                            addPoint(figure[3].x - 1, figure[3].y + 1);
                        }
                        case (1) -> {
                            addPoint(figure[0].x + 1, figure[0].y + 1);
                            addPoint(figure[1].x, figure[1].y);
                            addPoint(figure[2].x - 1, figure[2].y + 1);
                            addPoint(figure[3].x - 1, figure[3].y - 1);
                        }
                        case (2) -> {
                            addPoint(figure[0].x - 1, figure[0].y + 1);
                            addPoint(figure[1].x, figure[1].y);
                            addPoint(figure[2].x - 1, figure[2].y - 1);
                            addPoint(figure[3].x + 1, figure[3].y - 1);
                        }
                    }
                }
            }
            nRotates = nRotates == 3 ? 0 : nRotates + 1;
        }
    }
    public static Figure getRandomFigure() {
        int a = Tetris.randint(0, 6);
        Figure figure = new Figure(figures[a]);
        figure.typeOfFigure = a;
        figure.color = colors[Tetris.randint(0, 6)];

        return figure;
    }




}
