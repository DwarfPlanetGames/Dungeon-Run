package com.Drawfplanet.dungeon.framework;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {

	public float x, y;
	public float xo, yo;
	protected ObjectId id;
	protected float velX = 0, velY =0;
	protected boolean falling= true;
	protected boolean jumping = false;
	protected boolean falling_block = false;
	protected boolean reset = false;
	public float width, height;
	
	public GameObject(float x, float y, ObjectId id){
		this.x = x;
		this.y = y;
		this.id = id;
		xo = x;
		yo = y;
		
	}
	
	public abstract void tick(LinkedList<GameObject> object);
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public void quickRender(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect((int) x, (int) y, 32, 32);
	}
	
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	public void setX(float x){
		this.x = x;
	}
	public void setY(float y){
		this.y = y;
	}
	
	public float getVelX(){
		return velX;
	}
	public float getVelY(){
		return velY;
	}
	public void setVelX(float velX){
		this.velX = velX;
	}
	public void setVelY(float velY){
		this.velY = velY;
	}
	
	public ObjectId getId(){
		return id;
	}
	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}	
	
	public boolean falling_block(){
		return falling_block;
	}
	public void setfalling_block(boolean falling_block){
		this.falling_block = falling_block;
	}
	public boolean reset(){
		return reset;
	}
	public void setreset(boolean reset){
		this.reset = reset;
	}
	
	

}
