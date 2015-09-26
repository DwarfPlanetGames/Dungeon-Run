package tk.dwarfplanetgames.main.screens;

import tk.dwarfplanetgames.main.objects.Player;
import tk.dwarfplanetgames.main.objects.Spike;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TitleScreen extends InputAdapter implements Screen {
	
	// Declare render batch
	private SpriteBatch batch;
	// Declare timing longs for game loop
	long oldTime, newTime;
	// Initialize time for total time since game started
	public static int time = 0;
	// Initialize Textures and texture regions
	Texture img = new Texture("DPGamesLogo1.png");
	TextureRegion titleTex = new TextureRegion(new Texture("title-beta.png"), 0, 0, 526, 121);
	TextureRegion block = new TextureRegion(new Texture("Texture_Spritesheet.png"), 32, 0, 32, 32);
	Texture menu = new Texture("TitleButtons.png");
	public static final Texture vignette = new Texture("Vignette.png");
	// Declare camera
	public static OrthographicCamera camera;
	// Initialize music and volume
	public static Music music = Gdx.audio.newMusic(Gdx.files.internal("music/0.mp3"));
	public static final float musicVol = 0.6f;
	// Initialize the horizontal location of the player
	public static int playerX = 0;
	// Declare the screen used for rendering the main game
	public PlayScreen playScreen;

	@Override
	public void show() {
		// Flip textures
		Spike.tex.flip(false, true);
		// Initialize and setup the camera
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		// Set oldTime to the starting time for the game loop
		oldTime = System.nanoTime();
		// Initialize the batch for rendering
		batch = new SpriteBatch();
		// Set the game screen to level 1
		PlayScreen.levelId = 0;
		// Set the game screen to not update while the title screen is not shown
		PlayScreen.update = false;
		// Load the first level
		PlayScreen.levelUp();
		// Initialize the game screen
		playScreen = new PlayScreen();
		// Show the game screen
		playScreen.show(); 
		// Update the game screen to set the location of the camera to the player
		playScreen.update();
		// Move game camera slightly up
		PlayScreen.playerY += Gdx.graphics.getHeight() / 8f;
		// Flip the player texture
		Player.tex.flip(false, true);
		// If the music isn't playing and isn't muted begin playing the music
		if (!music.isPlaying() && !OptionsScreen.muted) {
			music.play();
		}
	}
	
	public static void setup() {
		// Setup the music
		music.setLooping(true);
		music.setVolume(musicVol);
		//music.play();
	}
	
	public static void playMusic() {
		// Start music
		music.play();
	}

	@Override
	public void render(float delta) {
		// Restart the game if the 'H' key is pressed
		if (Gdx.input.isKeyJustPressed(Keys.H)) {
			time = 0;
			music.setPosition(0);
		}
		// Set the number of seconds that the splash screen is shown
		int secToBegin = 2;
		// Set the render batche's projection matrix to that of the camera
		batch.setProjectionMatrix(camera.combined);
		// If the game time is greater than that of the seconds to begin then clear the screen to a white color
		if (time < 60 * secToBegin) Gdx.gl.glClearColor(1, 1, 1, 1);
		// If not then clear the screen to a gray color
		else Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 0);
		// Clear the screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// Start rendering
		batch.begin();
		// If game time is less than the splash screen show time then render the splash screen
		if (time < 60 * secToBegin) {
			batch.draw(img, Gdx.graphics.getWidth() / 2f - img.getWidth() / 2, Gdx.graphics.getWidth() / 2f - img.getHeight());
		}
		// set the new time to the current time
		newTime = System.nanoTime();
		// If the current time minus the old time is greater than a sixtieth of a second then tick the game
		if (newTime - oldTime > 1000000000 / 120.0) {
			// Set the old time variable to the current time
			oldTime = newTime;
			// update the game time
			time++;
			// If the splash screen is not showing then update the game
			if (time > 60 * secToBegin) {
				// Update the game
				update();
				// Set camera location to the center of the screen
				camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f,0);
				camera.update();
			}
		}
	
		if (time > 60 * secToBegin) {
			//playScreen.render(batch,camera);
			//batch.setProjectionMatrix(camera.combined);
			camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			camera.position.set(playerX + camera.viewportWidth / 2f, camera.viewportHeight / 2f,0);
			camera.update();
			for (int x = -1; x < Gdx.graphics.getWidth() / 64f + 2; x++) {
				for (int y = -1; y < Gdx.graphics.getHeight() / 64f + 2; y++) {
					batch.draw(block, (int)(x * 64), (int)(y * 64 + (time * 0.90f)%64), 64,64);
				}
			}
			batch.draw(vignette,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
			renderMenu();
		}
		
		batch.end();
		
	}

	public void update() {
		PlayScreen.playerX += 1;
	}

	public void renderMenu() {
		/*for (int x = -1; x < Gdx.graphics.getWidth() / 64f + 2; x++) {
			for (int y = -1; y < Gdx.graphics.getHeight() / 64f + 2; y++) {
				batch.draw(block, (int)(x * 64 - Gdx.graphics.getWidth() / 2f + camera.position.x - (camera.position.x / 2f) % 64), (int)(y * 64 - Gdx.graphics.getHeight() / 2f + camera.position.y - (camera.position.y / 2f) % 64), 64,64);
			}
		}*/
		//Handler.render(batch);
		
		if (Gdx.graphics.getHeight() >= 720) {
			batch.draw(titleTex, Gdx.graphics.getWidth() / 2f - titleTex.getRegionWidth() / 2f, Gdx.graphics.getHeight() / 2f + menu.getHeight() / 2f, titleTex.getRegionWidth() / 2f, titleTex.getRegionHeight() / 2f, titleTex.getRegionWidth(), titleTex.getRegionHeight(), (float) Math.sin(time / 120f) / 10f + 1.5f, (float) Math.sin(time / 120f) / 10f + 1.5f, (float) Math.cos(time / 140f) * 5f);
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
					//music.stop();
					//((Game) Gdx.app.getApplicationListener()).setScreen(new PlayScreen());
					((Game) Gdx.app.getApplicationListener()).setScreen(new LevelScreen());
				} else if (screenY > menuY + 100 * 2) {
					((Game) Gdx.app.getApplicationListener()).setScreen(new HelpScreen());
				} else if (screenY > menuY + 100 * 1) {
					((Game) Gdx.app.getApplicationListener()).setScreen(new OptionsScreen());
				} else if (screenY > menuY + 100 * 0) {
					((Game) Gdx.app.getApplicationListener()).setScreen(new CreditsScreen());
				}
			}
		}
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
		batch.dispose();
		menu.dispose();
		img.dispose();
		
		//music.dispose();
	}

}
