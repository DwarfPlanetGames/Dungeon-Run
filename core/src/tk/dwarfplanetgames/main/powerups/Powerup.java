package tk.dwarfplanetgames.main.powerups;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Powerup {
	
	protected int duration = 0;
	protected boolean active = false;
	
	public Powerup(int duration) {
		this.duration = duration;
	}
	
	public void start() {
		active = true;
	}
	
	public void end() {
		active = false;
	}
	
	public abstract void renderItem(SpriteBatch batch);
	
	public void tick() {
		
	}
	
	public int getDuration() {
		return duration;
	}
	
}
