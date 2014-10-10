package ru.vsu.csf.enlightened.gameobjects.board.points;

/**
 * Вспомогательный класс для инкапсуляции координат.
 */
public class FloatPoint implements IPoint<Float> {

    /**Х-компонента*/
    private Float x;

    /**Y-компонента*/
    private Float y;

    public FloatPoint() {
        x = 0f;
        y = 0f;
    }

    public FloatPoint(Float x, Float y) {
        this.x = x;
        this.y = y;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }
}
