package tk.dwarfplanetgames.main;

import java.util.LinkedList;

import tk.dwarfplanetgames.main.objects.Player;
import tk.dwarfplanetgames.main.powerups.Powerup;
import tk.dwarfplanetgames.main.screens.PlayScreen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Handler {
	
	// Lists for objects and powerups
	public static LinkedList<GameObject> objects = new LinkedList<GameObject>();
	public static LinkedList<Powerup> powerups = new LinkedList<Powerup>();
	
	// Variables for camera
	public static float camX = 0;
	public static float camY = 0;
	public static float camWidth = 0;
	public static float camHeight = 0;

	// Tick the objects and powerups
	public static void tick() {
		// Set the camera position relative to the player
		camX = PlayScreen.camera.position.x - PlayScreen.camera.viewportWidth
				/ 2f;
		camY = PlayScreen.camera.position.y - PlayScreen.camera.viewportHeight
				/ 2f;
		// Set the camera size relative to the screen size
		camWidth = PlayScreen.camera.viewportWidth;
		camHeight = PlayScreen.camera.viewportHeight;
		// Tick game objects
		for (int i = 0; i < objects.size(); i++) {
			// Set the temporary object
			GameObject tempObject = objects.get(i);
			// Check if temporary object is visible on the screen
			if ((tempObject.x + tempObject.width > camX
					&& tempObject.y + tempObject.width > camY
					&& tempObject.x < camX + camWidth && tempObject.y < camY
					+ camHeight)
					|| tempObject.id == ObjectId.Player) {
				// Tick the temporary object
				tempObject.tick(objects);
			}
		}
		// Tick powerups
		for (int i = 0; i < powerups.size(); i++) {
			// Set temporary powerup
			Powerup p = powerups.get(i);
			// Check if active
			if (p.isActive()) {
				// Tick powerup
				p.tick();
			}
		}
	}
	
	// Render the game
	public static void render(SpriteBatch b) {
		// Set camera location relative to the player
		camX = PlayScreen.camera.position.x - PlayScreen.camera.viewportWidth
				/ 2f;
		camY = PlayScreen.camera.position.y - PlayScreen.camera.viewportHeight
				/ 2f;
		// Set the camera size relative to the screen
		camWidth = PlayScreen.camera.viewportWidth;
		camHeight = PlayScreen.camera.viewportHeight;
		// Render objects
		for (int i = 0; i < objects.size(); i++) {
			// Set temporary object
			GameObject tempObject = objects.get(i);
			// Check if temporary object is on the screen
			if (tempObject.x + 32 > camX && tempObject.y + 32 > camY
					&& tempObject.x < camX + camWidth
					&& tempObject.y < camY + camHeight) {
				// Render the temporary object
				tempObject.qRender(b);
			}
		}
	}
	
	// Render based on a static camera
	public static void qRender(SpriteBatch b, OrthographicCamera camera) {
		// Set cam variables
		camX = camera.position.x - camera.viewportWidth / 2f;
		camY = camera.position.y - camera.viewportHeight / 2f;
		camWidth = camera.viewportWidth;
		camHeight = camera.viewportHeight;
		// Render the objects
		for (int i = 0; i < objects.size(); i++) {
			// Set temporary object
			GameObject tempObject = objects.get(i);
			float x = tempObject.x;
			float y = tempObject.y;
			// Move object relative to camera
			tempObject.x -= camX;
			tempObject.y -= camY;
			tempObject.y = ((tempObject.y - camY) * -1) + camY;
			// Render object
			tempObject.render(b);
			// Move object back
			tempObject.x = x;
			tempObject.y = y;
		}
	}

	public static void addObject(GameObject object) {
		// Add object to list
		Handler.objects.add(object);
	}

	public static void removeObject(GameObject object) {
		// Remove object from list
		Handler.objects.remove(object);
	}
	
	// Get the game's player object
	public static Player getPlayer() {
		// Create temporary player object
		Player p = null;
		// Find the player object from the list
		for (int i = 0; i < objects.size(); i++) {
			// Check if the object is a player
			if (objects.get(i) instanceof Player) {
				// Set the player variable to it
				p = (Player) objects.get(i);
			}
		}
		// Return the player variable
		return p;
	}

}
