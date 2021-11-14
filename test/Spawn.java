package test;

import java.util.Random;

public class Spawn {
	private Handler handler;
	private HUD hud;
	private int scoreKeep =0;          //keeps score in the spawn +hud. idk if thats good.
	private Random r = new Random();
	private Game game;
	
	public Spawn(Handler handler, HUD hud, Game game)
	{
		this.handler = handler;
		this.hud = hud;
		this.game = game;
	}
	
	public void tick()
	{
		scoreKeep++;
		
		if(game.diff == 0)
		{
			if(scoreKeep >=500)
			{
				scoreKeep = 0;
				hud.setLevel(hud.getLevel() + 1);
				
				if(hud.getLevel() == 2)
				{
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
				}
				else if(hud.getLevel() ==3)
				{
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
				}
				else if(hud.getLevel() ==4)
				{
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));
				}
				else if(hud.getLevel() ==5)
				{
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT), ID.SmartEnemy, handler));
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT), ID.SmartEnemy, handler));
				}
				else if(hud.getLevel()==6)
				{
					handler.clearEnemies();
					handler.addObject(new EnemyBoss(Game.WIDTH/2-48, -120, ID.EnemyBoss, handler));
				}
			}
		}
		else if(game.diff ==1)
		{
			if(scoreKeep >=500)
			{
				scoreKeep = 0;
				hud.setLevel(hud.getLevel() + 1);
				
				if(hud.getLevel() == 2)
				{
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
				}
				else if(hud.getLevel() ==3)
				{
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT), ID.HardEnemy, handler));
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT), ID.HardEnemy, handler));
				}
				else if(hud.getLevel() ==4)
				{
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));
				}
				else if(hud.getLevel() ==5)
				{
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT), ID.SmartEnemy, handler));
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT), ID.SmartEnemy, handler));
				}
				else if(hud.getLevel()==6)
				{
					handler.clearEnemies();
					handler.addObject(new EnemyBoss(Game.WIDTH/2-48, -120, ID.EnemyBoss, handler));
				}
			}
		}
		
		
	}
	
}
