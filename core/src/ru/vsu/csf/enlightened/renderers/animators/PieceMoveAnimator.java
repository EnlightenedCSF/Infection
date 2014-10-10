package ru.vsu.csf.enlightened.renderers.animators;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.vsu.csf.enlightened.gameobjects.board.Board;
import ru.vsu.csf.enlightened.gameobjects.board.points.FloatPoint;
import ru.vsu.csf.enlightened.gameobjects.Game;
import ru.vsu.csf.enlightened.renderers.BoardRenderer;

/**
 * Аниматор, занимающийся передвижением фишек
 */
public class PieceMoveAnimator extends Animator {

    /**
     * Абсолютная скорость передвижения
     */
    private static final double SPEED = 4;
    /**
     * Точность достижения конечной локации
     */
    private static final double EPS   = 3;

    /** Текущее положение */
    private FloatPoint position;
    /** Пункт назначения */
    private FloatPoint destination;

    /** Скорость по оси х */
    private double speedX;
    /** Скорость по оси у*/
    private double speedY;

    private static PieceMoveAnimator instance;
    private PieceMoveAnimator() {
        inProgress = false;
        speedX = 0;
        speedY = 0;
    }
    public static PieceMoveAnimator getInstance() {
        if (instance == null)
            instance = new PieceMoveAnimator();
        return instance;
    }

    /**
     * Инициализирует аниматор: вычисляет координаты и скорость
     * @param board Ссылка на игровое поле
     */
    public void init(final Board board) {
        this.board = board;

        position = new FloatPoint() {{
            setX(BoardRenderer.MARGIN_LEFT + board.getSelectedPiecePosition().getX()*BoardRenderer.PIECE_SIZE + 4);
            setY(BoardRenderer.MARGIN_TOP + board.getSelectedPiecePosition().getY()*BoardRenderer.PIECE_SIZE + 4);
        }};

        destination = new FloatPoint() {{
            setX(BoardRenderer.MARGIN_LEFT + board.getSelectedCell().getX()*BoardRenderer.PIECE_SIZE + 4);
            setY(BoardRenderer.MARGIN_TOP + board.getSelectedCell().getY()*BoardRenderer.PIECE_SIZE+ 4);
        }};

        inProgress = true;

        double phi = Math.atan2(destination.getY() - position.getY(), destination.getX() - position.getX());
        speedX = SPEED * Math.cos(phi);
        speedY = SPEED * Math.sin(phi);
    }

    /**
     * Определяет расстояние между 2 точками
     * @param self Текущее положение
     * @param target Конечный пункт
     * @return Расстояние
     */
    private double getDistance(FloatPoint self, FloatPoint target) {
        return Math.sqrt((self.getX() - target.getX())*(self.getX() - target.getX()) + (self.getY() - target.getY())*(self.getY() - target.getY()));
    }

    @Override
    public void render(Batch batch) {
        if (getDistance(position, destination) > EPS) {
            position.setX((float) (position.getX() + speedX));
            position.setY((float) (position.getY() + speedY));

            batch.draw(
                    BoardRenderer.pieces.get(Game.getGame().getCurrentPlayer().getColor()),
                    position.getX(), position.getY(),
                    BoardRenderer.PIECE_SIZE, BoardRenderer.PIECE_SIZE
            );
        }
        else {
            inProgress = false;
            board.deployPiece();
        }
    }
}
