package tk.dwarfplanetgames.main;

import tk.dwarfplanetgames.main.objects.Block;
import tk.dwarfplanetgames.main.objects.End;
import tk.dwarfplanetgames.main.objects.FallingBlocks;
import tk.dwarfplanetgames.main.objects.Lava;
import tk.dwarfplanetgames.main.objects.Player;
import tk.dwarfplanetgames.main.screens.TitleScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MainGame extends Game {
	SpriteBatch batch;
	public static OrthographicCamera camera;
	TextureRegion img;
	long oldTime;
	long newTime;
	public static int time = 0;
	public static Handler h;
	public static int playerX = 0;
	public static int playerY = 0;
	
	@Override
	public void create () {
		setScreen(new TitleScreen());
		/*camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Texture tex = new Texture("DPGamesLogo1.png");
		img = new TextureRegion(tex, tex.getWidth(), tex.getHeight());
		img.flip(false, true);
		oldTime = System.nanoTime();
		h = new Handler();
		Texture levelt = new Texture("Level-1.png");
		TextureData leveltd = levelt.getTextureData();
		leveltd.prepare();
		Pixmap level = leveltd.consumePixmap();
		loadImageLevel(level);
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f,0);
		camera.update();
		batch = new SpriteBatch();*/
	}

	@Override
	public void render () {
		super.render();
		/*int secToBegin = 2;
		batch.setProjectionMatrix(camera.combined);
		if (time < 60*secToBegin)
			Gdx.gl.glClearColor(1, 1, 1, 1);
		else
			Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		if (time < 60*secToBegin) {
			batch.draw(img, camera.viewportWidth / 2 - img.getRegionWidth() / 2, camera.viewportHeight / 2 - img.getRegionHeight() / 2);
		}
		newTime = System.nanoTime();
		if (newTime - oldTime > 1000000000 / 120.0) {
			oldTime = newTime;
			time++;
			if (time > 60*secToBegin) {
				update();
				camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
				camera.position.set(playerX + camera.viewportWidth / 2f, playerY,0);
				camera.update();
			}
		}
		if (time > 60*secToBegin)
			h.render(batch);
		batch.end();*/
	}
	
	public void update() {
		h.tick();
	}
	
	public static int getTime() {
		return time;
	}
	
	private void loadImageLevel(Pixmap image){
		Handler handler = h;
		int w = image.getWidth();
		int h = image.getHeight();
		
		
		for(int xx = 0; xx < h; xx++){
			for(int yy = 0; yy < w; yy++){
				int pixel = image.getPixel(xx, yy);
				
				if(pixel == 0xffffffff){
					handler.addObject(new Block(xx*32, yy*32,0));
				}
				if(pixel == 0x0000ffff){	
					handler.addObject(new Player(xx*32, yy*32,handler));
					System.out.println("Add Player at :" + xx*32 + " x " + yy*32);
				}
				if(pixel == 0xff0000ff){
					handler.addObject(new Lava(xx*32, yy*32,2));
				}
				if(pixel == 0x00ff00ff){
					handler.addObject(new End(xx*32,yy*32));
				}
				if(pixel == 0x555353ff){
					handler.addObject(new FallingBlocks((float)xx*32,(float)yy*32,3));
				}
			}
		}
		System.out.println("Done loading level!!!");
	}
}
