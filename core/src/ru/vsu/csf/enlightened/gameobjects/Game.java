package ru.vsu.csf.enlightened.gameobjects;

import ru.vsu.csf.enlightened.gameobjects.board.Board;
import ru.vsu.csf.enlightened.gameobjects.piece.PieceColor;

import java.util.ArrayList;

/**
 * Класс игры, отвечающий за игровую логику
 */
public class Game {

    //region Singleton
    private static Game instance;

    private Game() {
        players = new ArrayList<Player>();
    }

    public static Game getGame() {
        if (instance == null)
            instance = new Game();
        return instance;
    }
    //endregion

    /** Ссылка на текущего игрока */
    private Player currentPlayer;
    /** Массив игроков */
    private ArrayList<Player> players;
    /** Индекс текущего игрока в массиве игроков */
    private int currentPlayerIndex;
    /** Игровое поле */
    private Board board;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**  */
    public void addPlayer(PieceColor color) {
        players.add(new Player(color));
        if (players.size() == 1) {
            currentPlayerIndex = 0;
            currentPlayer = players.get(currentPlayerIndex);
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Возвращает игрока по цвету
     * @param color цвет
     * @return ссылка на игрока
     */
    public Player getPlayerByColor(PieceColor color) {
        for (Player player : players) {
            if (player.getColor() == color)
                return player;
        }
        return  null;
    }

    /**
     * Инициализирует массив игроков и загружает выбранный уровен
     * @param lvlNum индекс уровня
     */
    public void startNewGame(final int lvlNum) {
        players = new ArrayList<Player>();
        currentPlayer = null;
        currentPlayerIndex = -1;

        board = new Board() {{
            init("levels/" + lvlNum + ".igs");
        }};
    }

    /**
     * Определяет, закончилась ли игра
     * @return true, если завершена
     */
    public boolean hasEnded() {
        for (Player player : players) {
            if (player.hasWon())
                return true;
        }
        return false;
    }

    /**
     * Передает ход следующему игроку (учитывает блокированных и проигравших игроков)
     */
    public void passTurn() {
        if (currentPlayer.hasWon())
            return;

        do {
            if (currentPlayerIndex == players.size() - 1)
                currentPlayerIndex = 0;
            else
                currentPlayerIndex++;
        }
        while (players.get(currentPlayerIndex).wasDefeated() || players.get(currentPlayerIndex).wasLocked());

        currentPlayer = players.get(currentPlayerIndex);
    }
}