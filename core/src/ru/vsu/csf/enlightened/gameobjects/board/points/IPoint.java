package ru.vsu.csf.enlightened.gameobjects.board.points;

/**
 * Интерфейс для описания точки в двухмерном пространстве
 * @param <T> Обобщенный тип
 */
public interface IPoint<T> {

    public void setX(T value);
    public void setY(T value);

    public T getX();
    public T getY();
}
