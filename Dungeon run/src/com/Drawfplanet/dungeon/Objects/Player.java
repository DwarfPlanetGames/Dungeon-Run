package com.Drawfplanet.dungeon.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.Drawfplanet.dungeon.framework.GameObject;
import com.Drawfplanet.dungeon.framework.ObjectId;
import com.Drawfplanet.dungeon.framework.Texture;
import com.Drawfplanet.dungeon.window.Game;
import com.Drawfplanet.dungeon.window.Handler;

public class Player extends GameObject {

	private float width = 48, height = 96;

	private float gravity = 0.5f;
	private final float MAX_SPEED = 10;
	public static Boolean dead = false;
	
	private Handler handler;
	
	Texture tex = Game.getInstance();
	

	public Player(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		
	}

	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;

		if (falling || jumping) {
			velY += gravity;
		

			if (velY > MAX_SPEED) {
				velY = MAX_SPEED;
			}
		}
		
		

		Collision(object);
		Game.playerX = (int) x;
	}

	private void Collision(LinkedList<GameObject> object) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ObjectId.Block ) {
				// top collision
				if (getBoundsTop().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + 32;
					velY = 0;

				}
				// bottom collision
				if (getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;
					velY = 0;

					falling = false;
					jumping = false;					
				} else
					falling = true;
					
				// Right collision
				if (getBoundsRight().intersects(tempObject.getBounds())) {

					x = tempObject.getX() - width;
					
				}
				// left collision
				if (getBoundsLeft().intersects(tempObject.getBounds())) {

					x = tempObject.getX() + 32;
					
				}
			}
			//resets the player position back to start if player touches lava
			if (tempObject.getId() == ObjectId.Lava) {
				if(getBounds().intersects(tempObject.getBounds())){
					//handler.removeObject(this);
					x = xo;
					y = yo;	
				}
			
				
			}
			//ends the game if player touches the end block
			if(tempObject.getId() == ObjectId.End){
				if(getBounds().intersects(tempObject.getBounds())){
					System.exit(0);
					
				}
			}
			//triggers the falling blocks to fall
			if(tempObject.getId() == ObjectId.FallingBlocks){
				if(getBounds().intersects(tempObject.getBounds())){
					velY = 0;
					jumping = false;
					tempObject.setfalling_block(true);	
				}
				//side won't go through falling block
				if(getBoundsRight().intersects(tempObject.getBounds())){
					x = tempObject.getX() - width;
				}
				
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.drawRect((int) x, (int) y, (int) width, (int) height);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.red);
		
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int) ((int) x + (width / 2) - ((width / 2) / 2)),
				(int) ((int) y + (height / 2)), (int) width / 2,
				(int) height / 2);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int) x + width - 5), (int) y + 5, (int) 5,
				(int) height - 10);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x, (int) y + 5, (int) 5, (int) height - 10);
	}

	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int) x + (width / 2) - ((width / 2) / 2)),
				(int) y, (int) width / 2, (int) height / 2);
	}
}
