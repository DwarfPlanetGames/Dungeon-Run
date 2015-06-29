package tk.dwarfplanetgames.main.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TitleScreen extends InputAdapter implements Screen {
	private SpriteBatch batch;
	long oldTime, newTime;
	public static int time = 0;
	Texture img = new Texture("DPGamesLogo1.png");
	public static OrthographicCamera camera;
	Pixmap map;
	Texture menu;
	
	@Override
	public void show() {
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		map = new Pixmap(300,400,Pixmap.Format.RGBA4444);
		menu = new Texture(map);
		oldTime = System.nanoTime();
		batch = new SpriteBatch();
	}
	
	@Override
	public void render(float delta) {
		int secToBegin = 2;
		batch.setProjectionMatrix(camera.combined);
		if (time < 60*secToBegin)
			Gdx.gl.glClearColor(1, 1, 1, 1);
		else
			Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		if (time < 60*secToBegin) {
			batch.draw(img, Gdx.graphics.getWidth() / 2f - img.getWidth() / 2, Gdx.graphics.getWidth() / 2f - img.getHeight());
		}
		newTime = System.nanoTime();
		if (newTime - oldTime > 1000000000 / 120.0) {
			oldTime = newTime;
			time++;
			if (time > 60*secToBegin) {
				update();
			}
		}
		if (time > 60*secToBegin)
			renderMenu();
		batch.end();
	}
	
	public void update() {
		
	}
	
	public void renderMenu() {
		batch.draw(menu, Gdx.graphics.getWidth() / 2f - menu.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - menu.getHeight() / 2f);
		
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
		// TODO Auto-generated method stub
		
	}
	
	
	
}
