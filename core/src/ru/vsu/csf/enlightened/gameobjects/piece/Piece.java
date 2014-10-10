package ru.vsu.csf.enlightened.gameobjects.piece;

/**
 * Класс фишки
 */
public class Piece {
    /**
     * Цвет фишки
     */
    private PieceColor color;

    public Piece(PieceColor c) {
        color = c;
    }

    public PieceColor getColor() {
        return color;
    }

    @Override
    public String toString() {
        return color.toString();
    }
}
