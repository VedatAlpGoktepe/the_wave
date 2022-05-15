package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemyBullet extends GameObject {
	
	Random r = new Random();

	public BossEnemyBullet(float x, float y, ID id, Handler handler) {
		super(x, y, id, handler);

		//size = 16;
		velX = r.nextInt(11)-5;
		velY = 5;
		color = Color.magenta;
		objectImage = Game.bossBulletImage.grabWhole();
		width = objectImage.getWidth();
		height = objectImage.getHeight();
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, width, height);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(x >= Game.WIDTH-16) {handler.removeObject(this);}
		if(y >= Game.HEIGHT-40) {handler.removeObject(this);}
		
		new Trail(x, y, ID.Trail, objectImage, width, height, 0.08f, handler);
	}

	public void render(Graphics g) {
		g.drawImage(objectImage, (int)x, (int)y, null);
	}
}

 