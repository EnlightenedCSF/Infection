package ru.vsu.csf.enlightened.Renderers.animators;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.vsu.csf.enlightened.GameObjects.Board.Board;

/** Created by enlightenedcsf on 09.10.14. */
public abstract class Animator {

    protected Board board;
    protected boolean inProgress;

    public boolean isInProgress() {
        return inProgress;
    }

    public Animator(Board board) {
        this.board = board;
    }

    public abstract void init();
    public abstract void render(Batch batch);

}
