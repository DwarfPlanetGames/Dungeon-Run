package tk.dwarfplanetgames.main;

import java.util.LinkedList;

import tk.dwarfplanetgames.main.screens.PlayScreen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();

	private GameObject tempObject;
	
	float camX = 0;
	float camY = 0;
	float camWidth = 0;
	float camHeight = 0;

	public void tick() {
		camX = PlayScreen.camera.position.x - PlayScreen.camera.viewportWidth / 2f;
		camY = PlayScreen.camera.position.y - PlayScreen.camera.viewportHeight / 2f;
		camWidth = PlayScreen.camera.viewportWidth;
		camHeight = PlayScreen.camera.viewportHeight;
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			if ((
					tempObject.x + tempObject.width > camX &&
					tempObject.y + tempObject.width > camY &&
					tempObject.x < camX + camWidth && 
					tempObject.y < camY + camHeight
					)||
					tempObject.id == ObjectId.Player
					) {
				tempObject.tick(object);
			}
		}
	}

	public void render(SpriteBatch b) {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			if (
					tempObject.x + 32 > camX &&
					tempObject.y + 32 > camY &&
					tempObject.x  < camX + camWidth && 
					tempObject.y < camY + camHeight
					) {
				tempObject.render(b);
			}
		}
	}

	public void addObject(GameObject object) {
		this.object.add(object);
	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}


}
