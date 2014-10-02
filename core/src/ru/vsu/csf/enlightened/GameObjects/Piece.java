package ru.vsu.csf.enlightened.GameObjects;

/**
 * Перечислимый тип цветов фишек (в т.ч. указатель, является ли иконка фишки подгружаемой из файла)
 */
enum PieceColor {
    RED,
    BLUE,
    GREEN,
    YELLOW,
    PURPLE
}

/**
 * Класс фишки
 */
public class Piece {

    private PieceColor color;

    public Piece(PieceColor c) {
        color = c;
    }
}
