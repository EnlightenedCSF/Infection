package ru.vsu.csf.enlightened.Renderers;

import com.badlogic.gdx.Gdx;
import ru.vsu.csf.enlightened.GameObjects.Board.Board;
import ru.vsu.csf.enlightened.GameObjects.Board.FloatPoint;

/** Created by enlightenedcsf on 06.10.14. */
public class PieceAnimator {
    private Board board;

    private FloatPoint position;
    private FloatPoint destination;
    private boolean inProgress;

    public PieceAnimator(Board board) {
        this.board = board;
        inProgress = false;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void init() {
        position = new FloatPoint() {{
            setX(BoardRenderer.MARGIN_LEFT + board.getSelectedPiecePosition().getX()*BoardRenderer.CELL_SIZE + 4);
            setY(BoardRenderer.MARGIN_TOP + board.getSelectedPiecePosition().getY()*BoardRenderer.CELL_SIZE + 4);
        }};

        destination = new FloatPoint() {{
            setX(BoardRenderer.MARGIN_LEFT + board.getSelectedCell().getX()*BoardRenderer.CELL_SIZE + 4);
            setY(BoardRenderer.MARGIN_TOP + board.getSelectedCell().getY()*BoardRenderer.CELL_SIZE + 4);
        }};

        inProgress = true;

        Gdx.app.log("animator", position.getX()+"; "+position.getY());
        Gdx.app.log("animator", destination.getX()+"; "+destination.getY());
    }
}
