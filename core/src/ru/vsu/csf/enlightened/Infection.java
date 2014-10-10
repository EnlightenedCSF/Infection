package ru.vsu.csf.enlightened;

import com.badlogic.gdx.Game;
import ru.vsu.csf.enlightened.screens.IntroScreen;

public class Infection extends Game {
    @Override
	public void create () {
        setScreen(new IntroScreen(this));
	}
}
