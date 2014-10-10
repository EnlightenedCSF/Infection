package ru.vsu.csf.enlightened.screens.button;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import ru.vsu.csf.enlightened.screens.GameScreen;
import ru.vsu.csf.enlightened.screens.LevelSelectScreen;
import ru.vsu.csf.enlightened.screens.button.pressaction.PressBehaviour;

/** Created by enlightenedcsf on 10.10.14. */
public class LevelSelectButton extends Button {

    private static BitmapFont font = new BitmapFont() {{
        setColor(Color.YELLOW);
    }};

    private boolean isLvlNumBtn = false;
    private int index;

    public LevelSelectButton(int row, int col, final int index, final Game game) {
        normal = new Sprite(new Texture(Gdx.files.internal("assets/menuBtns/lvlNum.png")));
        hovered = new Sprite(new Texture(Gdx.files.internal("assets/menuBtns/lvlNumHovered.png")));

        isLvlNumBtn = true;
        this.index = index;
        normal.setPosition(LevelSelectScreen.LEFT_OFFSET + row*LevelSelectScreen.CELL_SIZE, LevelSelectScreen.TOP_OFFSET + col*LevelSelectScreen.CELL_SIZE);
        hovered.setPosition(LevelSelectScreen.LEFT_OFFSET + row*LevelSelectScreen.CELL_SIZE, LevelSelectScreen.TOP_OFFSET + col*LevelSelectScreen.CELL_SIZE);

        this.setAction(new PressBehaviour() {
            @Override
            public void execute() {
                game.setScreen(new GameScreen(game, index));
            }
        });
    }


    @Override
    public void render(Batch batch) {
        super.render(batch);

        if (isLvlNumBtn)
            font.draw(batch, index + "", normal.getX() + normal.getWidth()/2 - 5, normal.getY() + normal.getHeight()/2);
    }
}
