package tk.dwarfplanetgames.main.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import tk.dwarfplanetgames.main.MainGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Dungeon Run - Beta";
		config.vSyncEnabled = true;
		config.width = 1280;
		config.height = 720;
		config.samples = 1;
		config.resizable = false;
		new LwjglApplication(new MainGame(), config);
	}
}
