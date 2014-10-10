package ru.vsu.csf.enlightened.gameobjects.board.points;

/**
 * Вспомогательный класс для инкапсуляции координат клетки поля.
 */
public class Point implements IPoint<Integer> {

    /**Х-компонента*/
    private Integer x;

    /**Y-компонента*/
    private Integer y;

    public Point() {
        x = 0;
        y = 0;
    }

    public Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
