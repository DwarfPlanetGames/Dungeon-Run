package tk.dwarfplanetgames.main.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;

public class OptionsScreen implements Screen {

	TextureRegion block = new TextureRegion(new Texture("Texture_Spritesheet.png"), 32, 0, 32, 32);
	private SpriteBatch batch;
	public Stage stage;
	public static Skin skin;
	
	public static int volumed = 100;
	public Slider volume;
	public static boolean muted = false;
	public CheckBox mute;
	public static boolean fancyGraphicsd = true;
	public CheckBox fancyGraphics;

	@Override
	public void show() {
		skin = new Skin(Gdx.files.internal("uiskin.json"), new TextureAtlas("default.png"));
		batch = new SpriteBatch();
		stage = new Stage();
		
		volume = new Slider(0, 100, 1.0f, false, skin, "Volume");
		volume.setValue(OptionsScreen.volumed);
		
		mute = new CheckBox("Mute", skin);
		mute.setChecked(OptionsScreen.muted);
		
		fancyGraphics = new CheckBox("Pretty Stuff", skin);
		fancyGraphics.setChecked(OptionsScreen.fancyGraphicsd);
		
		stage.addActor(volume);
		stage.addActor(mute);
		stage.addActor(fancyGraphics);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		for (int x = -1; x < Gdx.graphics.getWidth() / 64f + 2; x++) {
			for (int y = -1; y < Gdx.graphics.getHeight() / 64f + 2; y++) {
				batch.draw(block, (int) (x * 64), (int) (y * 64), 64, 64);
			}
		}
		stage.act(delta);
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
	public void hide() {

	}

	@Override
	public void dispose() {
		volumed = (int)volume.getValue();
		muted = mute.isChecked();
		fancyGraphicsd = fancyGraphics.isChecked();
	}

}
