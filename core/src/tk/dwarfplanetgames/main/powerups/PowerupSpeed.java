package tk.dwarfplanetgames.main.powerups;

import tk.dwarfplanetgames.main.Handler;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PowerupSpeed extends Powerup {
	
	// Constructor
	public PowerupSpeed(int duration) {
		super(duration);
	}
	
	@Override
	public void tick() {
		super.tick();
		Handler.getPlayer().accel = 1f;
	}

	@Override
	public TextureRegion renderItem() {
		return new TextureRegion();
	}

}
