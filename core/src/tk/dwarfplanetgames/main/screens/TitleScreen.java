package tk.dwarfplanetgames.main.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TitleScreen implements Screen {
	private SpriteBatch batch;
	private Texture img;
	long oldTime, newTime;
	public static int time = 0;
	Texture tex = new Texture("DPGamesLogo1.png");
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render(float delta) {
		int secToBegin = 2;
		if (time < 60*secToBegin)
			Gdx.gl.glClearColor(1, 1, 1, 1);
		else
			Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		if (time < 60*secToBegin) {
			batch.draw(img, Gdx.graphics.getWidth() / 2 - img.getWidth() / 2, Gdx.graphics.getWidth() / 2 - img.getHeight() / 2);
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
