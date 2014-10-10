package ru.vsu.csf.enlightened.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.vsu.csf.enlightened.Infection;
import ru.vsu.csf.enlightened.screens.button.Button;
import ru.vsu.csf.enlightened.screens.button.MenuButton;

import java.util.ArrayList;

/** Created by enlightenedcsf on 02.10.14. */
public abstract class InfectionScreen implements Screen {

    public static float W =  Gdx.graphics.getWidth();
    public static float H = Gdx.graphics.getHeight();

    public Game game;

    protected SpriteBatch batch;

    protected ArrayList<Button> buttons;

    public InfectionScreen(Game game){
        this.game = game;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        batch = new SpriteBatch() {{
            getProjectionMatrix().setToOrtho2D(0, 0, Infection.SCREEN_WIDTH, Infection.SCREEN_HEIGHT);
        }};
        buttons = new ArrayList<Button>();
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
