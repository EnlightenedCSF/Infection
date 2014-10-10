package ru.vsu.csf.enlightened.gameobjects.board;

import ru.vsu.csf.enlightened.gameobjects.piece.Piece;

/**
 * Вспомогательный класс клетки игрового поля.
 */
public class BoardCell {

    /**
     * Существует ли клетка
     */
    private boolean isEmpty;

    /**
     * Фишка
     */
    private Piece piece;


    public BoardCell(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
