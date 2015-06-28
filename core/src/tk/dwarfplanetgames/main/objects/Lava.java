package tk.dwarfplanetgames.main.objects;

import java.awt.Graphics;
import java.util.LinkedList;

import tk.dwarfplanetgames.main.GameObject;
import tk.dwarfplanetgames.main.ObjectId;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Lava extends GameObject{

	public static final TextureRegion tex = new TextureRegion(new Texture("Texture_Spritesheet.png"),32*2,0,32,32);
	private int type;
		
	public Lava(float x, float y,int type) {
		super(x, y, ObjectId.Lava);
		this.type = type;
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
		if(type == 2)
			b.draw(tex,x,y);
	}

}
