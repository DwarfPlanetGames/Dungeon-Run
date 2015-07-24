package tk.dwarfplanetgames.main.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HelpScreen implements Screen {
	
	private SpriteBatch batch;
	private BitmapFont font;
	TextureRegion block = new TextureRegion(new Texture("Texture_Spritesheet.png"), 32, 0, 32, 32);
	private static final String[] helps = new String[]{
		"How to play Dungeon Run",
		"",
		"So simple we don't know why we ever made a help screen.",
		"",
		"Controls:",
		"Tap - Jump",
		"",
		"That's it",
		"Good luck",
		"",
		"If you throw your phone at a wall, it is not our fault.",
		"Who was the one stupid enough to throw their phone anyway?",
	};
	
	@Override
	public void show() {
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("default.fnt"));
		font.getData().setScale(1f/*((float)Gdx.graphics.getHeight() / 720f) * 2.5f*/);
		font.setColor(Color.WHITE);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		for (int x = -1; x < Gdx.graphics.getWidth() / 64f + 2; x++) {
			for (int y = -1; y < Gdx.graphics.getHeight() / 64f + 2; y++) {
				batch.draw(block, (int)(x * 64), (int)(y * 64), 64,64);
			}
		}
		
		batch.draw(TitleScreen.vignette, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		font.setColor(Color.BLACK);
		for (int i = 0; i < helps.length; i++) {
			font.draw(batch, helps[i], 65, Gdx.graphics.getHeight() - (i * 12*4 + 13));
		}
		font.setColor(Color.WHITE);
		for (int i = 0; i < helps.length; i++) {
			font.draw(batch, helps[i], 64, Gdx.graphics.getHeight() - (i * 12*4 + 12));
		}
		batch.end();
		
		if (Gdx.input.isTouched()) {
			((Game) Gdx.app.getApplicationListener()).setScreen(new TitleScreen());
		}
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
