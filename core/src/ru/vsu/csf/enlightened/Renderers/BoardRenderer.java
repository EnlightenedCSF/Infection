package ru.vsu.csf.enlightened.Renderers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.vsu.csf.enlightened.GameObjects.Board.Board;
import ru.vsu.csf.enlightened.GameObjects.Board.BoardCell;

/** Created by enlightenedcsf on 02.10.14. */
public class BoardRenderer {

    static final float MARGIN_TOP   = 70;
    static final float MARGIN_LEFT  = 100;
    static final float CELL_SIZE    = 50;

    static Texture pieceRed, pieceBlue, pieceGreen, piecePurple, pieceYellow, pieceSelected;
    static Texture selectionMark;

    private Board board;
    private Texture tile;
    private Batch batch = new SpriteBatch();

    private PieceAnimator animator;

    public boolean isAnimating() {
        return animator.isInProgress();
    }

    public PieceAnimator getAnimator() {
        return animator;
    }

    public BoardRenderer(Board board) {
        this.board = board;
        animator = new PieceAnimator(board);
        loadAssets();
    }

    public void loadAssets() {
        pieceRed = new Texture("assets/pieceRed.png");
        pieceBlue = new Texture("assets/pieceBlue.png");
        pieceGreen = new Texture("assets/pieceGreen.png");
        piecePurple = new Texture("assets/piecePurple.png");
        pieceYellow = new Texture("assets/pieceYellow.png");
        pieceSelected = new Texture("assets/pieceSelected.png");
        tile = new Texture("assets/tile.png");
        selectionMark = new Texture("assets/selectionMark.png");
    }


    public void render() {
        BoardCell[][] cells = board.getCells();

        batch.begin();

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
                }
            }
        }

        int cellX = board.getSelectedCell().getX();
        int cellY = board.getSelectedCell().getY();

        if (cellX >= 0 && cellX < cells.length
                && cellY >= 0 && cellY < cells[0].length
                && !cells[cellX][cellY].isEmpty()) {

            batch.draw(selectionMark, MARGIN_LEFT + cellX*CELL_SIZE + 2,
                    MARGIN_TOP + cellY*CELL_SIZE + 2,
                    CELL_SIZE - 2,
                    CELL_SIZE - 2);
        }

        if (board.hasSelectedPiece()) {
            batch.draw(pieceSelected, MARGIN_LEFT + board.getSelectedPiecePosition().getX() *CELL_SIZE + 4,
                    MARGIN_TOP + board.getSelectedPiecePosition().getY() *CELL_SIZE + 4,
                    CELL_SIZE - 4,
                    CELL_SIZE - 4);
        }

        batch.end();
    }


    public void setSelectionPosition(int x, int y) {
        x -= MARGIN_LEFT;
        y -= MARGIN_TOP;

        board.getSelectedCell().setX((int) (x / CELL_SIZE));
        board.getSelectedCell().setY((int) (y / CELL_SIZE));
    }
}
