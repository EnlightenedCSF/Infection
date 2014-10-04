package ru.vsu.csf.enlightened.Renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.vsu.csf.enlightened.GameObjects.Board;
import ru.vsu.csf.enlightened.GameObjects.BoardCell;

/**
 * Created by enlightenedcsf on 02.10.14.
 */
public class BoardRenderer {

    private static final float MARGIN_TOP   = 70;
    private static final float MARGIN_LEFT  = 100;
    private static final float CELL_SIZE    = 50;


    Board board;
    Texture pieceRed, pieceBlue, pieceGreen, piecePurple, pieceYellow;
    Texture tile;

    private int cellX, cellY;

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
        //batch.draw(tile, MARGIN_LEFT + 0*CELL_SIZE, MARGIN_TOP + 0*CELL_SIZE, CELL_SIZE, CELL_SIZE);

        BitmapFont font = new BitmapFont();
        font.setColor(Color.RED);

        for (int j = 0; j < cells[0].length; j++) {
            for (int i = 0; i < cells.length; i++) {
                if (!cells[i][j].isEmpty()) {
                    batch.draw(tile, MARGIN_LEFT + i*CELL_SIZE, MARGIN_TOP + j*CELL_SIZE, CELL_SIZE, CELL_SIZE);

                    if (cells[i][j].getPiece() != null) {
                        switch (cells[i][j].getPiece().getColor()) {
                            case RED:
                                batch.draw(pieceRed, MARGIN_LEFT + i*CELL_SIZE + 4, MARGIN_TOP + j*CELL_SIZE + 4, CELL_SIZE - 4, CELL_SIZE - 4);
                                break;
                            case BLUE:
                                batch.draw(pieceBlue, MARGIN_LEFT + i*CELL_SIZE + 4, MARGIN_TOP + j*CELL_SIZE + 4, CELL_SIZE - 4, CELL_SIZE - 4);
                                break;
                            case GREEN:
                                batch.draw(pieceGreen, MARGIN_LEFT + i*CELL_SIZE + 4, MARGIN_TOP + j*CELL_SIZE + 4, CELL_SIZE - 4, CELL_SIZE - 4);
                                break;
                            case PURPLE:
                                batch.draw(piecePurple, MARGIN_LEFT + i*CELL_SIZE + 4, MARGIN_TOP + j*CELL_SIZE + 4, CELL_SIZE - 4, CELL_SIZE - 4);
                                break;
                            case YELLOW:
                                batch.draw(pieceYellow, MARGIN_LEFT + i*CELL_SIZE + 4, MARGIN_TOP + j*CELL_SIZE + 4, CELL_SIZE - 4, CELL_SIZE - 4);
                                break;
                        }
                    }

                    font.draw(batch, i+"; "+j, MARGIN_LEFT + i*CELL_SIZE + 5, MARGIN_TOP + j*CELL_SIZE + 5);
                }
            }
        }

        font.setColor(Color.GREEN);
        //boolean xOK = cellX>=0 && cellX < cells.length;
        //Gdx.app.log("", xOK+"");
        if (cellX >= 0 && cellX < cells.length
                && cellY >= 0 && cellY < cells[0].length
                && !cells[cellX][cellY].isEmpty()) {
            font.draw(batch, "v", MARGIN_LEFT + cellX*CELL_SIZE + 20, MARGIN_TOP + cellY*CELL_SIZE + 30);
        }

        batch.end();
    }

    public void setSelectionPosition(int x, int y) {
        x -= MARGIN_LEFT;
        y -= MARGIN_TOP;

        //int xc = (int)x % CELL_SIZE;
        cellX = (int)(x / CELL_SIZE);
        cellY = (int)(y / CELL_SIZE);

        //Gdx.app.log("gm", cellX+"; "+cellY);
    }
}
