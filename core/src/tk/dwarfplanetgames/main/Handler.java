package tk.dwarfplanetgames.main;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();

	private GameObject tempObject;
	
	float camX = 0;
	float camY = 0;
	float camWidth = 0;
	float camHeight = 0;

	public void tick() {
		camX = MainGame.camera.position.x - MainGame.camera.viewportWidth / 2f;
		camY = MainGame.camera.position.y - MainGame.camera.viewportHeight / 2f;
		camWidth = MainGame.camera.viewportWidth;
		camHeight = MainGame.camera.viewportHeight;
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			if ((
					tempObject.x > camX &&
					tempObject.y > camY &&
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
					tempObject.x > camX &&
					tempObject.y > camY &&
					tempObject.x < camX + camWidth && 
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
