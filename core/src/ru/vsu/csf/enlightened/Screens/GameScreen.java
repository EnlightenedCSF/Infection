package ru.vsu.csf.enlightened.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import ru.vsu.csf.enlightened.GameObjects.Board.Board;
import ru.vsu.csf.enlightened.Renderers.BoardRenderer;
import ru.vsu.csf.enlightened.Renderers.UIRenderer;

/** Created by enlightenedcsf on 02.10.14. */
public class GameScreen extends InfectionScreen {

    private Board board;
    private BoardRenderer boardRenderer;
    private UIRenderer uiRenderer;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        ru.vsu.csf.enlightened.GameObjects.Game.getGame().startNewGame();
        board = ru.vsu.csf.enlightened.GameObjects.Game.getGame().getBoard();
        boardRenderer = new BoardRenderer(board);
        uiRenderer = new UIRenderer();

        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (ru.vsu.csf.enlightened.GameObjects.Game.getGame().hasEnded() || boardRenderer.isAnimating())
                    return false;

                if (board.click()) {
                    boardRenderer.getAnimator().init();
                }
                return true;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                screenY = 480 - screenY;
                boardRenderer.setSelectionPosition(screenX, screenY);
                return true;
            }

            @Override
            public boolean keyDown(int keycode) {
                if (ru.vsu.csf.enlightened.GameObjects.Game.getGame().hasEnded()) {
                    game.setScreen(new MainMenuScreen(game));
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        boardRenderer.render();
        uiRenderer.render();
    }
}
