package ru.vsu.csf.enlightened.Screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import ru.vsu.csf.enlightened.GameObjects.Board;
import ru.vsu.csf.enlightened.GameObjects.BoardCell;

/**
 * Created by enlightenedcsf on 02.10.14.
 */
public class MainMenuScreen extends InfectionScreen {

    Batch batch;

    Sprite newGameBtn;
    Sprite multiplayerBtn;
    Sprite optionsBtn;
    Sprite creditsBtn;
    Sprite exitBtn;

    public MainMenuScreen(Game game) {
        super(game);
    }


    @Override
    public void show() {
        super.show();

        batch = new SpriteBatch();
        batch.getProjectionMatrix().setToOrtho2D(0, 0, 600, 480);

        loadAssets();
    }

    private void loadAssets(){
        newGameBtn = new Sprite(new Texture(Gdx.files.internal("newGame.png")));
        multiplayerBtn = new Sprite(new Texture(Gdx.files.internal("multiplayer.png")));
        optionsBtn = new Sprite(new Texture(Gdx.files.internal("options.png")));
        creditsBtn = new Sprite(new Texture(Gdx.files.internal("credits.png")));
        exitBtn = new Sprite(new Texture(Gdx.files.internal("exit.png")));

        float widthCenter = InfectionScreen.W/2;
        float height = 440;
        float margin = 75;

        newGameBtn.setPosition(widthCenter - newGameBtn.getWidth()/2, height -= margin);
        multiplayerBtn.setPosition(widthCenter - multiplayerBtn.getWidth()/2, height -= margin);
        optionsBtn.setPosition(widthCenter - optionsBtn.getWidth()/2, height -= margin);
        creditsBtn.setPosition(widthCenter - creditsBtn.getWidth()/2, height -= margin);
        exitBtn.setPosition(widthCenter - exitBtn.getWidth()/2, height -= margin);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        newGameBtn.draw(batch);
        multiplayerBtn.draw(batch);
        optionsBtn.draw(batch);
        creditsBtn.draw(batch);
        exitBtn.draw(batch);
        batch.end();

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            float x = InfectionScreen.W - Gdx.input.getX();
            float y = InfectionScreen.H - Gdx.input.getY();


            if (isPointerInButton(x, y, newGameBtn))
                game.setScreen(new GameScreen(game));

            if (isPointerInButton(x, y, multiplayerBtn))
            {
                Board board = new Board();

                BoardCell[][] t = new BoardCell[3][2];
                for (int j = 0; j < t[0].length; j++)
                    for (int i = 0; i < t.length; i++)
                        t[i][j] = new BoardCell(false);
                t[0][1].setEmpty(true);

                board.setCells(t);
                board.saveToFile("save.igs");
            }

            if (isPointerInButton(x, y, optionsBtn))
            {
                Board board = new Board();
                board.init("save.igs");
            }

            if (isPointerInButton(x, y, exitBtn))
                Gdx.app.exit();
        }
    }

    private boolean isPointerInButton(float x, float y, Sprite btn) {
        return btn.getBoundingRectangle().contains(x, y);
    }

}
