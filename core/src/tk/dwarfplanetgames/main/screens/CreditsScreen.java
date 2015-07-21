package tk.dwarfplanetgames.main.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CreditsScreen implements Screen {
    private SpriteBatch batch;
    private BitmapFont font;
    TextureRegion block = new TextureRegion(new Texture("Texture_Spritesheet.png"), 0, 0, 32, 32);
    private static final String creditNames[] = new String[]{"Brandon Dyer", "Austin White", "Ian Green"};
    
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void render(float delta) { 
    	
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        //font.draw(batch, "Hello World", 200, 200);
        for (int x = -1; x < Gdx.graphics.getWidth() / 64f + 2; x++) {
			for (int y = -1; y < Gdx.graphics.getHeight() / 64f + 2; y++) {
				batch.draw(block, (int)(x * 64), (int)(y * 64), 64,64);
			}
		}
        for(int i=0 ; i < creditNames.length; i++){
        	font.draw(batch, "Hello World", 10, i*100);
        }
        
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }


	@Override
	public void show() {
		  batch = new SpriteBatch();    
	        font = new BitmapFont();
	        font.setColor(Color.RED);
	    	
	}


	@Override
	public void hide() {
		
		
	}
}
