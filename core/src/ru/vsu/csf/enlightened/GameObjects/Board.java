package ru.vsu.csf.enlightened.GameObjects;

import java.io.File;

/**
 * Вспомогательный класс для инкапсуляции координат клетки поля.
 */
class Point {

    /**Х-компонента*/
    private int x;

    /**Y-компонента*/
    private int y;

    public Point() {
        x = 0;
        y = 0;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}


/**
 * Класс игрового поля.
 * Хранит само поле и фишки.
 */
public class Board {

    /**
     * Вспомогательный класс клетки игрового поля.
     */
    private class BoardCell {

        /**
         * Существует ли клетка
         */
        private boolean isEmpty;

        /**
         * Фишка
         */
        private Piece piece;


        private BoardCell(boolean isEmpty) {
            this.isEmpty = isEmpty;
        }

        public boolean isEmpty() {
            return isEmpty;
        }

        public void setEmpty(boolean isEmpty) {
            this.isEmpty = isEmpty;
        }

        public Piece getPiece() {
            return piece;
        }

        public void setPiece(Piece piece) {
            this.piece = piece;
        }
    }


    private  BoardCell[][] cells;

    public Board(File file) {
        //TODO: init
    }

    /**
     * Метод, обрабатывающий ход
     * @param from Координаты выбранной фишки
     * @param to Координаты клетки назначения
     */
    public void makeMove(Point from, Point to) {
        //TODO: make a move
    }


    /**
     * Метод, обрабатывающий логику заражения соседних фишек, если таковые имеются
     * @param source Клетка, вокруг которой происходит заражение
     */
    protected void infect(Point source) {
        //TODO: infection logic
    }


}
