package ru.vsu.csf.enlightened.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.vsu.csf.enlightened.Infection;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Infection";
        config.width = 600;
        config.height = 480;

		new LwjglApplication(new Infection(), config);
	}
}
