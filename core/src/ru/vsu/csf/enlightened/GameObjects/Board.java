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
        BufferedReader reader = null;

        try {
            File file = new File(path);
            reader = new BufferedReader(new FileReader(file));

            String[] sizes = reader.readLine().split(" ");
            cells = new BoardCell[Integer.parseInt(sizes[0])][Integer.parseInt(sizes[1])];

            for (int j = 0; j < cells[0].length; j++) {
                String[] tiles = reader.readLine().split(" ");

                for (int i = 0; i < cells.length; i++) {
                    cells[i][j] = new BoardCell(Integer.parseInt(tiles[i]) == 0);
                }
            }

            String pieceInfo;
            while ((pieceInfo = reader.readLine()) != null) {
                String[] data = pieceInfo.split(" ");
                cells[Integer.parseInt(data[1])][Integer.parseInt(data[2])].setPiece(new Piece(PieceColor.valueOf(data[0])));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            assert reader != null;
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void saveToFile(String path) {

        BufferedWriter writer = null;

        try {
            File f = new File(path);
            writer = new BufferedWriter(new FileWriter(f));

            writer.write(cells.length + " " + cells[0].length + "\n");

            for (int j = 0; j < cells[0].length; j++) {
                for (BoardCell[] cell : cells) {
                    if (cell[j].isEmpty())
                        writer.write("0 ");
                    else
                        writer.write("1 ");
                }
                writer.write('\n');
            }

            String pieceInfo = "";
            for (int j = 0; j < cells[0].length; j++) {
                for (int i = 0; i < cells.length; i++) {
                    if (!cells[i][j].isEmpty() && cells[i][j].getPiece() != null) {
                        pieceInfo = cells[i][j].getPiece().toString() + " " + i + " " + j + "\n";
                        writer.write(pieceInfo);
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                assert writer != null;
                writer.flush();
                writer.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
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
