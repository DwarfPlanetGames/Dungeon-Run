package tk.dwarfplanetgames.main.objects;

import java.awt.Graphics;
import java.util.LinkedList;

import tk.dwarfplanetgames.main.GameObject;
import tk.dwarfplanetgames.main.ObjectId;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class End extends GameObject{
	
	public static final TextureRegion tex = new TextureRegion(new Texture("Texture_Spritesheet.png"),4*32,0,32,32);
	
	public End(float x, float y) {
		super(x, y, ObjectId.End);
	}

	public void tick(LinkedList<GameObject> object) {
				
	}
	
	public void render(Graphics g) {
		/*g.setColor(Color.green);
		g.drawRect((int) x, (int) y, 32, 32);*/
	}

	
	public Rectangle getBounds() {

		return new Rectangle((int)x, (int)y, 32,32);
	}

	@Override
	public void render(SpriteBatch b) {
		b.draw(tex,x,y);
	}

}

