package tk.dwarfplanetgames.main.objects;


import java.awt.Graphics;
import java.util.LinkedList;

import tk.dwarfplanetgames.main.GameObject;
import tk.dwarfplanetgames.main.ObjectId;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;


public class FallingBlocks extends GameObject{
		
	private float gravity = 7f;
		
	public static final TextureRegion tex = new TextureRegion(new Texture("Texture_Spritesheet.png"),32*3,0,32,32);
		
	
	private int type;
	public FallingBlocks(float x, float y,int type) {
		super(x, y, ObjectId.FallingBlocks);
		this.type =type;
		tex.flip(false, true);
		
	}
	
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
	
			//turns gravity on for the falling blocks
		if(falling_block){
			y += gravity;
		}
		
	
	}
	
	
	
	public void render(Graphics g) {
		/*if(type == 3)
			g.drawImage(tex.block[3],(int)x,(int)y,null);
		*/
		
	}
	
	public void setFallingBlock(boolean fall) {
		falling_block = true;
	}

	
	public Rectangle getBounds() {

		return new Rectangle((int)x, (int)y, 32,32);
	}

	@Override
	public void render(SpriteBatch b) {
		if(type == 3)
		b.draw(tex,x,y);
	}



}
