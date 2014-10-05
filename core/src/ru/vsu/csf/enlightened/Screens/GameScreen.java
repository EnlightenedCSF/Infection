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

    Board board;
    BoardRenderer boardRenderer;
    UIRenderer uiRenderer;
    ru.vsu.csf.enlightened.GameObjects.Game game = ru.vsu.csf.enlightened.GameObjects.Game.getGame();

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        board = new Board();
        board.init("save.igs");
        boardRenderer = new BoardRenderer(board);
        uiRenderer = new UIRenderer();

        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                board.click();
                return true;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                screenY = 480 - screenY;
                boardRenderer.setSelectionPosition(screenX, screenY);
                return true;
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
