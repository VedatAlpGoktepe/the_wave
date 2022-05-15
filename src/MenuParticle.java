package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject {

	Random r = new Random();
	private Color col;
	
	public MenuParticle(float x, float y, ID id, Handler handler) {
		super(x, y, id, handler);

		size = 16;
		
		velX = r.nextInt(11) - 5;
		velY = r.nextInt(11) - 5;
		if(velX == velY && velY == 0) {
			velX = r.nextInt(11) - 5;
			velY = r.nextInt(11) - 5;
		}
		
		col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, size, size);
	}

	public void tick() {
		x += velX;
		y += velY;
		x = Game.clamp(x, 0, Game.WIDTH-16 - size);
		y = Game.clamp(y, 0, Game.HEIGHT-40 - size);
		
		if(x <= 0 || x >= Game.WIDTH-16 - size) {velX *= -1; col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));}
		if(y <= 0 || y >= Game.HEIGHT-40 - size) {velY *= -1; col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));}
		
		new Trail(x, y, ID.Trail, col, size, size, 0.05f, handler);
	}

	public void render(Graphics g) {
		g.setColor(col);
		g.fillRect((int) x, (int) y, size, size);
		
	}

}
