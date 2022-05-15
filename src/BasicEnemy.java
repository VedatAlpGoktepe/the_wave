package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject {
	
	private float speedMult = 1;

	public BasicEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id, handler);

		//size = 16;
		velX = 5;
		velY = 5;
		color = Color.red;
		objectImage = Game.basicEnemyImage.grabWhole();
		width = objectImage.getWidth();
		height = objectImage.getHeight();
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, width, height);
	}

	public void tick() {
		if(HUD.level >= 11) {
			speedMult = (float) (1 + (HUD.level-11)*0.05);
		}
		
		x += velX * speedMult;
		y += velY * speedMult;
		x = Game.clamp(x, 0, Game.WIDTH-16 - width);
		y = Game.clamp(y, 0, Game.HEIGHT-40 - height);
		
		if(x <= 0 || x >= Game.WIDTH-16 - width) {velX *= -1;}
		if(y <= 0 || y >= Game.HEIGHT-40 - height) {velY *= -1;}
		
		new Trail(x, y, ID.Trail, Color.red, width, height, 0.08f, handler);
	}

	public void render(Graphics g) {
		g.drawImage(objectImage, (int)x, (int)y, null);	
	}
}
