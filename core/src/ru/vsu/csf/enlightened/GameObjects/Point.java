package ru.vsu.csf.enlightened.GameObjects;

/**
 * Вспомогательный класс для инкапсуляции координат клетки поля.
 */
public class Point {

    /**Х-компонента*/
    private int x;

    /**Y-компонента*/
    private int y;

    public Point() {
        x = 0;
        y = 0;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
