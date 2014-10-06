package ru.vsu.csf.enlightened.GameObjects.Board;

/**
 * Вспомогательный класс для инкапсуляции координат клетки поля.
 */
public class FloatPoint {

    /**Х-компонента*/
    private float x;

    /**Y-компонента*/
    private float y;

    public FloatPoint() {
        x = 0;
        y = 0;
    }

    public FloatPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
