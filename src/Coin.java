package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Coin extends GameObject {

	public Coin(float x, float y, ID id, Handler handler) {
		super(x, y, id, handler);

		color = Color.yellow;
		objectImage = Game.coinImage.grabWhole();
		width = objectImage.getWidth();
		height = objectImage.getHeight();
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, width, height);
	}

	public void tick() {
		x = Game.clamp(x, 0, Game.WIDTH-16-13);
		y = Game.clamp(y, 0, Game.HEIGHT-40-15);
	}

	public void render(Graphics g) {
		g.drawImage(objectImage, (int) x, (int) y, null);
	}
}
