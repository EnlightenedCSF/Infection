package ru.vsu.csf.enlightened.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/** Created by enlightenedcsf on 02.10.14. */
public class IntroScreen extends InfectionScreen {

    private BitmapFont font;

    private Sprite bgSprite;

    public IntroScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        Texture bg = new Texture(Gdx.files.internal("assets/spaceship.png"));
        bgSprite = new Sprite(bg);
        bgSprite.setPosition(InfectionScreen.W/2 - bgSprite.getWidth()/2, InfectionScreen.H/2 - bgSprite.getHeight()/2);

        font = new BitmapFont();
        font.setColor(Color.GRAY);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        batch.begin();
        font.draw(batch, "Ras-ras i v production", 50, 50);
        bgSprite.draw(batch);
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY))
            game.setScreen(new MainMenuScreen(game));
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
