package com.tutorial.main;

import java.util.Random;

public class Spawn {

	private Handler handler;
	private HUD hud;
	private Random r = new Random();

	private int score = 0;
	public static int levelUp = 250;
	public static int levelRequire = 250;
	static int coinTimer = 300;

	public static boolean bossPassed = false;
	public static boolean spawned = false;
	public static boolean noCoin = true;

	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}

	public void tick() {
		score = hud.getScore();

		//coin spawning
		if(coinTimer > 0) {
			coinTimer--;
		}
		else if(coinTimer <= 0 && noCoin) {
			coinTimer--;
			new Coin(r.nextInt(Game.WIDTH-16 -13), r.nextInt(Game.HEIGHT-40 -15 - 40) + 40, ID.Coin, handler);
			noCoin = false;
		}

		//level changing / enemy spawning
		if(score >= levelUp) {
			hud.setlevel(hud.getLevel()+1);

			if(!bossPassed) {
				if(Game.difficulty == 0) {
					if(hud.getLevel() == 2) {
						new BasicEnemy(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-56), ID.Enemy, handler);
					}
					else if(hud.getLevel() == 3) {
						new BasicEnemy(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-56), ID.Enemy, handler);
					}
					else if(hud.getLevel() == 4) {
						new FastEnemy(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-56), ID.Enemy, handler);
					}
					else if(hud.getLevel() == 5) {
						new SmartEnemy(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-56), ID.Enemy, handler);
					}
					else if(hud.getLevel() == 10) {
						handler.clearEnemies();
						new BossEnemy(Game.WIDTH/2 - 56, -96, ID.BossEnemy, handler);
						levelRequire = 1000;
					}
					else if(hud.getLevel() == 11) {
						bossPassed = true;
						handler.clearEnemies();
						levelRequire = 250;
					}
				}
				else {
					if(hud.getLevel() == 2) {
						new HardEnemy(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-56), ID.Enemy, handler);
					}
					else if(hud.getLevel() == 3) {
						new FastEnemy(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-56), ID.Enemy, handler);
					}
					else if(hud.getLevel() == 4) {
						new FastEnemy(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-56), ID.Enemy, handler);
					}
					else if(hud.getLevel() == 5) {
						new SmartEnemy(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-56), ID.Enemy, handler);
					}
					else if(hud.getLevel() == 10) {
						handler.clearEnemies();
						new BossEnemy(Game.WIDTH/2 - 56, -96, ID.BossEnemy, handler);
						levelRequire = 2000;
					}
					else if(hud.getLevel() == 11) {
						bossPassed = true;
						handler.clearEnemies();
						levelRequire = 500;
					}
				}
			}
			levelUp += levelRequire;
		}

		if(bossPassed && !spawned) {
			new BasicEnemy(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-56), ID.Enemy, handler);
			new BasicEnemy(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-56), ID.Enemy, handler);
			new FastEnemy(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-56), ID.Enemy, handler);
			new HardEnemy(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-56), ID.Enemy, handler);
			new SmartEnemy(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-56), ID.Enemy, handler);
			spawned = true;
		}
	}

	public void setLevelUp(int i) {
		levelUp = i;
	}

	public void setLevelRequire(int i) {
		levelRequire = i;
	}
}
