package com.matias.bouncingbullets.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.matias.bouncingbullets.Main;
import com.matias.bouncingbullets.PlatformSpecific;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Main(new PlatformSpecific() {
			@Override
			public void hacerFoto() {
				System.out.println("Not supported");
			}
		}), config);
	}
}
