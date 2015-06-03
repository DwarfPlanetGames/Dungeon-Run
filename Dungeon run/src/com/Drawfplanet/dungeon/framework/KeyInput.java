package com.Drawfplanet.dungeon.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.Drawfplanet.dungeon.window.Game;
import com.Drawfplanet.dungeon.window.Handler;

public class KeyInput extends KeyAdapter {

	Handler handler;

	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ObjectId.Player) {
				if (key == KeyEvent.VK_RIGHT)
					tempObject.setVelX(5);
				if (key == KeyEvent.VK_LEFT)
					tempObject.setVelX(-5);
				if (key == KeyEvent.VK_D)
					tempObject.setVelX(5);
				if (key == KeyEvent.VK_A)
					tempObject.setVelX(-5);
				if (key == KeyEvent.VK_SPACE && !tempObject.isJumping()) {
					tempObject.setJumping(true);
					tempObject.setVelY(-13);
				}
				
					
			}
		}

		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		if(key == KeyEvent.VK_R){
			Game.restart = false;
			if(Game.restart == true){
			int num = 1;
			System.out.println(num);
			}else if(Game.restart == false){
				int num = 0;
				System.out.println("restart is now false");
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ObjectId.Player) {
				if (key == KeyEvent.VK_RIGHT)
					tempObject.setVelX(0);
				if (key == KeyEvent.VK_LEFT)
					tempObject.setVelX(0);
				if (key == KeyEvent.VK_D)
					tempObject.setVelX(0);
				if (key == KeyEvent.VK_A)
					tempObject.setVelX(0);

			}
		}
	}

}
