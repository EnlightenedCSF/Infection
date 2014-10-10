package ru.vsu.csf.enlightened.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.vsu.csf.enlightened.gameobjects.board.Board;
import ru.vsu.csf.enlightened.gameobjects.board.BoardCell;
import ru.vsu.csf.enlightened.gameobjects.piece.Piece;
import ru.vsu.csf.enlightened.gameobjects.piece.PieceColor;

/** Created by enlightenedcsf on 02.10.14. */
public class MainMenuScreen extends InfectionScreen {

    String TAG = "screen";

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

        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                screenY = 480 - screenY;

                if (newGameBtn.getBoundingRectangle().contains(screenX, screenY)) {
                    game.setScreen(new GameScreen(game));
                    return true;
                }

                if (exitBtn.getBoundingRectangle().contains(screenX, screenY)) {
                    Gdx.app.exit();
                    return true;
                }

                if (multiplayerBtn.getBoundingRectangle().contains(screenX, screenY)) {
                    Board board = new Board();

                    BoardCell[][] t = new BoardCell[7][7];
                    for (int j = 0; j < t[0].length; j++)
                        for (int i = 0; i < t.length; i++)
                            t[i][j] = new BoardCell(false);

                    t[0][0].setEmpty(true);
                    t[1][0].setEmpty(true);
                    t[0][1].setEmpty(true);

                    t[6][6].setEmpty(true);
                    t[5][6].setEmpty(true);
                    t[6][5].setEmpty(true);

                    t[0][6].setEmpty(true);
                    t[0][5].setEmpty(true);
                    t[1][6].setEmpty(true);

                    t[6][0].setEmpty(true);
                    t[6][1].setEmpty(true);
                    t[5][0].setEmpty(true);

                    t[3][3].setEmpty(true);

                    t[0][3].setPiece(new Piece(PieceColor.RED));
                    t[3][0].setPiece(new Piece(PieceColor.BLUE));
                    t[3][6].setPiece(new Piece(PieceColor.GREEN));
                    t[6][3].setPiece(new Piece(PieceColor.YELLOW));

                    board.setCells(t);
                    board.saveToFile("save.igs");
                }

                return false;
            }
        });
    }

    private void loadAssets(){
        newGameBtn = new Sprite(new Texture(Gdx.files.internal("assets/newGame.png")));
        multiplayerBtn = new Sprite(new Texture(Gdx.files.internal("assets/multiplayer.png")));
        optionsBtn = new Sprite(new Texture(Gdx.files.internal("assets/options.png")));
        creditsBtn = new Sprite(new Texture(Gdx.files.internal("assets/credits.png")));
        exitBtn = new Sprite(new Texture(Gdx.files.internal("assets/exit.png")));

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
    }
}