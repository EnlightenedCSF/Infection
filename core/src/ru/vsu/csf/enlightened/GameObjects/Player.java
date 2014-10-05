package ru.vsu.csf.enlightened.GameObjects;

/**
 * Класс игрока
 */
public class Player {

    /**
     * Количество набранных очков
     */
    private int score;

    private PieceColor color;

    private boolean wasDefeated;

    public Player(PieceColor color) {
        score = 0;
        this.color = color;
        wasDefeated = false;
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
}