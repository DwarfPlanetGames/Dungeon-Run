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

import tk.dwarfplanetgames.main.ui.Actor;
import tk.dwarfplanetgames.main.ui.Stage;

public class CreditsScreen implements Screen {
	private SpriteBatch batch;
	private BitmapFont font;
	public Actor back;
	public Stage stage;
	public final Texture vignette = new Texture("Vignette.png");
	TextureRegion block = new TextureRegion(new Texture(
			"Texture_Spritesheet.png"), 0, 0, 32, 32);
	private static final String creditNames[] = new String[] {
			"Project Director and Lead Programmer:", "       Austin White", "",
			"", "", "", "", "", "", "Programmers:", "       Brandon Dyer",
			"       Ian Green" };
	public static float creditanmin = 0;

	public void dispose() {
		batch.dispose();
		font.dispose();
	}

	@Override
	public void render(float delta) {
		creditanmin += delta * 75;
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		batch.begin();
		// font.draw(batch, "Hello World", 200, 200);
		for (int x = -1; x < Gdx.graphics.getWidth() / 64f + 2; x++) {
			for (int y = -1; y < Gdx.graphics.getHeight() / 64f + 2; y++) {
				batch.draw(block, (int) (x * 64), (int) (y * 64), 64, 64);
			}
		}
		for (int i = 0; i < creditNames.length; i++) {
			font.draw(batch, creditNames[i], Gdx.graphics.getWidth() / 2 - 100,
					Gdx.graphics.getHeight() / 2 - i * 75 + creditanmin);
		}
		batch.draw(vignette, 0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		stage.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		stage = new Stage(new Texture(Gdx.files.internal("skin.png")));
		font = new BitmapFont(Gdx.files.internal("default.fnt"));
		font.setColor(Color.WHITE);
		font.getData().setScale(
				((float) Gdx.graphics.getHeight() / 720f) * 1.1f);

		back = new Actor(0, Gdx.graphics.getHeight() - 64, 64, 64) {
			Texture tex = new Texture("back.png");

			@Override
			public void touchDown(int localX, int localY) {
				((Game) Gdx.app.getApplicationListener())
						.setScreen(new TitleScreen());
			}

			@Override
			public void draw(SpriteBatch batch, Texture skin) {
				batch.draw(tex, x, y);
			}
		};

		stage.addActor(back);
	}

	@Override
	public void hide() {

	}
}