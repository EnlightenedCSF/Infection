package ru.vsu.csf.enlightened.gameobjects;

import ru.vsu.csf.enlightened.gameobjects.piece.PieceColor;

/**
 * Класс игрока
 */
public class Player {

    /**
     * Количество набранных очков
     */
    private int score;
    /**
     * Цвет игрока
     */
    private PieceColor color;

    /** Флаг поражения игрока */
    private boolean wasDefeated;
    /** Флаг блокировки игрока */
    private boolean wasLocked;
    /** Флаг победы игрока */
    private boolean hasWon;

    public Player(PieceColor color) {
        score = 0;
        this.color = color;
        wasDefeated = false;
        hasWon = false;
        wasLocked = false;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public PieceColor getColor() {
        return color;
    }

    public void setWasDefeated(boolean wasDefeated) {
        this.wasDefeated = wasDefeated;
    }

    public boolean wasDefeated() {
        return wasDefeated;
    }

    public boolean wasLocked() {
        return wasLocked;
    }

    public void setLock(boolean wasLocked) {
        this.wasLocked = wasLocked;
    }

    public boolean hasWon() {
        return hasWon;
    }

    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }
}