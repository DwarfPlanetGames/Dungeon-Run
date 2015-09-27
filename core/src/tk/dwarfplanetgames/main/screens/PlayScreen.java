package tk.dwarfplanetgames.main.screens;

import java.util.LinkedList;
import java.util.Random;

import tk.dwarfplanetgames.main.Handler;
import tk.dwarfplanetgames.main.objects.Block;
import tk.dwarfplanetgames.main.objects.End;
import tk.dwarfplanetgames.main.objects.FallingBlocks;
import tk.dwarfplanetgames.main.objects.Lava;
import tk.dwarfplanetgames.main.objects.Player;
import tk.dwarfplanetgames.main.objects.Spike;
import tk.dwarfplanetgames.main.powerups.Powerup;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayScreen implements Screen {
	
	SpriteBatch batch;
	public static Random rand = new Random();
	public static Pixmap overlay;
	public static OrthographicCamera camera;
	//TextureRegion img;
	long oldTime;
	long newTime;
	public static boolean update = true;
	public static int time = 0;
	public static Handler h;
	public static int playerX = 0;
	public static int playerY = 0;
	public static int levelId = 1 - 1;
	public static LinkedList<Powerup> powerups = new LinkedList<Powerup>();
	public static float X = 0;
	public static float Y = 0;
	public static Texture tex = new Texture("Texture_Spritesheet.png");
	public static TextureRegion gradient = new TextureRegion(new Texture("Gradient.png"),0,0,1,1);
	TextureRegion block = new TextureRegion(tex, 32, 0, 32, 32);
	public static final TextureRegion vignette = new TextureRegion(new Texture("game_vignette.png"));
	
	@Override
	public void show() {
		block.flip(false, true);
		camera = new OrthographicCamera(1080, 720);
		camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		oldTime = System.nanoTime();
		h = new Handler();
		
		levelUp();
		
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f,0);
		camera.update();
		batch = new SpriteBatch();
		update = true;
		//TitleScreen.music.stop();
	}
	
	public static void levelUp() {
		levelId++;
		if (update)
			LevelScreen.file.writeString(String.valueOf(levelId), false);
		try {
			Texture levelt = new Texture("levels/" + levelId + ".png");
			TextureData leveltd = levelt.getTextureData();
			leveltd.prepare();
			Pixmap level = leveltd.consumePixmap();
			loadImageLevel(level);
			levelt.dispose();
			leveltd.disposePixmap();
			level.dispose();
		} catch (Exception e) {
			((Game) Gdx.app.getApplicationListener()).setScreen(new TitleScreen());
		}
	}
	
	public void render(SpriteBatch batch, OrthographicCamera camera) {
		camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//camera.update();
		camera.position.set(playerX, playerY,0);
		camera.update();
		//batch.setProjectionMatrix(camera.combined);
		float dist = 2f;
		for (int x = -1; x < Gdx.graphics.getWidth() / 64f + 2; x++) {
			for (int y = -1; y < Gdx.graphics.getHeight() / 64f + 2; y++) {
				batch.draw(block, (int)(x * 64 /*- Gdx.graphics.getWidth() / 2f*/ - (camera.position.x / dist) % 64), (int)(y * 64 /*- Gdx.graphics.getHeight() / 2f*/ - (camera.position.y / dist) % 64), 64,64);
			}
		}
		Handler.qRender(batch,camera);
	}
	
	public void itemTap(int itemInt) {
		if (itemInt > powerups.size())
			return;
		
	}

	@Override
	public void render(float delta) {
		if (Gdx.input.isTouched() && Gdx.input.getY() <= 64) {
			itemTap((int)(Gdx.input.getX() / 64.0));
		}
		int secToBegin = 0;
		batch.setProjectionMatrix(camera.combined);
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		newTime = System.nanoTime();
		if (newTime - oldTime > 1000000000 / 30.0) {
			oldTime = newTime;
			time++;
			if (time > 60*secToBegin) {
				if (update)
					update();
				camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
				camera.position.set(playerX + camera.viewportWidth / 2f, playerY,0);
				camera.update();
				batch.setProjectionMatrix(camera.combined);
			}
		}
		float dist = 1.25f;
		if (time > 60*secToBegin) {
			for (int x = -1; x < Gdx.graphics.getWidth() / 64f + 2; x++) {
				for (int y = -1; y < Gdx.graphics.getHeight() / 64f + 2; y++) {
					batch.draw(block, (int)(x * 64 - Gdx.graphics.getWidth() / 2f + camera.position.x - (camera.position.x / dist) % 64), (int)(y * 64 - Gdx.graphics.getHeight() / 2f + camera.position.y - (camera.position.y / dist) % 64), 64,64);
				}
			}
			gradient.setRegion(150, 0, 1, 1);
			batch.draw(gradient, camera.position.x, camera.position.y, 0.5f, 0.5f, 1, 1, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 0);
			Handler.render(batch);
		}
		//batch.draw(vignette, camera.position.x - camera.viewportWidth/2f, camera.position.y - camera.viewportHeight/2f, vignette.getRegionWidth()/2f, vignette.getRegionHeight()/2f, camera.viewportWidth, camera.viewportHeight, 1, 1, 0);
		batch.end();
	}
	
	public void update() {
		Handler.tick();
	}
	
	public static int getTime() {
		return time;
	}
	
	private static void loadImageLevel(Pixmap image){
		Handler.objects.clear();
		int w = image.getWidth();
		int h = image.getHeight();
		
		
		for(int xx = 0; xx < h; xx++){
			for(int yy = 0; yy < w; yy++){
				int pixel = image.getPixel(xx, yy);
				
				if(pixel == 0xffffffff){
					Handler.addObject(new Block(xx*32, yy*32,0));
				}
				if(pixel == 0x0000ffff){	
					Handler.addObject(new Player(xx*32, yy*32));
					playerX = xx*32;
					playerY = yy*32;
					System.out.println("Add Player at :" + xx*32 + " x " + yy*32);
				}
				if(pixel == 0xff0000ff){
					Handler.addObject(new Lava(xx*32, yy*32,2));
				}
				if(pixel == 0xffff00ff){
					Handler.addObject(new Spike(xx*32, yy*32,2));
				}
				if(pixel == 0x00ff00ff){
					Handler.addObject(new End(xx*32,yy*32));
				}
				if(pixel == 0x555353ff){
					Handler.addObject(new FallingBlocks((float)xx*32,(float)yy*32,3));
				}
			}
		}
		System.out.println("Done loading level!!!");
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
