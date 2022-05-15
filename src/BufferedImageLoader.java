package com.tutorial.main;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class BufferedImageLoader {

	private BufferedImage image;
	private int width;
	private int height;

	public BufferedImageLoader(String path) {
		try {
			image = ImageIO.read(new File(path));
			width = image.getWidth();
			height = image.getHeight();
		} catch(Exception e) {e.printStackTrace();}
	}
	
	public BufferedImage grabWhole() {
		return image;
	}
	
	public BufferedImage grabImage(int col, int row, int widht, int height) {
		BufferedImage cut = image.getSubimage(col*32 - 32, row*32 - 32, widht, height);
		return cut;
	}
	
	public BufferedImage grabBossImage(int col, int row, int widht, int height) {
		BufferedImage cut = image.getSubimage(col*96 - 96, row*96 - 96, widht, height);
		return cut;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
}