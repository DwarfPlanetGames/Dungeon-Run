package tk.dwarfplanetgames.main;

import java.util.LinkedList;

import tk.dwarfplanetgames.main.screens.PlayScreen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Handler {

	public static LinkedList<GameObject> object = new LinkedList<GameObject>();

	private static GameObject tempObject;
	
	public static float camX = 0;
	public static float camY = 0;
	public static float camWidth = 0;
	public static float camHeight = 0;

	public static void tick() {
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

	public static void render(SpriteBatch b) {
		camX = PlayScreen.camera.position.x - PlayScreen.camera.viewportWidth / 2f;
		camY = PlayScreen.camera.position.y - PlayScreen.camera.viewportHeight / 2f;
		camWidth = PlayScreen.camera.viewportWidth;
		camHeight = PlayScreen.camera.viewportHeight;
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			if (
					tempObject.x + 32 > camX &&
					tempObject.y + 32 > camY &&
					tempObject.x  < camX + camWidth && 
					tempObject.y < camY + camHeight
					) {
				tempObject.qRender(b);
			}
		}
	}
	
	public static void qRender(SpriteBatch b, OrthographicCamera camera) {
		camX = camera.position.x - camera.viewportWidth / 2f;
		camY = camera.position.y - camera.viewportHeight / 2f;
		camWidth = camera.viewportWidth;
		camHeight = camera.viewportHeight;
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			float x = tempObject.x;
			float y = tempObject.y;
			tempObject.x -= camX;
			tempObject.y -= camY;
			tempObject.y = ((tempObject.y - camY) * -1) + camY;
			tempObject.render(b);
			tempObject.x = x;
			tempObject.y = y;
		}
	}

	public static void addObject(GameObject object) {
		Handler.object.add(object);
	}

	public static  void removeObject(GameObject object) {
		Handler.object.remove(object);
	}


}
