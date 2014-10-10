package ru.vsu.csf.enlightened;

import com.badlogic.gdx.Game;
import ru.vsu.csf.enlightened.screens.IntroScreen;

public class Infection extends Game {

    public static int SCREEN_WIDTH  = 600;
    public static int SCREEN_HEIGHT = 480;


    @Override
	public void create () {
        setScreen(new IntroScreen(this));
	}
}
