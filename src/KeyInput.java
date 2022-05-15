package com.tutorial.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.tutorial.main.Game.STATE;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	public static int playerSpeed = 5;
	public static boolean uP = false, lP = false, dP = false, rP = false;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Player) {
				if(key == KeyEvent.VK_W) {
					uP = true;
					if(dP) {tempObject.setVelY(0);}
					else{tempObject.setVelY(-playerSpeed);}
				}
				if(key == KeyEvent.VK_A) {
					lP = true;
					if(rP) {tempObject.setVelX(0);}
					else{tempObject.setVelX(-playerSpeed);}
				}
				if(key == KeyEvent.VK_S) {
					dP = true;
					if(uP) {tempObject.setVelY(0);}
					else{tempObject.setVelY(playerSpeed);}
				}
				if(key == KeyEvent.VK_D) {
					rP = true;
					if(lP) {tempObject.setVelX(0);}
					else{tempObject.setVelX(playerSpeed);}
				}
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE || key == KeyEvent.VK_P) {
			if(Game.gameState == STATE.Game) {
				if(Game.paused) {Game.paused = false;}
				else {Game.paused = true;}
			}
		}
		
		if(key == KeyEvent.VK_SPACE) {
			if(Game.gameState == STATE.Game && !Game.paused) {Game.gameState = STATE.Shop;}
			else if(Game.gameState == STATE.Shop) {Game.gameState = STATE.Game; Shop.errorTimer = 0;}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Player) {
				if(key == KeyEvent.VK_W) {
					uP = false;
					if(dP) {tempObject.setVelY(playerSpeed);}
					else{tempObject.setVelY(0);}
				}
				if(key == KeyEvent.VK_A) {
					lP = false;
					if(rP) {tempObject.setVelX(playerSpeed);}
					else{tempObject.setVelX(0);}
				}
				if(key == KeyEvent.VK_S) {
					dP = false;
					if(uP) {tempObject.setVelY(-playerSpeed);}
					else{tempObject.setVelY(0);}
				}
				if(key == KeyEvent.VK_D) {
					rP = false;
					if(lP) {tempObject.setVelX(-playerSpeed);}
					else{tempObject.setVelX(0);}
				}
			}
			
		}
	}
}
