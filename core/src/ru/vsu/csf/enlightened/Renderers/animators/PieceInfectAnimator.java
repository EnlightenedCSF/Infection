package ru.vsu.csf.enlightened.Renderers.animators;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.vsu.csf.enlightened.GameObjects.Board.Board;
import ru.vsu.csf.enlightened.Renderers.BoardRenderer;

/** Created by enlightenedcsf on 09.10.14. */
public class PieceInfectAnimator extends Animator {

    private float transparency;
    private boolean isDrawingFromWhiteToSecondColor;

    public PieceInfectAnimator(Board board) {
        super(board);
    }

    @Override
    public void init() {
        this.inProgress = true;
        transparency = 0;
        isDrawingFromWhiteToSecondColor = false;
    }

    @Override
    public void render(Batch batch) {
        transparency += 0.02f;
        if ((0.5f - transparency) < 0.01f) {
            if (!isDrawingFromWhiteToSecondColor) {
                transparency = 0f;
                isDrawingFromWhiteToSecondColor = true;
            }
            else {
                transparency = 0;
                isDrawingFromWhiteToSecondColor = false;
            }
        }

        batch.setColor(1,1,1,1);
        batch.draw(BoardRenderer.tile, 0, 0, 100, 100);

        if (!isDrawingFromWhiteToSecondColor) {
            batch.setColor(1, 1, 1, 1 -getActualTransparency(transparency));
            batch.draw(BoardRenderer.pieceRed, 0, 0, 100, 100);

            batch.setColor(1, 1, 1, getActualTransparency(transparency));
            batch.draw(BoardRenderer.pieceSelected, 0, 0, 100, 100);
        }
        else {
            batch.setColor(1, 1, 1, 1 - getActualTransparency(transparency));
            batch.draw(BoardRenderer.pieceSelected, 0, 0, 100, 100);

            batch.setColor(1, 1, 1, getActualTransparency(transparency));
            batch.draw(BoardRenderer.pieceBlue, 0, 0, 100, 100);
        }
    }

    private float getActualTransparency(float transparency) {
        return (float) Math.sqrt(transparency/2);
    }
}
