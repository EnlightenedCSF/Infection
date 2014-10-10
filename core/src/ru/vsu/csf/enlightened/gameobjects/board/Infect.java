package ru.vsu.csf.enlightened.gameobjects.board;

import ru.vsu.csf.enlightened.gameobjects.board.points.IPoint;
import ru.vsu.csf.enlightened.gameobjects.board.points.Point;
import ru.vsu.csf.enlightened.gameobjects.piece.PieceColor;

/** Created by enlightenedcsf on 10.10.14. */
public class Infect {

    private PieceColor initialColor;
    private PieceColor finalColor;
    private IPoint position;

    public PieceColor getInitialColor() {
        return initialColor;
    }

    public PieceColor getFinalColor() {
        return finalColor;
    }

    public IPoint getPosition() {
        return position;
    }

    public void setPosition(IPoint position) {
        this.position = position;
    }

    public Infect(PieceColor initialColor, PieceColor finalColor, Point position) {
        this.initialColor = initialColor;
        this.finalColor = finalColor;
        this.position = position;
    }
}
