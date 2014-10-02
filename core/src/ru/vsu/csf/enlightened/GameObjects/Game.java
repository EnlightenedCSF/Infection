package ru.vsu.csf.enlightened.GameObjects;

import java.io.File;

/**
 * Класс игры, отвечающий за игровую логику
 */
public class Game {

    //region Singleton
    private static Game instance;

    private Game() {
        player = new Player("");
        //board = new Board(new File("in.txt"));
    }

    public static Game getGame() {
        if (instance == null)
            instance = new Game();
        return instance;
    }
    //endregion

    private Player player;
    private Board board;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}