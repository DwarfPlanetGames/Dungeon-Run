package tk.dwarfplanetgames.main.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Actor {
	
	public int x = 0, y = 0;
	public int width = 1, height = 1;
	protected boolean isTouching = false;
	protected int X = 0, Y = 0;
	protected int drag = 5;
	
	public Actor(int x, int y, int width, int height) {
		this.x = x; this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void act(float delta) {
		boolean isTouched = Gdx.input.isTouched();
		int getX = Gdx.input.getX();
		int getY = Gdx.graphics.getHeight() - Gdx.input.getY();
		if (isTouched) {
			if (!isTouching && getX >= x && getX <= x + width && getY >= y && getY <= y + height) {
				isTouching = true;
				X = getX - x;
				Y = getY - y;
				touchDown(getX - x, getY - y);
			}
			int thresh = drag;
			if (getX < X - thresh || getX > X + thresh || getY < Y - thresh ||getY > Y + thresh) {
				touchDragged(Gdx.input.getDeltaX(),-Gdx.input.getDeltaY());
			}
			if (!(getX >= x && getX <= x + width && getY >= y && getY <= y + height)) {
				if (isTouching) {
					isTouching = false;
					touchUp(X,Y);
				}
			}
		}
		if (!isTouched) {
			if (isTouching) {
				isTouching = false;
				touchUp(getX, getY);
			}
		}
	}
	
	public void draw(SpriteBatch batch, Texture skin) {
		
	}
	
	public void touchDown(int localX, int localY) {
		
	}
	
	public void touchUp(int localX, int localY) {
		
	}
	
	public void touchDragged(int deltaX, int deltaY) {
		
	}
	
}
