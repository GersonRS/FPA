package com.gersonrs.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gersonrs.game.Application;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Black Orbit";
		config.width = 1024;
		config.height = 600;
		config.useGL30 = false;
		new LwjglApplication(new Application(), config);
	}
}
