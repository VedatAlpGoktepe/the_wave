package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HardEnemy extends GameObject {

	private GameObject player;
	private float speedMult = 1;

	public HardEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id, handler);

		//size = 16;
		velX = 5;
		velY = 5;
		color = Color.orange;
		objectImage = Game.hardEnemyImage.grabWhole();
		width = objectImage.getWidth();
		height = objectImage.getHeight();
		
		for(int i = 0; i < handler.object.size(); i++) {if(handler.object.get(i).getID() == ID.Player) {player = handler.object.get(i);}}
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
		
		float diffX = x - (player.getX() + 9);
		float diffY = y - (player.getY() + 9);
		float distance = (float) Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));

		if(x <= 0 || x >= Game.WIDTH-16 - width) {
			velX = -6*diffX/distance;
			velY = -6*diffY/distance;
		}
		if(y <= 0 || y >= Game.HEIGHT-40 - height) {
			velX = -6*diffX/distance;
			velY = -6*diffY/distance;
		}

		new Trail(x, y, ID.Trail, Color.red, Color.orange, width, height, 0.08f, handler);
	}

	public void render(Graphics g) {
		g.drawImage(objectImage, (int)x, (int)y, null);
	}
}
