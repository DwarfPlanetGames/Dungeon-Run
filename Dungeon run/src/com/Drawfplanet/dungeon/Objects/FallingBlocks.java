package com.Drawfplanet.dungeon.Objects;


	import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.Drawfplanet.dungeon.framework.GameObject;
import com.Drawfplanet.dungeon.framework.ObjectId;
import com.Drawfplanet.dungeon.framework.Texture;
import com.Drawfplanet.dungeon.window.Game;


	public class FallingBlocks extends GameObject{
		
		private float gravity = 7f;
		
		Texture tex = Game.getInstance();
		
		
		private int type;
		public FallingBlocks(float x, float y,int type, ObjectId id) {
			super(x, y, id);
			this.type =type;
		
			
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
			if(type == 3)
				g.drawImage(tex.block[3],(int)x,(int)y,null);
			
			
		}

		
		public Rectangle getBounds() {

			return new Rectangle((int)x, (int)y, 32,32);
		}

	

}
