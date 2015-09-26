package tk.dwarfplanetgames.main.objects;

import java.awt.Graphics;
import java.util.LinkedList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import tk.dwarfplanetgames.main.GameObject;
import tk.dwarfplanetgames.main.ObjectId;

public class Spike extends GameObject {

	public static TextureRegion tex = new TextureRegion(new Texture("Texture_Spritesheet.png"),0,32,32,32);
		
	public Spike(float x, float y,int type) {
		super(x, y, ObjectId.Lava);
	}

	public void tick(LinkedList<GameObject> object) {
				
	}
	
	public void render(Graphics g) {
		/*if(type == 2)
			g.drawImage(tex.block[2], (int)x,(int) y, null);*/
	}

	
	public Rectangle getBounds() {

		return new Rectangle((int)x, (int)y, 32,32);
	}

	@Override
	public void render(SpriteBatch b) {
		b.draw(tex,x,y);
	}
	
	@Override
	public void qRender(SpriteBatch b) {
		b.draw(tex,x,y);
	}
}
