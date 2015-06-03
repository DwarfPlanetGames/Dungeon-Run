package com.Drawfplanet.dungeon.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.Drawfplanet.dungeon.Objects.Block;
import com.Drawfplanet.dungeon.Objects.End;
import com.Drawfplanet.dungeon.Objects.FallingBlocks;
import com.Drawfplanet.dungeon.Objects.Lava;
import com.Drawfplanet.dungeon.Objects.Player;
import com.Drawfplanet.dungeon.framework.KeyInput;
import com.Drawfplanet.dungeon.framework.ObjectId;
import com.Drawfplanet.dungeon.framework.Texture;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -412412589753911557L;
	
	public static boolean restart;
	
	private boolean running;
	private Thread thread;
	
	public static int playerX = 0;
	
	int time = 0;

	public static int WIDTH, HEIGHT;
	
	private BufferedImage level = null;

	Handler handler;
	Camera cam;
	static Texture tex;
	Random rand = new Random();

	private void init() {

		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		tex = new Texture();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/Level-1.png");
		
		handler = new Handler();
		cam = new Camera(0, 0);
		
		LoadImageLevel(level);
		
		this.addKeyListener(new KeyInput(handler));
	
		
	}

	public synchronized void start() {
		if (running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;

		int ticks = 0;
		int frames = 0;

		long lastTimer = System.currentTimeMillis();
		double delta = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;

			while (delta >= 1) {
				ticks++;
				tick();
				delta -= 1;
				shouldRender = true;
			}

			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (shouldRender) {
				frames++;
				render();
			}

			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				System.out.println(ticks + " ticks, " + frames + " frames");
				frames = 0;
				ticks = 0;

			}
		}

	}

	private void tick() {
		time++;
		handler.tick();
		for(int i = 0; i < handler.object.size(); i++){
			if(handler.object.get(i).getId() ==ObjectId.Player){
				cam.tick(handler.object.get(i));
			}
		}
		
		
		
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3); 
			return;
		}

		Graphics g = bs.getDrawGraphics();
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setClip(new Rectangle(0,0,getWidth(),getHeight()));
		

		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
		double backgroundDist = 1.25;
		g2d.drawImage(Texture.block[1], (int) (-playerX / backgroundDist) % getWidth(), 0, getWidth(), getHeight(), null);
		g2d.drawImage(Texture.block[1], (int) (-playerX / backgroundDist) % getWidth() + getWidth(), 0, getWidth(), getHeight(), null);
		
		g2d.translate(cam.getX(), cam.getY());
		handler.render(g);
		g2d.translate(-cam.getX(), -cam.getY());
		
		
		g.dispose();
		bs.show();

	}
	
	private void LoadImageLevel(BufferedImage image){
		int w = image.getWidth();
		int h = image.getHeight();
		
		
		for(int xx = 0; xx < h; xx++){
			for(int yy = 0; yy < w; yy++){
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 255 && green == 255 && blue == 255){
					handler.addObject(new Block(xx*32, yy*32,0,ObjectId.Block));
				}
				if(red == 0 && green == 0 && blue == 255){	
					handler.addObject(new Player(xx*32, yy*32,handler,ObjectId.Player));
				}
				if(red == 255 && green == 0 && blue == 0){
					handler.addObject(new Lava(xx*32, yy*32,2,ObjectId.Lava));
				}
				if(red == 0 && green == 255 && blue == 0){
					handler.addObject(new End(xx*32,yy*32,ObjectId.End));
				}
				if(red == 85 && green == 83 && blue == 83){
					handler.addObject(new FallingBlocks(xx*32,yy*32,3,ObjectId.FallingBlocks));
				}
			}
		}
	}
	public static Texture getInstance(){
		return tex;
	}

	public static void main(String args[]) {
		new Window(800, 600, "Dungeon run alpha", new Game());
	}

}
