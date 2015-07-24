package tk.dwarfplanetgames.main.ui;

import tk.dwarfplanetgames.main.H;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CheckBox extends Actor {

	public boolean checked = false;
	public String text;
	private BitmapFont font;

	public CheckBox(int x, int y, String text) {
		super(x, y, 64, 64);
		this.text = text;
		font = new BitmapFont(Gdx.files.internal("default.fnt"));
		font.getData().setScale(1f);
		font.setColor(0.8f,0.8f,0.8f,1);
	}

	@Override
	public void draw(SpriteBatch batch, Texture skin) {
		TextureRegion r = new TextureRegion(skin, checked ? 32 * 3 : 32 * 2, 0, 32, 32);
		batch.draw(r, x, y, 0, 0, 32, 32, 2, 2, 0);
		font.draw(batch, text, x + 64, y + 64 - 16);
	}

	@Override
	public void touchDown(int localX, int localY) {
		checked = H.swap(checked);
	}

}
