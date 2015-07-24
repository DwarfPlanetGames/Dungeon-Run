package tk.dwarfplanetgames.main.ui;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Stage {
	
	public Texture skin;
	
	private LinkedList<Actor> actors = new LinkedList<Actor>();
	
	public Stage(Texture skin) {
		this.skin = skin;
	}
	
	public void act(float delta) {
		for (int i = 0; i < actors.size(); i++) {
			actors.get(i).act(delta);
		}
	}
	
	public void draw(SpriteBatch batch) {
		for (int i = 0; i < actors.size(); i++) {
			actors.get(i).draw(batch, skin);
		}
	}
	
	public void addActor(Actor actor) {
		actors.add(actor);
	}
	
	
}
