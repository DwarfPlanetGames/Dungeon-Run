package com.Drawfplanet.dungeon.Objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.Drawfplanet.dungeon.framework.GameObject;
import com.Drawfplanet.dungeon.framework.ObjectId;
import com.Drawfplanet.dungeon.framework.Texture;
import com.Drawfplanet.dungeon.window.Game;

public class Block extends GameObject{
	
	int falling = 0;
	
	
	Texture tex = Game.getInstance();
	
	private int type;
	public Block(float x, float y,int type, ObjectId id) {
		super(x, y, id);
		this.type =type;
	}

	public void tick(LinkedList<GameObject> object) {
				
	}
	
	
	
	public void render(Graphics g) {
		if(type == 0)
			g.drawImage(tex.block[0],(int)x,(int)y,null);
		
	}
	
	

	
	public Rectangle getBounds() {

		return new Rectangle((int)x, (int)y, 32,32);
	}

}
