package ru.vsu.csf.enlightened.gameobjects.board.points;

/** Created by enlightenedcsf on 10.10.14. */
public interface IPoint<T> {

    public void setX(T value);
    public void setY(T value);

    public T getX();
    public T getY();
}
