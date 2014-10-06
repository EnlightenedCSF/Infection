package ru.vsu.csf.enlightened.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by enlightenedcsf on 02.10.14.
 */
public class IntroScreen extends InfectionScreen {

    SpriteBatch batch;
    BitmapFont font;

    Texture bg;
    Sprite bgSprite;

    float time;

    public IntroScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        batch.getProjectionMatrix().setToOrtho2D(0, 0, 600, 480);

        bg = new Texture(Gdx.files.internal("assets/spaceship.png"));
        bgSprite = new Sprite(bg);
        bgSprite.setPosition(InfectionScreen.W/2 - bgSprite.getWidth()/2, InfectionScreen.H/2 - bgSprite.getHeight()/2);

        font = new BitmapFont();
        font.setColor(Color.GRAY);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        font.draw(batch, "EnlightenedCSF production", 50, 50);
        bgSprite.draw(batch);
        batch.end();

        time += delta;
        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY))
            game.setScreen(new MainMenuScreen(game));
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
    }
}
