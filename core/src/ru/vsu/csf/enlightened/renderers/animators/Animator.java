package ru.vsu.csf.enlightened.renderers.animators;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.vsu.csf.enlightened.gameobjects.board.Board;

/** Created by enlightenedcsf on 09.10.14. */
public abstract class Animator {

    protected Board board;
    protected boolean inProgress;

    public boolean isInProgress() {
        return inProgress;
    }

    public abstract void render(Batch batch);
}
