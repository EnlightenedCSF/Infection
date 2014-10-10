package ru.vsu.csf.enlightened.renderers.animators;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.vsu.csf.enlightened.gameobjects.board.Board;
import ru.vsu.csf.enlightened.gameobjects.board.Infect;
import ru.vsu.csf.enlightened.gameobjects.board.points.FloatPoint;
import ru.vsu.csf.enlightened.gameobjects.board.points.Point;
import ru.vsu.csf.enlightened.gameobjects.piece.PieceColor;
import ru.vsu.csf.enlightened.renderers.BoardRenderer;

import java.util.ArrayList;

/** Created by enlightenedcsf on 09.10.14. */
public class PieceInfectAnimator extends Animator {

    private static PieceInfectAnimator instance;
    private PieceInfectAnimator() {
        inProgress = false;
    }
    public static PieceInfectAnimator getInstance() {
        if (instance == null)
            instance = new PieceInfectAnimator();
        return instance;
    }


    private float transparency;
    private boolean isDrawingFromWhiteToSecondColor;
    private ArrayList<Infect> area;

    public void init(final Board board, ArrayList<Infect> area) {
        this.board = board;
        this.inProgress = true;
        transparency = 0;
        isDrawingFromWhiteToSecondColor = false;

        this.area = area;
        for (Infect infect : area) {
            Point position = (Point) infect.getPosition();
            infect.setPosition(new FloatPoint(
                    BoardRenderer.MARGIN_LEFT + position.getX() * BoardRenderer.CELL_SIZE + 4,
                    BoardRenderer.MARGIN_TOP + position.getY() * BoardRenderer.CELL_SIZE + 4
            ));
        }
    }


    @Override
    public void render(Batch batch) {
        transparency += 0.05f;
        if ((1 - transparency) < 0.05f) {
            if (!isDrawingFromWhiteToSecondColor) {
                transparency = 0;
                isDrawingFromWhiteToSecondColor = true;
            }
            else {
                for (Infect infect : area) {
                    int x = (int) ((((FloatPoint)infect.getPosition()).getX() - BoardRenderer.MARGIN_LEFT) / BoardRenderer.CELL_SIZE);
                    int y = (int) ((((FloatPoint)infect.getPosition()).getY() - BoardRenderer.MARGIN_TOP) / BoardRenderer.CELL_SIZE);
                    board.deployPiece(x, y, infect.getFinalColor());
                }
                inProgress = false;
                board.finishTurn();
            }
        }

        for (Infect infect : area) {

            FloatPoint pos = (FloatPoint) infect.getPosition();

            if (!isDrawingFromWhiteToSecondColor) {
                batch.setColor(1, 1, 1, 1);
                batch.draw(
                        BoardRenderer.pieces.get(infect.getInitialColor()),
                        pos.getX(), pos.getY(),
                        BoardRenderer.PIECE_SIZE, BoardRenderer.PIECE_SIZE
                );

                batch.setColor(1, 1, 1, getActualTransparency(transparency));
                batch.draw(BoardRenderer.pieces.get(PieceColor.SELECTED),
                        pos.getX(), pos.getY(),
                        BoardRenderer.PIECE_SIZE, BoardRenderer.PIECE_SIZE
                );
            }
            else {
                batch.setColor(1, 1, 1, 1);
                batch.draw(BoardRenderer.pieces.get(PieceColor.SELECTED),
                        pos.getX(), pos.getY(),
                        BoardRenderer.PIECE_SIZE, BoardRenderer.PIECE_SIZE
                );

                batch.setColor(1, 1, 1, getActualTransparency(transparency));
                batch.draw(BoardRenderer.pieces.get(infect.getFinalColor()),
                        pos.getX(), pos.getY(),
                        BoardRenderer.PIECE_SIZE, BoardRenderer.PIECE_SIZE
                );
            }
        }
    }

    private float getActualTransparency(float transparency) {
        return transparency * transparency;
    }
}
