package test;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public int bounds =0; //extra pixels, which means bounds/2 = extra health
	public static float HEALTH = 100;
	
	private int score = 0;
	private int level = 1;
	
	public void tick()
	{
		
		HEALTH = Game.clamp(HEALTH, 0, 100+bounds/2);
		
		
		score++;
		
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200+bounds, 32);
		g.setColor(Color.getHSBColor( (1f * HEALTH) / 360, 1f, 1f));
		g.fillRect(15, 15, (int)HEALTH * 2, 32);     //1 point of health = 2 pixels wide
		g.setColor(Color.white);
		g.drawRect(15, 15, 200+bounds, 32);      // 200 because we start with 100 health 
		
		g.drawString("Score: "+score,15, 64);
		g.drawString("Level: "+level,15, 80);
		g.drawString("Space for Shop", 15, 96);
		g.drawString("p for pause", 15, 112);
	}
	
	public void setScore(int score)
	{
		this.score = score;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public void setLevel(int x)
	{
		level = x;
	}
}
