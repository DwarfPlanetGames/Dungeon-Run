package tk.dwarfplanetgames.main;

import java.util.LinkedList;

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
	
	public ObjectId getId() {
		return id;
	}
	
}
