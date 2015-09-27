package tk.dwarfplanetgames.main.objects;

import java.util.LinkedList;

import tk.dwarfplanetgames.main.GameObject;
import tk.dwarfplanetgames.main.ObjectId;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Spike extends GameObject {
	
	// TExture region for the render
	public static TextureRegion tex = new TextureRegion(new Texture(
			"Texture_Spritesheet.png"), 0, 32, 32, 32);
	
	// Constructor
	public Spike(float x, float y, int type) {
		// Use same player interactions as Lava
		super(x, y, ObjectId.Lava);
	}
	
	// Tick
	public void tick(LinkedList<GameObject> object) {
	}

	public Rectangle getBounds() {
		// Return the bounding box
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	@Override
	public void render(SpriteBatch b) {
		// Draw the texture
		b.draw(tex, x, y);
	}

	@Override
	public void qRender(SpriteBatch b) {
		// Render the object
		render(b);
	}
}
