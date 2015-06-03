package com.Drawfplanet.dungeon.framework;

import java.awt.image.BufferedImage;

import com.Drawfplanet.dungeon.window.BufferedImageLoader;

public class Background {
	SpriteSheet bs, ps;
	private BufferedImage back = null;

	
	public BufferedImage[] block = new BufferedImage[3];
	
	public Background(){
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try{
			back = loader.loadImage("background.png");
			
	
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	

}
