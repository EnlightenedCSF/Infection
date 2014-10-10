package ru.vsu.csf.enlightened.renderers.animators;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.vsu.csf.enlightened.gameobjects.board.Board;

/**
 * Абстрактный класс аниматора
 */
public abstract class Animator {

    /**
     * Ссылка на игровое поле
     */
    protected Board board;
    /**
     * Флаг занятости
     */
    protected boolean inProgress;

    public boolean isInProgress() {
        return inProgress;
    }

    /**
     * Рисует очередной кадр анимации
     * @param batch Контекст
     */
    public abstract void render(Batch batch);
}
