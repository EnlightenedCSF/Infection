package ru.vsu.csf.enlightened.GameObjects;

import java.io.*;

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




    private  BoardCell[][] cells;

    public BoardCell[][] getCells() {
        return cells;
    }

    public void setCells(BoardCell[][] cells) {
        this.cells = cells;
    }


    public Board() {

    }

    public void init(String path) {
        try {
            DataInputStream reader = new DataInputStream(new FileInputStream(path));
            int width = reader.readInt();
            int height = reader.readInt();

            cells = new BoardCell[width][height];

            for (int j = 0; j < cells[0].length; j++) {
                for (int i = 0; i < cells.length; i++) {
                    cells[i][j] = new BoardCell(false);

                    if (reader.readInt() == 0)
                        cells[i][j].setEmpty(true);
                }
            }

            reader.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void saveToFile(String path) {
        try {
            DataOutputStream writer = new DataOutputStream(new FileOutputStream(path));
            writer.writeInt(cells.length);
            writer.writeInt(cells[0].length);

            for (int j = 0; j < cells[0].length; j++) {
                for (int i = 0; i < cells.length; i++) {
                    if (cells[i][j].isEmpty())
                        writer.writeInt(0);
                    else
                        writer.writeInt(1);
                }
            }

            writer.flush();
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
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
