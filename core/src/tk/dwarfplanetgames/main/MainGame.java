package tk.dwarfplanetgames.main;

import tk.dwarfplanetgames.main.screens.TitleScreen;

import com.badlogic.gdx.Game;

public class MainGame extends Game {
	
	@Override
	public void create () {
		// Setup title screen
		TitleScreen.setup();
		// Set the screen to the title screen
		setScreen(new TitleScreen());
	}

	@Override
	public void render () {
		// Render the current screen
		super.render();
	}
}
