package tk.dwarfplanetgames.main.powerups;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Powerup {
	
	protected int duration = 0;
	protected boolean active = false;
	protected int time = 0;
	
	public Powerup(int duration) {
		this.duration = duration;
	}
	
	public void start() {
		active = true;
		time = 0;
	}
	
	public void end() {
		active = false;
	}
	
	public abstract TextureRegion renderItem();
	
	public void tick() {
		time++;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public int getTime() {
		return time;
	}
	
	public boolean isActive() {
		return active;
	}
	
}
