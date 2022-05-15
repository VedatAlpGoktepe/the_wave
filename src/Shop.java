package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.tutorial.main.Game.STATE;

public class Shop extends MouseAdapter{

	Handler handler;
	HUD hud;

	private int U1 = 1000;
	private int U2 = 1000;
	private int U3 = 1000;
	private int U4 = 1000;
	private int U5 = 1000;
	private int U6 = 1000;

	public static int money = 0;

	public static int errorTimer = 0;

	public Shop(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick() {
		if(errorTimer > 0) {
			errorTimer--;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("arial", 1, 48));
		g.drawString("SHOP", Game.WIDTH/2 - 74, 75);

		//upgrade 1
		g.drawRect(100, 145, 100, 75);
		g.setFont(new Font("arial", 1, 12));
		g.drawString("Refill Health", 118, 174);
		g.setFont(new Font("arial", 0, 12));
		g.drawString("Cost: " + U1, 118, 195);

		//upgrade 2
		g.drawRect(Game.MIDDLEW-50, 145, 100, 75);
		g.setFont(new Font("arial", 1, 12));
		g.drawString("Upgrade Health", Game.MIDDLEW-42, 174);
		g.setFont(new Font("arial", 0, 12));
		g.drawString("Cost: " + U2, Game.MIDDLEW-42, 195);

		//upgrade 3
		g.drawRect(Game.WIDTH - 216, 145, 100, 75);
		g.setFont(new Font("arial", 1, 12));
		g.drawString("Upgrade Speed", Game.WIDTH - 208, 174);
		g.setFont(new Font("arial", 0, 12));
		g.drawString("Cost: " + U3, Game.WIDTH - 208, 195);

		//money
		g.setFont(new Font("arial", 0, 30));
		g.drawString("Money: " + money, Game.MIDDLEW - 90, 315);

		//hud
		Game.fontDefault(g);
		g.drawString("Score: " + hud.getScore(), 10, 25);
		g.drawString("Space to Continue", 10, 40);

		//not enough money
		if(errorTimer > 0) {
			g.setColor(Color.red);
			g.setFont(new Font("arial", 1, 12));
			g.drawString("Not Enough Money", Game.MIDDLEW-50, 310);
		}
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(Game.gameState == STATE.Shop) {
			//upgrade 1
			if(mx >= 100 && mx <= 200) {
				if(my >= 75 && my <= 150) {
					if(money >= U1) {
						money -= U1;
						U1 += 1000;
						HUD.HEALTH = HUD.healthCap;
					}
					else {errorTimer = 180;}
				}
			}

			//upgrade 2
			if(mx >= Game.MIDDLEW-50 && mx <= Game.MIDDLEW+50) {
				if(my >= 75 && my <= 150) {
					if(money >= U2) {
						money -= U2;
						U2 += 1000;
						HUD.healthCap += 10;
					}
					else {errorTimer = 180;}
				}
			}

			//upgrade 3
			if(mx >= Game.WIDTH-216 && mx <= Game.WIDTH-116) {
				if(my >= 75 && my <= 150) {
					if(money >= U3) {
						money -= U3;
						U3 += 1000;
						KeyInput.playerSpeed += 1;
					}
					else {errorTimer = 180;}
				}
			}

			//upgrade 4
			if(mx >= 100 && mx <= 200) {
				if(my >= 175 && my <= 250) {
					if(money >= U4) {
						money -= U4;
						U4 += 1000;
						HUD.HEALTH = 100;
					}
					else {errorTimer = 180;}
				}
			}

			//upgrade 5
			if(mx >= Game.MIDDLEW-50 && mx <= Game.MIDDLEW+50) {
				if(my >= 175 && my <= 250) {
					if(money >= U5) {
						money -= U5;
						U5 += 1000;
						HUD.HEALTH = 100;
					}
					else {errorTimer = 180;}
				}
			}

			//upgrade 6
			if(mx >= Game.WIDTH-216 && mx <= Game.WIDTH-116) {
				if(my >= 175 && my <= 250) {
					if(money >= U6) {
						money -= U6;
						U6 += 1000;
						HUD.HEALTH = 100;
					}
					else {errorTimer = 180;}
				}
			}
		}
	}

}
