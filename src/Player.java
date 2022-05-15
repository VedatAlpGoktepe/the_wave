package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject {

	Random r = new Random();

	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id, handler);

		//size = 32;
		color = Color.green;
		objectImage = Game.playerImage.grabWhole();
		width = objectImage.getWidth();
		height = objectImage.getHeight();
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, width, height);
	}

	public void tick() {
		x += velX;
		y += velY;

		x = Game.clamp(x, 0, Game.WIDTH-16 - width);
		y = Game.clamp(y, 0, Game.HEIGHT-40 - height);

		new Trail(x, y, ID.Trail, color, width, height, 0.1f, handler);

		collision();
	}

	public void render(Graphics g) {
		g.drawImage(objectImage, (int)x, (int)y, null);
	}

	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if(tempObject.getID() == ID.Enemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH--;
				}
			}
			else if(tempObject.getID() == ID.BossEnemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 3;
				}
			}
			else if(tempObject.getID() == ID.Coin) {
				if(getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(tempObject);
					Spawn.noCoin = true;
					Shop.money += 250;
					Spawn.coinTimer = 300;
				}
			}
		}
	}
}
