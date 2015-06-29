package tk.dwarfplanetgames.main.objects;

import java.awt.Graphics;
import java.util.LinkedList;

import tk.dwarfplanetgames.main.GameObject;
import tk.dwarfplanetgames.main.ObjectId;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Block extends GameObject{
	
	int falling = 0;
	
	public static final TextureRegion tex = new TextureRegion(new Texture("Texture_Spritesheet.png"),0,0,32,32);
	
	private int type;
	public Block(float x, float y,int type) {
		super(x, y, ObjectId.Block);
		this.type =type;
		tex.flip(false, true);
	}

	public void tick(LinkedList<GameObject> object) {
				
	}
	
	
	
	public void render(Graphics g) {
		/*if(type == 0)
			g.drawImage(tex.block[0],(int)x,(int)y,null);
		*/
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32,32);
	}

	@Override
	public void render(SpriteBatch b) {
		if(type == 0) {
			b.draw(tex,x,y);
		}
	}

}
