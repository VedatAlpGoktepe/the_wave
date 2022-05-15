package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemy extends GameObject {
	
	Random r = new Random();
	
	private int entrancePause = 120;
	private boolean entrance = false;
	private boolean ready = false;
	private int fireTimer = 0;
	private int fireTime = 600;

	public BossEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id, handler);
		
		//size = 96;
		objectImage = Game.boss1Image.grabWhole();
		width = objectImage.getWidth();
		height = objectImage.getHeight();
		
		x = (Game.WIDTH-16)/2 - width;
		y = 0 - height;
		velX = 0;
		velY = 1;
		color = Color.blue;
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, width, height);
	}

	public void tick() {
		if(ready) {
			x += velX;
			y += velY;
		}
		
		if(!entrance && y < 20) {
			y += velY;
			if(y == 0) {velY = 0; entrance= true;}
		}
		
		if(entrance && !ready) {
			entrancePause--;
			if(entrancePause == 0) {velX = 2; ready = true;}
		}
		
		if(ready) {
			if(fireTimer >= fireTime) {
				new BossEnemyBullet(x + width/2 - 8, y + height/2 - 8, ID.Enemy, handler);
				fireTimer = 0;
			}
			if(fireTime > 100) {fireTime--;}
			
			
			if(velX > 0) {velX += 0.005f;}
			else {velX -= 0.005f;}
			
			velX = Game.clamp(velX, -10, 10);
			
			fireTimer += 10;
		}
		
		if(x <= 0 || x >= Game.WIDTH-16 - width) {velX *= -1;}
		
		//new Trail(x, y, ID.Trail, color, width, height, 0.08f, handler);
	}

	public void render(Graphics g) {
		g.drawImage(objectImage, (int) x, (int) y, null);
	}

}
