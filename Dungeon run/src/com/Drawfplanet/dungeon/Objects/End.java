package com.Drawfplanet.dungeon.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.Drawfplanet.dungeon.framework.GameObject;
import com.Drawfplanet.dungeon.framework.ObjectId;

public class End extends GameObject{

	public End(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	public void tick(LinkedList<GameObject> object) {
				
	}
	
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.drawRect((int) x, (int) y, 32, 32);
	}

	
	public Rectangle getBounds() {

		return new Rectangle((int)x, (int)y, 32,32);
	}

}

