package tk.dwarfplanetgames.main.ui;

import tk.dwarfplanetgames.main.H;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CheckBox extends Actor {

	public boolean checked = false;

	public CheckBox(int x, int y) {
		super(x - 8, y - 8, 32 + 16, 32 + 16);
	}

	@Override
	public void draw(SpriteBatch batch, Texture skin) {
		TextureRegion r = new TextureRegion(skin, checked ? 32 * 3 : 32 * 2, 0, 32, 32);
		batch.draw(r, x + 8, y + 8);
	}

	@Override
	public void touchDown(int localX, int localY) {
		checked = H.swap(checked);
	}

}
