package tk.dwarfplanetgames.main.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HelpScreen implements Screen {
	
	private SpriteBatch batch;
	private BitmapFont font;
	private static final String[] helps = new String[]{
		"How to play Dungeon Run",
		"",
		"So simple we didn't even know why we made a help screen.",
		"",
		"Controls:",
		"Tap - Jump",
		"",
		"That's it",
		"Good luck",
		"",
		"If you throw your phone against a wall, it is not our fault.",
		"Who was the one stupid enough to throw their phone anyway?",
	};
	
	@Override
	public void show() {
		batch = new SpriteBatch();
		font = new BitmapFont();
	}

	@Override
	public void render(float delta) {
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
