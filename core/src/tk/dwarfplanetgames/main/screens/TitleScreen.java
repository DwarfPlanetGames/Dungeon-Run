package tk.dwarfplanetgames.main.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TitleScreen extends InputAdapter implements Screen {
	private SpriteBatch batch;
	long oldTime, newTime;
	public static int time = 0;
	Texture img = new Texture("DPGamesLogo1.png");
	TextureRegion titleTex = new TextureRegion(new Texture("title.png"), 0, 0, 526, 121);
	TextureRegion block = new TextureRegion(new Texture("Texture_Spritesheet.png"), 0, 0, 32, 32);
	Texture menu = new Texture("TitleButtons.png");
	public static OrthographicCamera camera;
	public Music music;

	@Override
	public void show() {
		//((Game) Gdx.app.getApplicationListener()).setScreen(new PlayScreen());
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		oldTime = System.nanoTime();
		batch = new SpriteBatch();

		music = Gdx.audio.newMusic(Gdx.files.internal("title_music.wav"));
		music.setLooping(true);
		music.play();
	}

	@Override
	public void render(float delta) {
		int secToBegin = 2;
		batch.setProjectionMatrix(camera.combined);
		if (time < 60 * secToBegin) Gdx.gl.glClearColor(1, 1, 1, 1);
		else Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		if (time < 60 * secToBegin) {
			batch.draw(img, Gdx.graphics.getWidth() / 2f - img.getWidth() / 2, Gdx.graphics.getWidth() / 2f - img.getHeight());
		}
		newTime = System.nanoTime();
		if (newTime - oldTime > 1000000000 / 120.0) {
			oldTime = newTime;
			time++;
			if (time > 60 * secToBegin) {
				update();
			}
		}
		if (time > 60 * secToBegin) renderMenu();
		batch.end();
	}

	public void update() {

	}

	public void renderMenu() {
		for (int x = -1; x < Gdx.graphics.getWidth() / 64 + 2; x++) {
			for (int y = -1; y < Gdx.graphics.getHeight() / 64 + 2; y++) {
				batch.draw(block, x * 64, y * 64, 64, 64);
			}
		}
		if (Gdx.graphics.getHeight() >= 720) {
			batch.draw(titleTex, Gdx.graphics.getWidth() / 2f - titleTex.getRegionWidth() / 2f, Gdx.graphics.getHeight() / 2f + menu.getHeight() / 2f, titleTex.getRegionWidth() / 2f, titleTex.getRegionHeight() / 2f, titleTex.getRegionWidth(), titleTex.getRegionHeight(), (float) Math.sin(time / 120f) / 10f + 1.5f, 1.5f, (float) Math.cos(time / 140f) * 5f);
			batch.draw(menu, Gdx.graphics.getWidth() / 2f - menu.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - menu.getHeight() / 2f - titleTex.getRegionHeight() / 2f);
		} else {
			batch.draw(titleTex, Gdx.graphics.getWidth() / 2f - titleTex.getRegionWidth() / 2f, Gdx.graphics.getHeight() / 2f + menu.getHeight() / 2f - titleTex.getRegionHeight() * 0.75f, titleTex.getRegionWidth() / 2f, titleTex.getRegionHeight() / 2f, titleTex.getRegionWidth(), titleTex.getRegionHeight(), (float) Math.sin(time / 120f) / 10f + 1.5f, 1.5f, (float) Math.cos(time / 140f) * 5f);
			batch.draw(menu, Gdx.graphics.getWidth() / 2f - menu.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - menu.getHeight() / 2f - titleTex.getRegionHeight() / 2f);
		}
		if (Gdx.input.isTouched()) {
			int screenX = Gdx.input.getX();
			int screenY = Gdx.input.getY();
			screenY = Gdx.graphics.getHeight() - screenY;
			float menuY;
			if (Gdx.graphics.getHeight() >= 720) {
				menuY = Gdx.graphics.getHeight() / 2f - menu.getHeight() / 2f - titleTex.getRegionHeight() / 2f;
			} else {
				menuY = Gdx.graphics.getHeight() / 2f - menu.getHeight() / 2f - titleTex.getRegionHeight() / 2f;
			}
			if (screenX > Gdx.graphics.getWidth() / 2f - menu.getWidth() / 2f && screenX < Gdx.graphics.getWidth() / 2f - menu.getWidth() / 2f + menu.getWidth()) {
				if (screenY > menuY + 100 * 3) {
					music.stop();
					((Game) Gdx.app.getApplicationListener()).setScreen(new PlayScreen());
					System.out.println("STARTED PlayScreen");
				}
			}
		}
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		float menuY;
		if (Gdx.graphics.getHeight() >= 720) {
			menuY = Gdx.graphics.getHeight() / 2f - menu.getHeight() / 2f - titleTex.getRegionHeight() / 2f;
		} else {
			menuY = Gdx.graphics.getHeight() / 2f - menu.getHeight() / 2f - titleTex.getRegionHeight() / 2f;
		}
		if (screenX > Gdx.graphics.getWidth() / 2f - menu.getWidth() / 2f && screenX < Gdx.graphics.getWidth() / 2f - menu.getWidth() / 2f + menu.getWidth()) {
			if (screenY > menuY + 100 * 3) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new PlayScreen());
			}
		}
		return true;
	}

	@Override
	public void resize(int width, int height) {

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
		batch.dispose();
		menu.dispose();
		img.dispose();
		
		music.dispose();
	}

}
