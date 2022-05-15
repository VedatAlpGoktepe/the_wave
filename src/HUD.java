package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public static float HEALTH = 100;
	public static float healthCap = 100;
	private float greenValue = 255;
	
	private int score = 0;
	public static int level = 1;

	public void tick() {
		HEALTH = Game.clamp(HEALTH, 0, healthCap);
		greenValue = 255*HEALTH/healthCap;
		greenValue = Game.clamp(greenValue, 0, 255);
		
		if(Game.difficulty == 0) {
			score++;
		}
		else {
			score += 2;
		}
	}
	
	public void render(Graphics g) {
		Game.fontDefault(g);
		g.setColor(Color.gray);
		g.fillRect(Game.WIDTH/2 - 108, 15, 200, 32);
		g.setColor(new Color (125, (int) greenValue, 0));
		g.fillRect(Game.WIDTH/2 - 108, 15, (int) (200/healthCap * HEALTH), 32);
		g.setColor(Color.white);
		g.drawRect(Game.WIDTH/2 - 108, 15, 200, 32);
		
		g.drawString("Score: " + score, 10, 25);
		g.drawString("Level: " + level, 10, 40);
		g.drawString("Space for Shop", 10, 55);
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	public void setlevel(int level) {
		HUD.level = level;
	}
	public int getScore() {
		return score;
	}
	public int getLevel() {
		return level;
	}
}
