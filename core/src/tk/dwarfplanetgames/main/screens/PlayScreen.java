package tk.dwarfplanetgames.main.screens;

import tk.dwarfplanetgames.main.Handler;
import tk.dwarfplanetgames.main.objects.Block;
import tk.dwarfplanetgames.main.objects.End;
import tk.dwarfplanetgames.main.objects.FallingBlocks;
import tk.dwarfplanetgames.main.objects.Lava;
import tk.dwarfplanetgames.main.objects.Player;

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
	public static OrthographicCamera camera;
	//TextureRegion img;
	long oldTime;
	long newTime;
	public boolean update = true;
	public static int time = 0;
	public static Handler h;
	public static int playerX = 0;
	public static int playerY = 0;
	public static int levelId = 1;
	TextureRegion block = new TextureRegion(new Texture("Texture_Spritesheet.png"), 32, 0, 32, 32);
	
	@Override
	public void show() {
		block.flip(false, true);
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		oldTime = System.nanoTime();
		h = new Handler();
		
		Texture levelt = new Texture("levels/1.png");
		
		TextureData leveltd = levelt.getTextureData();
		leveltd.prepare();
		Pixmap level = leveltd.consumePixmap();
		loadImageLevel(level);
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f,0);
		camera.update();
		batch = new SpriteBatch();
		update = true;
	}
	
	public static void levelUp() {
		levelId++;
		Texture levelt = new Texture("levels/" + levelId + ".png");
		TextureData leveltd = levelt.getTextureData();
		leveltd.prepare();
		Pixmap level = leveltd.consumePixmap();
		loadImageLevel(level);
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

	@Override
	public void render(float delta) {
		int secToBegin = 2;
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
			Handler.render(batch);
		}
		batch.end();
	}
	
	public void update() {
		Handler.tick();
	}
	
	public static int getTime() {
		return time;
	}
	
	private static void loadImageLevel(Pixmap image){
		Handler.object.clear();
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
