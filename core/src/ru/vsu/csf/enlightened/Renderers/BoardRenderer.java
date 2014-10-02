package ru.vsu.csf.enlightened.Renderers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.vsu.csf.enlightened.GameObjects.Board;
import ru.vsu.csf.enlightened.GameObjects.BoardCell;

/**
 * Created by enlightenedcsf on 02.10.14.
 */
public class BoardRenderer {

    private static final float MARGIN_TOP = 50;

    Board board;
    Texture pieceRed, pieceBlue, pieceGreen, piecePurple, pieceYellow;
    Texture tile;
    Batch batch = new SpriteBatch();

    public BoardRenderer(Board board) {
        this.board = board;

        loadAssets();
    }

    public void loadAssets() {
        pieceRed = new Texture("pieceRed.png");
        pieceBlue = new Texture("pieceBlue.png");
        pieceGreen = new Texture("pieceGreen.png");
        piecePurple = new Texture("piecePurple.png");
        pieceYellow = new Texture("pieceYellow.png");

        tile = new Texture("tile.png");
    }


    public void render() {
        BoardCell[][] cells = board.getCells();

        batch.begin();
        for (int j = 0; j < cells[0].length; j++) {
            for (int i = 0; i < cells.length; i++) {
                if (!cells[i][j].isEmpty()) {
                    batch.draw(tile, i*20, j*20);
                }
            }
        }
        batch.end();
    }
}
