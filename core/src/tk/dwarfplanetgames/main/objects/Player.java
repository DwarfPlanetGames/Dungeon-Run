package tk.dwarfplanetgames.main.objects;

import java.awt.Graphics;
import java.util.LinkedList;

import tk.dwarfplanetgames.main.GameObject;
import tk.dwarfplanetgames.main.Handler;
import tk.dwarfplanetgames.main.ObjectId;
import tk.dwarfplanetgames.main.screens.PlayScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.sun.glass.events.KeyEvent;
import com.sun.webkit.dom.KeyboardEventImpl;

public class Player extends GameObject {

	private float width = 48, height = 96;

	private float gravity = 1.3f;
	public static Boolean dead = false;
	public static int levelid = 0;

	public static TextureRegion tex;
	public static final float DEFAULT_SPEED = 18f;
	public float speed = DEFAULT_SPEED;
	public static final float DEFAULT_ACCEL = 0.5f;
	public float accel = DEFAULT_ACCEL;

	public Player(float x, float y) {
		super(x, y, ObjectId.Player);
		tex = new TextureRegion(new Texture("Texture_Spritesheet.png"), 32 * 4,
				0, 48, 96);
		tex.flip(false, true);
	}

	public void tick(LinkedList<GameObject> object) {
		 if (velX < speed)
			velX += accel;
		x += velX;
		y += velY;

		if ((Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.SPACE)) && !jumping && Gdx.input.getY() > 64) {
			jumping = true;
			velY -= 30;
		}

		if (falling || jumping) {
			velY += gravity;

			/*
			 * if (velY > MAX_SPEED) { velY = MAX_SPEED; }
			 */
		}

		Collision(object);
		PlayScreen.playerX = (int) x - 128;
		PlayScreen.playerY = (int) (y + height / 2f);
		PlayScreen.X = x;
		PlayScreen.Y = y;
		speed = DEFAULT_SPEED;
		accel = DEFAULT_ACCEL;
	}

	private void Collision(LinkedList<GameObject> object) {
		for (int i = 0; i < Handler.objects.size(); i++) {
			GameObject tempObject = Handler.objects.get(i);

			if (tempObject.getId() == ObjectId.Block) {
				// Right collision
				if (getBoundsRight().overlaps(tempObject.getBounds())) {

					x = tempObject.x - width;
					velX = 0;

				}
				// top collision
				if (getBoundsTop().overlaps(tempObject.getBounds())) {
					y = tempObject.y + 32;
					velY = 0;

				}
				// bottom collision
				if (getBounds().overlaps(tempObject.getBounds())) {
					y = tempObject.y - height;
					velY = 0;

					falling = false;
					jumping = false;
				} else {
					falling = true;

					// intended to fix the jumping after falling from a platform
					// glitch
					// jumping = true;
				}

				// left collision
				if (getBoundsLeft().overlaps(tempObject.getBounds())) {

					x = tempObject.x + 32;
					velX = 0;
					System.out
							.println("Woah!!! Something happened that shouldn't have!");

				}
			}
			// resets the player position back to start if player touches lava
			if (tempObject.getId() == ObjectId.Lava) {
				if (getBounds().overlaps(tempObject.getBounds())) {
					PlayScreen.levelId--;
					PlayScreen.levelUp();
				}

			}
			// ends the game if player touches the end block
			if (tempObject.getId() == ObjectId.End) {
				if (getBounds().overlaps(tempObject.getBounds())) {
					PlayScreen.levelUp();
				}
			}
			// triggers the falling blocks to fall
			if (tempObject.getId() == ObjectId.FallingBlocks) {
				if (getBounds().overlaps(tempObject.getBounds())) {
					velY = 0;
					jumping = false;
					((FallingBlocks) tempObject).setFallingBlock(true);
				}
				// side won't go through falling block
				if (getBoundsRight().overlaps(tempObject.getBounds())) {
					x = tempObject.x - width;
				}

			}
		}
	}

	public void render(Graphics g) {
		/*
		 * g.setColor(Color.blue); g.drawRect((int) x, (int) y, (int) width,
		 * (int) height);
		 * 
		 * Graphics2D g2d = (Graphics2D) g; g2d.setColor(Color.red);
		 */

	}

	public Rectangle getBounds() {
		return new Rectangle((int) ((int) x + (width / 2) - ((width / 2) / 2)),
				(int) ((int) y + (height / 2)), (int) width / 2, (int) height
						/ 2 + velY);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int) x + width - 5 - velX), (int) y + 10,
				(int) 5 + velX, (int) height - 20);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x + velX, (int) y + 10, (int) 5 - velX,
				(int) height - 20);
	}

	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int) x + (width / 2) - ((width / 2) / 2)),
				(int) y + velY, (int) width / 2, (int) height / 2 - velY);
	}

	@Override
	public void render(SpriteBatch b) {
		b.draw(tex, x, y);
	}
}
