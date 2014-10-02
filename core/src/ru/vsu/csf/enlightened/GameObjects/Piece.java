package ru.vsu.csf.enlightened.GameObjects;

/**
 * Перечислимый тип цветов фишек (в т.ч. указатель, является ли иконка фишки подгружаемой из файла)
 */
enum PieceColor {
    Red,
    Blue,
    Green,
    Yellow,
    Purple,
    GFX
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
