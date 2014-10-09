package ru.vsu.csf.enlightened.Renderers;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.vsu.csf.enlightened.GameObjects.Board.Board;
import ru.vsu.csf.enlightened.GameObjects.Board.FloatPoint;
import ru.vsu.csf.enlightened.GameObjects.Board.Point;
import ru.vsu.csf.enlightened.GameObjects.Game;

/** Created by enlightenedcsf on 06.10.14. */
public class PieceMoveAnimator {

    private static final double SPEED = 4;
    private static final double EPS   = 3;

    private Board board;

    private FloatPoint position;
    private FloatPoint destination;
    private boolean inProgress;

    private double speedX;
    private double speedY;

    public PieceMoveAnimator(Board board) {
        this.board = board;
        inProgress = false;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void init() {
        position = new FloatPoint() {{
            setX(BoardRenderer.MARGIN_LEFT + board.getSelectedPiecePosition().getX()*BoardRenderer.PIECE_SIZE + 4);
            setY(BoardRenderer.MARGIN_TOP + board.getSelectedPiecePosition().getY()*BoardRenderer.PIECE_SIZE + 4);
        }};

        destination = new FloatPoint() {{
            setX(BoardRenderer.MARGIN_LEFT + board.getSelectedCell().getX()*BoardRenderer.PIECE_SIZE + 4);
            setY(BoardRenderer.MARGIN_TOP + board.getSelectedCell().getY()*BoardRenderer.PIECE_SIZE+ 4);
        }};

        inProgress = true;

        double phi = Math.atan2(destination.getY() - position.getY(), destination.getX() - position.getX());
        speedX = SPEED * Math.cos(phi);
        speedY = SPEED * Math.sin(phi);
    }

    private double getDistance(FloatPoint self, FloatPoint target) {
        return Math.sqrt((self.getX() - target.getX())*(self.getX() - target.getX()) + (self.getY() - target.getY())*(self.getY() - target.getY()));
    }

    public void render(Batch batch) {
        if (getDistance(position, destination) > EPS) {
            position.setX((float) (position.getX() + speedX));
            position.setY((float) (position.getY() + speedY));

            switch (Game.getGame().getCurrentPlayer().getColor()) {
                case RED:
                    batch.draw(BoardRenderer.pieceRed, position.getX(), position.getY(), BoardRenderer.PIECE_SIZE, BoardRenderer.PIECE_SIZE);
                    break;
                case BLUE:
                    batch.draw(BoardRenderer.pieceBlue, position.getX(), position.getY(), BoardRenderer.PIECE_SIZE, BoardRenderer.PIECE_SIZE);
            }

        }
        else {
            inProgress = false;
            board.deployPiece();
        }
    }
}
