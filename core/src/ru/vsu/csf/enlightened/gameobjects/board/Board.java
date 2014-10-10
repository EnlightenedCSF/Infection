package ru.vsu.csf.enlightened.gameobjects.board;

import ru.vsu.csf.enlightened.gameobjects.Game;
import ru.vsu.csf.enlightened.gameobjects.board.points.Point;
import ru.vsu.csf.enlightened.gameobjects.piece.Piece;
import ru.vsu.csf.enlightened.gameobjects.piece.PieceColor;
import ru.vsu.csf.enlightened.gameobjects.Player;
import ru.vsu.csf.enlightened.renderers.animators.PieceInfectAnimator;
import ru.vsu.csf.enlightened.renderers.animators.PieceMoveAnimator;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Класс игрового поля.
 * Хранит само поле и фишки.
 */
public class Board {

    /** Массив клеток */
    private  BoardCell[][] cells;
    /** Выделена ли фишка */
    private boolean hasSelectedPiece;
    /** Координаты клетки под указателем мыши */
    private Point selectedCell;
    /** Координаты клетки с выделенной фишкой */
    private Point selectedPiecePosition;

    /** Координаты клетки-назначения при перемещении фишки */
    private Point deployPoint;
    /** Цвет передвигаемой фишки */
    private PieceColor deployColor;

    /** Массив со смещениями x и y-координат, содержащий все клетки, соседние с данной */
    private static Point[] neighbours;
    /** Массив со смещениями x и y-координат, содержащий все клетки, находящиеся на расстоянии 2 клеток от данной */
    private static Point[] farNeighbours;

    /** Массив, содержащий количества очков каждого из игроков */
    private HashMap<PieceColor, Integer> scores;
    /** Массив, содержащий информацию о заблокированных игроках */
    private HashMap<PieceColor, Boolean> locks;
    /** Количество пустых ячеек */
    private int freeCells;

    public Board() {
        hasSelectedPiece = false;
        selectedCell = new Point(-1, -1);

        deployPoint = new Point(-1, -1);
        deployColor = PieceColor.BLUE;

        selectedPiecePosition = new Point(-1, -1);
        scores = new HashMap<PieceColor, Integer>();
        locks = new HashMap<PieceColor, Boolean>();
        freeCells = 0;

        neighbours = new Point[8];
        neighbours[0] = new Point(-1, -1);
        neighbours[1] = new Point(-1, 0);
        neighbours[2] = new Point(-1, 1);
        neighbours[3] = new Point(0, -1);
        neighbours[4] = new Point(1, -1);
        neighbours[5] = new Point(1, 0);
        neighbours[6] = new Point(1, 1);
        neighbours[7] = new Point(0, 1);

        farNeighbours = new Point[16];
        farNeighbours[0] = new Point(-2, -2);
        farNeighbours[1] = new Point(-2, -1);
        farNeighbours[2] = new Point(-2, -0);
        farNeighbours[3] = new Point(-2, 1);

        farNeighbours[4] = new Point(-2, 2);
        farNeighbours[5] = new Point(-1, 2);
        farNeighbours[6] = new Point(0, 2);
        farNeighbours[7] = new Point(1, 2);

        farNeighbours[8] = new Point(2, 2);
        farNeighbours[9] = new Point(2, 1);
        farNeighbours[10] = new Point(2, 0);
        farNeighbours[11] = new Point(2, -1);

        farNeighbours[12] = new Point(2, -2);
        farNeighbours[13] = new Point(1, -2);
        farNeighbours[14] = new Point(0, -2);
        farNeighbours[15] = new Point(-1, -2);
    }

    public BoardCell[][] getCells() {
        return cells;
    }

    public Point getSelectedCell() {
        return selectedCell;
    }

    public Point getSelectedPiecePosition() {
        return selectedPiecePosition;
    }

    public boolean hasSelectedPiece() {
        return hasSelectedPiece;
    }

