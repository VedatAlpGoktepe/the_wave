package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;

public class Menu extends MouseAdapter{
	
	private Handler handler;
	private HUD hud;
	Random r = new Random();
	
	public Menu(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e) {
		
	}
	
	public void mouseReleased(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(Game.gameState == STATE.Menu) {
			//play button
			if(mouseOver(mx, my, 212, 150, 200, 64)) {
				Game.gameState = STATE.Select;
				AudioPlayer.playAudio("res/ButtonClick.wav");
			}
			
			//help button
			if(mouseOver(mx, my, 212, 240, 200, 64)) {
				Game.gameState = STATE.Help;
				AudioPlayer.playAudio("res/ButtonClick.wav");
			}
			
			//quit button
			if(mouseOver(mx, my, 212, 330, 200, 64)) {
				System.exit(1);
			}
		}
		
		else if(Game.gameState == STATE.Help) {
			//back button
			if(mouseOver(mx, my, 212, 330, 200, 64)) {
				Game.gameState = STATE.Menu;
				AudioPlayer.playAudio("res/ButtonClick.wav");
			}
		}
		
		else if(Game.gameState == STATE.Select) {
			//normal difficulty
			if(mouseOver(mx, my, 212, 150, 200, 64)) {
				handler.clearEnemies();
				Game.gameState = STATE.Game;
				new Player(Game.WIDTH/2 - 24, Game.HEIGHT/2 - 36, ID.Player, handler);
				new BasicEnemy(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-56), ID.Enemy, handler);
				
				Game.difficulty = 0;
				Spawn.levelUp = 250;
				Spawn.levelRequire = 250;
				
				AudioPlayer.playAudio("res/ButtonClick.wav");
			}
			
			//hard difficulty
			if(mouseOver(mx, my, 212, 240, 200, 64)) {
				handler.clearEnemies();
				Game.gameState = STATE.Game;
				new Player(Game.WIDTH/2 - 24, Game.HEIGHT/2 - 36, ID.Player, handler);
				new HardEnemy(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-56), ID.Enemy, handler);
				
				Game.difficulty = 1;
				Spawn.levelUp = 500;
				Spawn.levelRequire = 500;
				
				AudioPlayer.playAudio("res/ButtonClick.wav");
			}
			
			//back button
			if(mouseOver(mx, my, 212, 330, 200, 64)) {
				Game.gameState = STATE.Menu;
				AudioPlayer.playAudio("res/ButtonClick.wav");
			}
		}
		
		else if(Game.gameState == STATE.End) {
			//main menu button
			if(mouseOver(mx, my, 212, 330, 200, 64)) {
				Game.gameState = STATE.Menu;
				hud.setScore(0);
				AudioPlayer.playAudio("res/ButtonClick.wav");
			}
		}
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx >= x && mx <= x+width) {
			if(my >= y && my <= y+height) {return true;}
			else {return false;}
		}
		else {return false;}
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		if(Game.gameState == STATE.Menu) {
			Font fnt = new Font("arial", 1, 55);
			Font fnt2 = new Font("arial", 1, 35);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("The Wave", 185, 90);
			
			g.setFont(fnt2);
			g.drawRect(212, 150, 200, 64);
			g.drawString("Play", 275, 195);
			
			g.drawRect(212, 240, 200, 64);
			g.drawString("Help", 275, 285);
			
			g.drawRect(212, 330, 200, 64);
			g.drawString("Quit", 275, 375);
		}
		else if(Game.gameState == STATE.Select) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 35);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Select Difficulty", 130, 90);
			
			g.setFont(fnt2);
			g.drawRect(212, 150, 200, 64);
			g.drawString("Normal", 253, 195);
			
			g.drawRect(212, 240, 200, 64);
			g.drawString("Hard", 274, 285);
			
			g.drawRect(212, 330, 200, 64);
			g.drawString("Back", 273, 375);
		}
		else if(Game.gameState == STATE.Help) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 25);
			Font fnt3 = new Font("arial", 1, 35);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 260, 70);
			
			g.setFont(fnt2);
			g.drawString("Use WASD to move around", 150, 195);
			g.drawString("and dodge enemies.", 195, 220);
			
			g.setFont(fnt3);
			g.drawRect(212, 330, 200, 64);
			g.drawString("Back", 270, 375);
		}
		else if(Game.gameState == STATE.End) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 25);
			Font fnt3 = new Font("arial", 1, 35);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game Over", 180, 70);
			
			g.setFont(fnt2);
			g.drawString("Final Score", 245, 195);
			g.drawString("" + hud.getScore(), 290, 230);
			
			g.setFont(fnt3);
			g.drawRect(212, 330, 200, 64);
			g.drawString("Main Menu", 226, 375);
		}
	}

}
