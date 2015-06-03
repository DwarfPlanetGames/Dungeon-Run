package com.Drawfplanet.dungeon.framework;

import java.awt.image.BufferedImage;

import com.Drawfplanet.dungeon.window.BufferedImageLoader;

public class Texture {
	private static SpriteSheet bs, ps;
	private static BufferedImage block_sheet = null;
	private static BufferedImage player_sheet = null;
	
	public static BufferedImage[] block = new BufferedImage[4];
	
	public Texture(){
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try{
			block_sheet = loader.loadImage("/Texture_Spritesheet.png");
			
			//player_sheet = loader.loadImage("");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		bs = new SpriteSheet(block_sheet);
		ps = new SpriteSheet(player_sheet);
		
		getTexture();
	}
	
	public static void getTexture(){
		block[0]= bs.grabImage(1,1,32,32);
		block[1] = bs.grabImage(2, 1, 32, 32);
		block[2] = bs.grabImage(3, 1, 32, 32);
		block[3] = bs.grabImage(4, 1, 32, 32);
	}
}
