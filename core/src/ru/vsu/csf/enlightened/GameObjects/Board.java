package ru.vsu.csf.enlightened.GameObjects;

import com.badlogic.gdx.Gdx;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Класс игрового поля.
 * Хранит само поле и фишки.
 */
public class Board {

    private  BoardCell[][] cells;
    private boolean hasSelectedPiece;
    private Point selectedCell;
    private Point selectedPiecePosition;

    private static Point[] neighbours;
    private HashMap<PieceColor, Integer> scores;

    public Board() {
        hasSelectedPiece = false;
        selectedCell = new Point(-1, -1);
        selectedPiecePosition = new Point(-1, -1);
        scores = new HashMap<PieceColor, Integer>();

        neighbours = new Point[8];
        neighbours[0] = new Point(-1, -1);
        neighbours[1] = new Point(-1, 0);
        neighbours[2] = new Point(-1, 1);
        neighbours[3] = new Point(0, -1);
        neighbours[4] = new Point(1, -1);
        neighbours[5] = new Point(1, 0);
        neighbours[6] = new Point(1, 1);
        neighbours[7] = new Point(0, 1);
    }

    public BoardCell[][] getCells() {
        return cells;
    }

    public void setCells(BoardCell[][] cells) {
        this.cells = cells;
    }

    public Point getSelectedCell() {
        return selectedCell;
    }

    public Point getSelectedPiecePosition() {
        return selectedPiecePosition;
    }

    public void setSelectedPiecePosition(Point selectedPiecePosition) {
        this.selectedPiecePosition = selectedPiecePosition;
    }

    public void setSelectedCell(Point selectedCell) {
        this.selectedCell = selectedCell;
    }

    public boolean hasSelectedPiece() {
        return hasSelectedPiece;
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

            ArrayList<PieceColor> colors = new ArrayList<PieceColor>();
            String pieceInfo;
            while ((pieceInfo = reader.readLine()) != null) {
                String[] data = pieceInfo.split(" ");
                PieceColor color = PieceColor.valueOf(data[0]);
                if (!colors.contains(color)) {
                    Game.getGame().addPlayer(color);
                    colors.add(color);
                    scores.put(color, 0);
                }

                if (scores.containsKey(color)) {
                    Integer x = scores.get(color);
                    scores.put(color, ++x);
                }

                cells[Integer.parseInt(data[1])][Integer.parseInt(data[2])].setPiece(new Piece(color));
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
        int dist = getDistance(selectedPiecePosition.getX(), selectedPiecePosition.getY(),
                selectedCell.getX(), selectedCell.getY());

        PieceColor currentColor = Game.getGame().getCurrentPlayer().getColor();
        cells[to.getX()][to.getY()].setPiece(new Piece(currentColor));
        Integer count = scores.get(currentColor);
        count++;

        if (dist == 2)
        {
            cells[from.getX()][from.getY()].setPiece(null);
            count--;
        }
        scores.put(currentColor, count);

        infect(to);

        Game.getGame().passTurn();
    }


    /**
     * Метод, обрабатывающий логику заражения соседних фишек, если таковые имеются
     * @param source Клетка, вокруг которой происходит заражение
     */
    protected void infect(Point source) {
        int xs = source.getX();
        int ys = source.getY();

        Player currentPlayer = Game.getGame().getCurrentPlayer();
        Integer playerCount = scores.get(currentPlayer.getColor());

        for (Point adjacent : neighbours) {
            int x = xs + adjacent.getX();
            int y = ys + adjacent.getY();

            if (x >= 0 && x < cells.length && y >= 0 && y < cells[0].length && !cells[x][y].isEmpty()) {
                Piece enemy = cells[x][y].getPiece();
                if (enemy != null && enemy.getColor() != currentPlayer.getColor()){

                    Integer enemyCount = scores.get(enemy.getColor());
                    scores.put(enemy.getColor(), --enemyCount);

                    enemy.setColor(Game.getGame().getCurrentPlayer().getColor());

                    playerCount++;

                    currentPlayer.addScore(1);
                }
            }
        }

        scores.put(currentPlayer.getColor(), playerCount);

        for (PieceColor color : scores.keySet()) {
            Gdx.app.log("scores", color + " " + scores.get(color)+"");
        }

        checkIfDefeat();
    }

    private void checkIfDefeat() {
        ArrayList<Player> players = Game.getGame().getPlayers();

        for (Player player : players) {
            if (player.getColor() == Game.getGame().getCurrentPlayer().getColor())
                continue;

            if (scores.get(player.getColor()) == 0) {
                player.setWasDefeated(true);
            }
        }
    }


    private int getDistance(int x1, int y1, int x2, int y2) {
        return Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2));
    }

    public void click() {

        Piece piece = cells[selectedCell.getX()][selectedCell.getY()].getPiece();
        if (piece != null && piece.getColor() == Game.getGame().getCurrentPlayer().getColor()) {
            hasSelectedPiece = true;
            selectedPiecePosition.setX(selectedCell.getX());
            selectedPiecePosition.setY(selectedCell.getY());
        }
        else {
            int dist = getDistance(selectedPiecePosition.getX(), selectedPiecePosition.getY(),
                    selectedCell.getX(), selectedCell.getY());

            if (hasSelectedPiece && (dist == 1 || dist == 2) && !cells[selectedCell.getX()][selectedCell.getY()].isEmpty() &&
                    cells[selectedCell.getX()][selectedCell.getY()].getPiece() == null) {

                makeMove(selectedPiecePosition, selectedCell);
            }

            hasSelectedPiece = false;
            selectedPiecePosition.setX(-1);
            selectedPiecePosition.setY(-1);
        }
    }
}
