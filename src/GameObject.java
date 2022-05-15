package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class GameObject {

	protected float x, y, velX, velY;
	protected ID id;
	protected Handler handler;
	protected int width, height, size;
	protected Color color;
	protected BufferedImage objectImage;

	public GameObject(float x, float y, ID id, Handler handler) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;

		if(id == ID.Trail) {
			handler.addTrail(this);
		}
		else {
			handler.addObject(this);
		}
	}

	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();

	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setID(ID id) {
		this.id = id;
	}
	public void setVelX(int velX) {
		this.velX = velX;
	}
	public void setVelY(int velY) {
		this.velY = velY;
	}

	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public ID getID() {
		return id;
	}
	public float getVelX() {
		return velX;
	}
	public float getVelY() {
		return velY;
	}

}
