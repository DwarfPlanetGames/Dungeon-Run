package tk.dwarfplanetgames.main;

import tk.dwarfplanetgames.main.screens.TitleScreen;

import com.badlogic.gdx.Game;

public class MainGame extends Game {
	
	@Override
	public void create () {
		TitleScreen.setup();
		setScreen(new TitleScreen());
	}

	@Override
	public void render () {
		super.render();
	}
}
