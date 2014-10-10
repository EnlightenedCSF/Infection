package ru.vsu.csf.enlightened.screens;

import com.badlogic.gdx.*;
import ru.vsu.csf.enlightened.screens.button.Button;
import ru.vsu.csf.enlightened.screens.button.MenuButton;
import ru.vsu.csf.enlightened.screens.button.pressaction.PressBehaviour;

/** Created by enlightenedcsf on 02.10.14. */
public class MainMenuScreen extends InfectionScreen {

    public static float WIDTH_CENTER    = InfectionScreen.W/2;
    public static float TOP_OFFSET      = 350;
    public static float MARGIN          = 75;

    public MainMenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        loadAssets();

        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                screenY = 480 - screenY;

                for (Button button : buttons) {
                    button.mouseHover(screenX, screenY);
                }

                return false;
            }


            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                screenY = 480 - screenY;

                for (Button button1 : buttons) {
                    button1.click(screenX, screenY);
                }

                /*

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
                }*/

                return false;
            }
        });
    }

    private void loadAssets(){
        int index = 0;

        buttons.add(new MenuButton("soloGame", index++) {{
                    setAction(new PressBehaviour() {
                        @Override
                        public void execute() {
                            game.setScreen(new LevelSelectScreen(game));
                        }
                    });
                }}
        );


        //buttons.add(new MenuButton("hotSeat", index++));

        buttons.add(new MenuButton("tutorial", index++) {{
                        setAction(new PressBehaviour() {
                            @Override
                            public void execute() {
                                game.setScreen(new TutorialScreen(game));
                            }
                        });
                    }}
        );

        buttons.add(new MenuButton("exit", index++) {{
                    setAction( new PressBehaviour() {
                        @Override
                        public void execute() {
                            Gdx.app.exit();
                        }
                    });
                }}
        );
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
}
