package ru.vsu.csf.enlightened.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import ru.vsu.csf.enlightened.GameObjects.Board;
import ru.vsu.csf.enlightened.Renderers.BoardRenderer;

/**
 * Created by enlightenedcsf on 02.10.14.
 */
public class GameScreen extends InfectionScreen {

    Board board;
    BoardRenderer boardRenderer;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        board = new Board();
        board.init("save.igs");
        boardRenderer = new BoardRenderer(board);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        boardRenderer.render();

        if (Gdx.input.isButtonPressed(Input.Keys.ESCAPE))
            game.setScreen(new MainMenuScreen(game));
    }
}
