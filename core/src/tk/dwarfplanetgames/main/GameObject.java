package tk.dwarfplanetgames.main;

import java.util.LinkedList;

import tk.dwarfplanetgames.main.screens.PlayScreen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class GameObject {
	
	public float x, y;
	public float xo, yo;
	public ObjectId id;
	public float velX = 0, velY =0;
	public boolean falling= true;
	public boolean jumping = false;
	public boolean falling_block = false;
	public boolean reset = false;
	public float width, height;
	public int time = 0;
	
	public GameObject(float x, float y, ObjectId id){
		this.x = x;
		this.y = y;
		this.id = id;
		xo = x;
		yo = y;
		
	}
	
	public abstract void tick(LinkedList<GameObject> object);
	public abstract void render(SpriteBatch b);
	public abstract Rectangle getBounds();
	
	public int getDistToPlayer() {
		return (int)(Math.sqrt(Math.pow(x - PlayScreen.X, 2) + Math.pow(y - PlayScreen.Y, 2)));
	}
	
	public void qRender(SpriteBatch b) {
		int dist = getDistToPlayer();
		int num = 2;
		if (dist/num < 255)
			render(b);
		PlayScreen.gradient.setRegion(dist/num <= 255 ? dist/num : 255,0,1,1);
		b.draw(PlayScreen.gradient, (int)x, (int)y, 0, 0, 1, 1, 32, 32, 0);
	}
	
	public ObjectId getId() {
		return id;
	}
	
}
