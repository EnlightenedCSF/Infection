package ru.vsu.csf.enlightened.Renderers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.vsu.csf.enlightened.GameObjects.Board.Board;
import ru.vsu.csf.enlightened.GameObjects.Board.BoardCell;
import ru.vsu.csf.enlightened.Renderers.animators.PieceInfectAnimator;
import ru.vsu.csf.enlightened.Renderers.animators.PieceMoveAnimator;

/** Created by enlightenedcsf on 02.10.14. */
public class BoardRenderer {

    private static final boolean DRAW_CELL_INDEXES = false;

    public static final float MARGIN_TOP   = 70;
    public static final float MARGIN_LEFT  = 100;
    public static final float CELL_SIZE    = 50;
    public static final float PIECE_SIZE   = 46;

    public static Texture pieceRed, pieceBlue, pieceGreen, piecePurple, pieceYellow, pieceSelected;
    public static Texture selectionMark;
    public static Texture tile;

    private Board board;

    private Batch batch = new SpriteBatch();

    private PieceMoveAnimator moveAnimator;
    private PieceInfectAnimator infectAnimator;

    public boolean isAnimating() {
        return moveAnimator.isInProgress();
    }

    public PieceMoveAnimator getMoveAnimator() {
        return moveAnimator;
    }

    public PieceInfectAnimator getInfectAnimator() {
        return infectAnimator;
    }

    public BoardRenderer(Board board) {
        this.board = board;
        moveAnimator = new PieceMoveAnimator(board);
        infectAnimator = new PieceInfectAnimator(board);
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
        batch.begin();
        batch.setColor(1, 1, 1, 1);

        drawField();

        if (DRAW_CELL_INDEXES) {
            drawIndexes();
        }

        drawSelectedCell();
        drawSelectedPiece();

        if (moveAnimator.isInProgress())
            moveAnimator.render(batch);


        if (infectAnimator.isInProgress())
            infectAnimator.render(batch);

        batch.end();
    }


    private void drawField() {
        BoardCell[][] cells = board.getCells();

        for (int j = 0; j < cells[0].length; j++) {
            for (int i = 0; i < cells.length; i++) {
                if (!cells[i][j].isEmpty()) {
                    batch.draw(tile, MARGIN_LEFT + i*CELL_SIZE, MARGIN_TOP + j*CELL_SIZE, CELL_SIZE, CELL_SIZE);

                    if (cells[i][j].getPiece() != null) {
                        switch (cells[i][j].getPiece().getColor()) {
                            case RED:
                                batch.draw(pieceRed, MARGIN_LEFT + i*CELL_SIZE + 4, MARGIN_TOP + j*CELL_SIZE + 4, PIECE_SIZE, PIECE_SIZE);
                                break;
                            case BLUE:
                                batch.draw(pieceBlue, MARGIN_LEFT + i*CELL_SIZE + 4, MARGIN_TOP + j*CELL_SIZE + 4, PIECE_SIZE, PIECE_SIZE);
                                break;
                            case GREEN:
                                batch.draw(pieceGreen, MARGIN_LEFT + i*CELL_SIZE + 4, MARGIN_TOP + j*CELL_SIZE + 4, PIECE_SIZE, PIECE_SIZE);
                                break;
                            case PURPLE:
                                batch.draw(piecePurple, MARGIN_LEFT + i*CELL_SIZE + 4, MARGIN_TOP + j*CELL_SIZE + 4, PIECE_SIZE, PIECE_SIZE);
                                break;
                            case YELLOW:
                                batch.draw(pieceYellow, MARGIN_LEFT + i*CELL_SIZE + 4, MARGIN_TOP + j*CELL_SIZE + 4, PIECE_SIZE, PIECE_SIZE);
                                break;
                        }
                    }
                }
            }
        }
    }

    private void drawIndexes() {
        BoardCell[][] cells = board.getCells();

        BitmapFont font = new BitmapFont() {{
            setColor(Color.GREEN);
        }};
        for (int j = 0; j < cells[0].length; j++) {
            for (int i = 0; i < cells.length; i++) {
                if (!cells[i][j].isEmpty()) {
                    font.draw(batch, i + " " + j, MARGIN_LEFT + i * CELL_SIZE + 10, MARGIN_TOP + j * CELL_SIZE + 4);
                }
            }
        }
    }

    private void drawSelectedCell() {
        BoardCell[][] cells = board.getCells();

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
    }

    private void drawSelectedPiece() {
        if (board.hasSelectedPiece()) {
            batch.draw(pieceSelected, MARGIN_LEFT + board.getSelectedPiecePosition().getX() *CELL_SIZE + 4,
                    MARGIN_TOP + board.getSelectedPiecePosition().getY() *CELL_SIZE + 4,
                    CELL_SIZE - 4,
                    CELL_SIZE - 4);
        }
    }

    public void setSelectionPosition(int x, int y) {
        x -= MARGIN_LEFT;
        y -= MARGIN_TOP;

        board.getSelectedCell().setX((int) (x / CELL_SIZE));
        board.getSelectedCell().setY((int) (y / CELL_SIZE));
    }
}
