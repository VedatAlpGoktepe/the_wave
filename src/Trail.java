package com.tutorial.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Trail extends GameObject {
	
	private float alpha = 1;
	private float life;
	
	private Color color, outline;
	
	private int width, height;
	private int type; //0 = solid color, 1 = color with outline, 2 = image

	public Trail(float x, float y, ID id, Color color, int width, int height, float life, Handler handler) {
		super(x, y, id, handler);
		this.color = color;
		this.width = width;
		this.height = height;
		this.life = life;
		type = 0;
	}
	public Trail(float x, float y, ID id, Color color, Color outline, int width, int height, float life, Handler handler) {
		super(x, y, id, handler);
		this.color = color;
		this.width = width;
		this.height = height;
		this.life = life;
		this.outline = outline;
		type = 1;
	}
	public Trail(float x, float y, ID id, BufferedImage objectImage, int width, int height, float life, Handler handler) {
		super(x, y, id, handler);
		this.objectImage = objectImage;
		this.width = width;
		this.height = height;
		this.life = life;
		type = 2;
	}

	public void tick() {
		if(alpha > life) {
			alpha -= life - 0.001f;
		}
		else {handler.removeTrail(this);}
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		if(type == 0) {
			g.setColor(color);
			g.fillRect((int) x, (int) y, width, height);
		}
		else if(type == 1) {
			g.setColor(color);
			g.fillRect((int) x, (int) y, width, height);
			g.setColor(outline);
			g.drawRect((int) x, (int) y, width, height);
		}
		else if(type == 2){
			g.drawImage(objectImage, (int)x, (int)y, null);
		}
		g2d.setComposite(makeTransparent(1));
	}
	
	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));
	}

	public Rectangle getBounds() {
		return null;
	}

}
