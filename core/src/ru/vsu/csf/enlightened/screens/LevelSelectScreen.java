package ru.vsu.csf.enlightened.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import ru.vsu.csf.enlightened.Infection;
import ru.vsu.csf.enlightened.screens.button.Button;
import ru.vsu.csf.enlightened.screens.button.LevelSelectButton;
import ru.vsu.csf.enlightened.screens.button.MenuButton;

/** Created by enlightenedcsf on 02.10.14. */
public class LevelSelectScreen extends InfectionScreen {

    public static float TOP_OFFSET  = 250;
    public static float LEFT_OFFSET = 100;
    public static float CELL_SIZE   = 120;

    public LevelSelectScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        buttons.add(new LevelSelectButton(0, 0, 3, game));
        buttons.add(new LevelSelectButton(0, 1, 1, game));
        buttons.add(new LevelSelectButton(1, 0, 4, game));
        buttons.add(new LevelSelectButton(1, 1, 2, game));

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                screenY = Infection.SCREEN_HEIGHT - screenY;

                for (Button button : buttons) {
                    button.mouseHover(screenX, screenY);
                }

                return true;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                screenY = Infection.SCREEN_HEIGHT - screenY;

                for (Button button1 : buttons) {
                    button1.click(screenX, screenY);
                }

                return false;
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        batch.begin();
        for (Button button : buttons) {
            button.render(batch);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
