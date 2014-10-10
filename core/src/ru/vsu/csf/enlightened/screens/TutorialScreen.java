package ru.vsu.csf.enlightened.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import ru.vsu.csf.enlightened.screens.button.Button;

/** Created by enlightenedcsf on 10.10.14. */
public class TutorialScreen extends InfectionScreen {

    public static float TOP_OFFSET      = 120;
    public static float LEFT_OFFSET     = 50;

    public TutorialScreen(Game game) {
        super(game);

    }

    @Override
    public void show() {
        super.show();



        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                return false;
            }

        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        for (Button button : buttons) {
            button.render(batch);
        }
    }
}
