package ru.vsu.csf.enlightened.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

/**
 * Created by enlightenedcsf on 02.10.14.
 */
public abstract class InfectionScreen implements Screen {

    Game game;

    public static float W =  Gdx.graphics.getWidth();
    public static float H = Gdx.graphics.getHeight();

    public InfectionScreen(Game game){
        this.game = game;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

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

    }
}
