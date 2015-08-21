package tk.dwarfplanetgames.main.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;

public class LevelScreen implements Screen {
	
	public final Texture tex = new Texture("levels.png");
	private final Texture vignette = new Texture("Vignette.png");
	public static final Texture gradient = new Texture("Gradient.png");
	public static FileHandle file;
	public float x = 0f;
	public SpriteBatch batch;
	FirstPersonCameraController c;
	public boolean dragging = false;
	public boolean isTouching = false;
	public int X, Y;
	public float texX = 0, texY = 0;
	private int maxLev = 1;
	
	public void show() {
		x = 0f;
		batch = new SpriteBatch();
		file = Gdx.files.local("level.txt");
		if (!file.exists()) {
			file.writeString("1", false);
		} else {
			maxLev = Integer.parseInt(file.readString());
		}
	}
	
	public void setMaxLev(int i) {
		maxLev = i;
		file.writeString(String.valueOf(i), false);
	}
	
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		
		if (texX < -tex.getWidth() + Gdx.graphics.getWidth()) texX = -tex.getWidth() + Gdx.graphics.getWidth();
		if (texX > 0) texX = 0;
		if (texY < -tex.getHeight() + Gdx.graphics.getHeight()) texY = -tex.getHeight() + Gdx.graphics.getHeight();
		if (texY > 0) texY = 0;
		if (Gdx.graphics.getHeight() > tex.getHeight()) {
			texY = Gdx.graphics.getHeight() / 2 - tex.getHeight() / 2;
		}
		batch.draw(tex, texX, texY);
		if (isTouching && !dragging) {
			PlayScreen.gradient.setRegion(100,0,1,1);
			batch.draw(PlayScreen.gradient, (int)((Gdx.input.getX() - texX) / 100f) * 100 + texX, texY, 0, 0, 1, 1, 100, tex.getHeight(), 0);
		}
		
		for (int i = 0; i < 30; i++) {
			if (i <= maxLev) {
				
			} else if (i == maxLev+1) {
				batch.draw(gradient,i*100+texX,0,100,Gdx.graphics.getHeight());
			} else {
				PlayScreen.gradient.setRegion(255,0,1,1);
				batch.draw(PlayScreen.gradient, i *100 + texX, texY, 0, 0, 1, 1, 100, tex.getHeight(), 0);
			}
		}
		
		batch.draw(vignette,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		
		batch.end();
		
		if (Gdx.input.isTouched()) {
			if (!isTouching) {
				isTouching = true;
				touchDown(Gdx.input.getX(), Gdx.input.getY());
			}
			int thresh = 5;
			if (Gdx.input.getX() < X - thresh || Gdx.input.getX() > X + thresh || Gdx.input.getY() < Y - thresh || Gdx.input.getY() > Y + thresh) {
				touchDragged(Gdx.input.getDeltaX(),-Gdx.input.getDeltaY());
			}
		}
		if (!Gdx.input.isTouched()) {
			if (isTouching) {
				isTouching = false;
				touchUp(Gdx.input.getX(), Gdx.input.getY());
			}
		}
	}
	
	public void touchDown(int x, int y) {
		X = x;
		Y = y;
	}
	
	public void touchUp(int x, int y) {
		if (!dragging) {
			int i =  (int)((x-this.x)/100.0);
			if (i <= maxLev) {
				PlayScreen.levelId = i;
				((Game) Gdx.app.getApplicationListener()).setScreen(new PlayScreen());
			}
		}
		dragging = false;
	}
	
	public void touchDragged(float deltaX, float deltaY) {
		dragging = true;
		texX += deltaX; texY += deltaY;
	}
	
	public void dispose() {
		
	}
	
	public void resize(int width, int height){}
	public void pause(){}
	public void resume(){}
	public void hide(){}
	
}
