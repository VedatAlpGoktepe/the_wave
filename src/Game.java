package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1550691097823471818L;

	public static final int WIDTH = 640, HEIGHT = WIDTH/12 * 9, MIDDLEW = WIDTH/2-8, MIDDLEH = HEIGHT/2-20;

	private Thread thread;
	private boolean running = false;

	public static boolean paused = false;
	public static int difficulty = 0; //0 = normal, 1 = hard

	private Random r = new Random();
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private Shop shop;

	public static BufferedImageLoader playerImage = new BufferedImageLoader("res/player.png");
	public static BufferedImageLoader basicEnemyImage = new BufferedImageLoader("res/basicEnemy.png");
	public static BufferedImageLoader fastEnemyImage = new BufferedImageLoader("res/fastEnemy.png");
	public static BufferedImageLoader smartEnemyImage = new BufferedImageLoader("res/smartEnemy.png");
	public static BufferedImageLoader hardEnemyImage = new BufferedImageLoader("res/hardEnemy.png");
	public static BufferedImageLoader boss1Image = new BufferedImageLoader("res/boss.png");
	public static BufferedImageLoader bossBulletImage = new BufferedImageLoader("res/bossBullet.png");
	public static BufferedImageLoader boss2Image = new BufferedImageLoader("res/boss2Latest.png");
	public static BufferedImageLoader coinImage = new BufferedImageLoader("res/coin.png");
	public static BufferedImageLoader enemyV2Image = new BufferedImageLoader("res/enemyV2.png");

	public enum STATE {
		Menu,
		Help,
		Select,
		Game,
		Shop,
		End;
	}

	public static STATE gameState = STATE.Menu;

	public Game() {
		handler = new Handler();
		hud = new HUD();
		spawner = new Spawn(handler, hud);
		menu = new Menu(handler, hud);
		shop = new Shop(handler, hud);

		this.addMouseListener(menu);
		this.addMouseListener(shop);
		this.addKeyListener(new KeyInput(handler));

		new Window(WIDTH, HEIGHT, "Java Game Woot Woot", this);

		AudioPlayer.playAudioLoop("res/RERUN.wav");

		if(gameState == STATE.Game) {
			new Player(WIDTH/2 - 24, HEIGHT/2 - 36, ID.Player, handler);
			new BasicEnemy(r.nextInt(WIDTH-32), r.nextInt(HEIGHT-56), ID.Enemy, handler);
		}
		else {
			for(int i = 0; i < 15; i++) {
				new MenuParticle(r.nextInt(WIDTH-32), r.nextInt(HEIGHT-56), ID.MenuParticle, handler) ;
			}
		}
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running) {
				render();
			}
			frames++;

			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	private void tick() {
		shop.tick();
		if(gameState == STATE.Game) {
			if(!paused) {
				handler.tick();
				hud.tick();
				spawner.tick();

				if(HUD.HEALTH <= 0) {
					HUD.HEALTH = 100;
					hud.setlevel(1);
					spawner.setLevelUp(250);
					handler.clearAll();
					KeyInput.playerSpeed = 5;
					Shop.money = 0;
					Spawn.noCoin = true;
					Spawn.bossPassed = false;
					Spawn.spawned = false;
					KeyInput.uP = false;
					KeyInput.dP = false;
					KeyInput.lP = false;
					KeyInput.rP = false;
					gameState = STATE.End;
					for(int i = 0; i < 15; i++) {
						new MenuParticle(r.nextInt(WIDTH-32), r.nextInt(HEIGHT-56), ID.MenuParticle, handler) ;
					}
				}
			}
		}
		else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.Select || gameState == STATE.End) {
			handler.tick();
			menu.tick();
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		if(paused) {
			g.setColor(Color.lightGray);
			g.setFont(new Font("arial", 1, 40));
			g.drawString("PAUSED", WIDTH/2 - 85, HEIGHT/2 - 50);
			g.setFont(new Font("arial", 1, 30));
			g.drawString("Press P or Esc to continue", WIDTH/2 - 190, HEIGHT/2);
		}

		if(gameState == STATE.Game) {
			handler.render(g);
			hud.render(g);
		}
		else if(gameState == STATE.Shop) {
			shop.render(g);
		}
		else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.Select || gameState == STATE.End) {
			handler.render(g);
			menu.render(g);
		}

		g.dispose();
		bs.show();
	}

	public static void fontDefault(Graphics g) {
		g.setFont(new Font("dialog", 0, 12));
	}

	public static float clamp(float var, float min, float max) {
		if(var <= min) {return min;}
		else if(var >= max) {return max;}
		else {return var;}
	}

	public static void main(String[] args) {
		new Game();
	}

}
