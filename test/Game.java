package test;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7249936825495199895L;
	public static final int WIDTH = 800, HEIGHT = WIDTH / 12 * 9;
	private Thread thread; // single thread game. but not recommended for other games, study on why
	private boolean running = false;
	public static boolean paused = false;
	public int diff = 0; // 0 = normal 1 = hard difficulty
	
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawn;
	private Menu menu;
	private Shop shop;
	

	public enum STATE
	{
		Menu,
		Help,
		Select,
		Shop,
		Game,
		End
	}
	
	public STATE gameState = STATE.Menu;
	
	public Game() {

		handler = new Handler();
		hud = new HUD();
		shop = new Shop(handler, hud);
		menu = new Menu(this,handler,hud);
		this.addKeyListener(new KeyInput(handler,this));
		this.addMouseListener(menu);
		this.addMouseListener(shop);
		
		
		new Window(WIDTH, HEIGHT, "Let's Build a Game!", this);
		
		spawn = new Spawn(handler, hud, this);
		r= new Random();
		
		if(gameState == STATE.Game)
		{
			handler.addObject(new Player(Game.WIDTH/2, Game.HEIGHT/2, ID.Player, handler));
			handler.addObject(new BasicEnemy(100, 100, ID.BasicEnemy, handler));
		}
		else
		{
			for(int i =0; i< 20; i++)
			{
				handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() // not original code for game loop but very "popular" apparently
	{
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running) {
				render();
			}
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	private void tick() {
		
		if(gameState==STATE.Game)
		{
			if(!paused)
			{
				hud.tick();
				spawn.tick();
				handler.tick();
				if(HUD.HEALTH <=0)
				{
					HUD.HEALTH  =100;
					
					gameState = STATE.End;
					handler.clearEnemies();
					handler.clearPlayer(); //we dont want the player to show at game over
					for(int i =0; i< 20; i++)
					{
						handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
					}
				}
			}
			
		}
		else if(gameState == STATE.Menu || gameState == STATE.End||gameState == STATE.Help|| gameState == STATE.Select)
		{
			menu.tick();
			handler.tick();
		}
		

	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(paused)
		{
			g.drawString("PAUSED", 100, 100);
		}
		
		if(gameState == STATE.Game)
		{
			hud.render(g);
			handler.render(g);
		}
		else if(gameState == STATE.Shop)
		{
			shop.render(g); 
		}
		else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select)
		{
			menu.render(g);
			handler.render(g);
		}
		
		g.dispose();
		bs.show();
	}

	public static float clamp(float x, int y, int z)
	{
		if(x<=y)
		{
			x=y;
		}
		else if(x >=z)	
		{
			x=z;
		}
		return x;
	}
	
	public static void main(String[] args) {
		new Game();
	}
}
