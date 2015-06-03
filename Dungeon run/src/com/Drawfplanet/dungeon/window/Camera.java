package com.Drawfplanet.dungeon.window;

import com.Drawfplanet.dungeon.framework.GameObject;

public class Camera {

	public float x;
	public float y;
	
	public Camera(float x, float y){
		this.x = x;
		this.y = y;
	}
	public void tick(GameObject Player){
		
		x = -Player.getX() + Game.WIDTH/2;
		//y = -Player.getY() + Game.HEIGHT/3;
	}
	
	public void setX(float x){this.x = x;}
	public void setY(float y){this.y = y;}
	public float getX(){ return x;}
	public float getY(){ return y;}
	
	
}
