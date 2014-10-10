package ru.vsu.csf.enlightened.renderers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.vsu.csf.enlightened.gameobjects.Game;
import ru.vsu.csf.enlightened.gameobjects.Player;

import java.util.ArrayList;

/** Created by enlightenedcsf on 02.10.14. */
public class UIRenderer {

    private static final int MARGIN_TOP  = 40;
    private static final int MARGIN_LEFT = 10;
    private static final int PLAYER_ICON_SIZE = 30;

    private BitmapFont font;
    private Texture defeatMark;
    private Batch batch;

    public UIRenderer() {
        batch = new SpriteBatch();
        font = new BitmapFont() {{
            setColor(Color.GRAY);
        }};
        defeatMark = new Texture("assets/marks/defeatMark.png");
    }

    public void render() {
        ArrayList<Player> players = Game.getGame().getPlayers();

        batch.begin();
        int i = -1;
        for (Player player : players) {
            i++;

            if (Game.getGame().getCurrentPlayer() == player) {
                batch.draw(BoardRenderer.selectionMark, MARGIN_LEFT, 480 - MARGIN_TOP - i*(PLAYER_ICON_SIZE + 5), PLAYER_ICON_SIZE, PLAYER_ICON_SIZE);
            }

            batch.draw(BoardRenderer.pieces.get(player.getColor()),
                    MARGIN_LEFT,
                    480 - MARGIN_TOP - i*(PLAYER_ICON_SIZE + 5),
                    PLAYER_ICON_SIZE, PLAYER_ICON_SIZE
            );

            if (player.wasDefeated()) {
                batch.draw(defeatMark, MARGIN_LEFT, 480 - MARGIN_TOP - i*(PLAYER_ICON_SIZE + 5), PLAYER_ICON_SIZE, PLAYER_ICON_SIZE);
            }

            font.draw(batch, player.getScore()+"",
                    MARGIN_LEFT + PLAYER_ICON_SIZE + 5,
                    480 - MARGIN_TOP - i*(PLAYER_ICON_SIZE + 5) + 20
            );

            if (Game.getGame().hasEnded()) {
                font.draw(batch, "The " + Game.getGame().getCurrentPlayer().getColor() + " has won!", MARGIN_LEFT + 100, 480 - MARGIN_TOP);
                font.draw(batch, "Press any key to return to the Main Menu", MARGIN_LEFT + 100, 480 - MARGIN_TOP - 30);
            }
        }
        batch.end();
    }
}
