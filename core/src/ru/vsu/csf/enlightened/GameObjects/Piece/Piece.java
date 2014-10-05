package ru.vsu.csf.enlightened.GameObjects.Piece;

/**
 * Класс фишки
 */
public class Piece {

    private PieceColor color;

    public Piece(PieceColor c) {
        color = c;
    }

    public PieceColor getColor() {
        return color;
    }

    public void setColor(PieceColor color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color.toString();
    }
}