    /**
     * Инициализирует содержимое доски; добавляет игроков
     * @param path Путь к файлу уровня
     */
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
                    boolean isEmpty = Integer.parseInt(tiles[i]) == 0;
                    cells[i][j] = new BoardCell(isEmpty);
                    if (!isEmpty)
                        freeCells++;
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
                    locks.put(color, false);
                }

                if (scores.containsKey(color)) {
                    Integer x = scores.get(color);
                    scores.put(color, ++x);
                }

                cells[Integer.parseInt(data[1])][Integer.parseInt(data[2])].setPiece(new Piece(color));
                freeCells--;
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

    /*public void saveToFile(String path) {

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

            String pieceInfo;
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
    }*/

    /**
     * Обрабатывает ход
     * @param from Координаты выбранной фишки
     * @param to Координаты клетки назначения
     */
    public void makeMove(Point from, Point to) {
        int dist = getDistance(selectedPiecePosition.getX(), selectedPiecePosition.getY(),
                selectedCell.getX(), selectedCell.getY());

        PieceColor currentColor = Game.getGame().getCurrentPlayer().getColor();

        deployPoint = new Point(to.getX(), to.getY());
        deployColor = currentColor;

        Integer count = scores.get(currentColor);
        count++;
        freeCells--;

        if (dist == 2)
        {
            cells[from.getX()][from.getY()].setPiece(null);
            count--;
            freeCells++;
        }
        scores.put(currentColor, count);
    }


    /**
     * Обрабатывает логику заражения соседних фишек, если таковые имеются
     * @param source Клетка, вокруг которой происходит заражение
     */
    protected void infect(Point source) {
        ArrayList<Infect> area = new ArrayList<Infect>();
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

                    area.add(new Infect(enemy.getColor(), currentPlayer.getColor(), new Point(x, y)));

                    Integer enemyCount = scores.get(enemy.getColor());
                    scores.put(enemy.getColor(), --enemyCount);

                    playerCount++;

                    currentPlayer.addScore(1);
                }
            }
        }

        scores.put(currentPlayer.getColor(), playerCount);

        if (area.size() == 0)
            finishTurn();
        else
            PieceInfectAnimator.getInstance().init(this, area);
    }

    /**
     * Проверяет, не победил ли один из игроков
     * @return true, если какой-либо из игроков выиграл
     */
    private boolean checkIfVictory() {
        if (freeCells == 0) {
            int max = -1;
            PieceColor winner = PieceColor.UNDEFINED;
            for (PieceColor color : scores.keySet()) {
                if (scores.get(color) > max) {
                    max = scores.get(color);
                    winner = color;
                }
            }

            Game.getGame().getPlayerByColor(winner).setHasWon(true);
            return true;
        }
        else {
            boolean ok = true;
            boolean wasFirstNonZero = false;
            for (PieceColor color : scores.keySet()) {
                if (scores.get(color) > 0) {
                    if (!wasFirstNonZero)
                        wasFirstNonZero = true;
                    else {
                        ok = false;
                        break;
                    }
                }
            }

            if (ok) {
                Game.getGame().getCurrentPlayer().setHasWon(true);
                return true;
            }
        }

        return false;
    }

    /**
     * Устанавливает флаг поражения игрока в случае, когда убраны все его фишки
     */
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

    /**
     * Устанавливает флаг блокировки игрока, если ему некуда ходить
     */
    private void checkIfLocked() {
        ArrayList<Player> players = Game.getGame().getPlayers();
        for (PieceColor color : locks.keySet()) {
            locks.put(color, true);
        }

        for (Player player : players) {
            boolean OK = false;

            if (player.wasDefeated())
                continue;

            for (int j = 0; j < cells[0].length; j++) {
                if (OK)
                    break;

                for (int i = 0; i < cells.length; i++) {
                    if (OK)
                        break;

                    if (!cells[i][j].isEmpty() && cells[i][j].getPiece() != null && cells[i][j].getPiece().getColor() == player.getColor()) {

                        int x, y;

                        for (Point p : neighbours) {
                            x = i + p.getX();
                            y = j + p.getY();

                            //TODO: вынести checkIsAbsoluteEmpty в метод
                            if (x >= 0 && x < cells.length && y >= 0 && y < cells[0].length && !cells[x][y].isEmpty() && cells[x][y].getPiece() == null)
                            {
                                OK = true;
                                break;
                            }
                        }

                        for (Point p : farNeighbours) {
                            x = i + p.getX();
                            y = j + p.getY();

                            if (x >= 0 && x < cells.length && y >= 0 && y < cells[0].length && !cells[x][y].isEmpty() && cells[x][y].getPiece() == null)
                            {
                                OK = true;
                                break;
                            }
                        }
                    }
                }
            }

            if (OK)
                locks.put(player.getColor(), false);
        }

        for (Player player : players) {
            player.setLock(locks.get(player.getColor()));
        }
    }

    /**
     * Возвращает расстояние между клетками
     * @param x1 x1
     * @param y1 y1
     * @param x2 x2
     * @param y2 y2
     * @return Расстояние между клетками
     */
    private int getDistance(int x1, int y1, int x2, int y2) {
        return Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2));
    }


    /**
     * Проверяет, пуста ли клетка (не учитывает наличие фишки в клетке)
     * @param x х-координата
     * @param y у-координата
     * @return true, если пуста
     */
    private boolean ifCellIsEmpty(int x, int y) {
        return (x >= 0 && x < cells.length &&
                y >= 0 && y < cells[0].length &&
                !cells[x][y].isEmpty());
    }

    /**
     * Проверяет, пуста ли клетка (не учитывает наличие фишки в клетке)
     * @param p Координата
     * @return true, если пуста
     */
    private boolean ifCellIsEmpty(Point p) {
        return this.ifCellIsEmpty(p.getX(), p.getY());
    }

    /**
     * Проверяет, пуста ли клетка (не учитывает наличие фишки в клетке)
     * @param x x-координата
     * @param y у-координата
     * @return true, если пуста
     */
    private boolean ifCellIsEmptyAndContainsNoOne(int x, int y) {
        return this.ifCellIsEmpty(x, y) && cells[x][y].getPiece() == null;
    }

    /**
     * Обрабатывает последовательность действий, совершаемых после щелчка мыши
     */
    public void click() {
        if (!ifCellIsEmpty(selectedCell))
            return;

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
                PieceMoveAnimator.getInstance().init(this);
            }

            hasSelectedPiece = false;
        }
    }

    /**
     * Возвращает фишку на клетку поля после того, как она переместилась
     */
    public void deployPiece() {
        cells[deployPoint.getX()][deployPoint.getY()].setPiece(new Piece(deployColor));

        selectedPiecePosition.setX(-1);
        selectedPiecePosition.setY(-1);

        infect(deployPoint);
    }

    /**
     * Создает новую фишку
     * @param x х-координата
     * @param y у-координата
     * @param color Цвет новой фишки
     */
    public void deployPiece(int x, int y, PieceColor color) {
        cells[x][y].setPiece(new Piece(color));
    }

    /**
     * Проверяет на состояние победы, поражения или блокировки игрока и передает ход
     */
    public void finishTurn() {
        checkIfDefeat();
        if (!checkIfVictory())
            checkIfLocked();

        Game.getGame().passTurn();
    }
}
