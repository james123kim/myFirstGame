package test; 

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

//player checks himself for collision

public class Player extends GameObject {
	
	private Handler handler;
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x,(int)y,32,32);
	}

	@Override
	public void tick() { // i didnt use clamping here but i can.i implemented my own clamping here
		
		
		
		if(x<=0 && velX <=0 || x>=Game.WIDTH-48 && velX >=0)
		{
			
		}
		else
		{
			x+=velX;
		}
		if(y<=0 && velY <=0 || y>=Game.HEIGHT- 72&& velY >=0)
		{
			
		}
		else
		{
			y+=velY;
		}
		
		collision();
	}
	
	private void collision()
	{
		for(int i=0; i< handler.object.size(); i++)
		{
			GameObject tempObj = handler.object.get(i);
			
			if(tempObj.getId() == ID.BasicEnemy || tempObj.getId() == ID.FastEnemy || tempObj.getId() == ID.SmartEnemy|| tempObj.getId() == ID.HardEnemy)
			{
				if(getBounds().intersects(tempObj.getBounds()))
				{
					//collision code
					HUD.HEALTH -=2;
				}
			}
			if(tempObj.getId() == ID.EnemyBoss)
			{
				if(getBounds().intersects(tempObj.getBounds()))
				{
					//collision code
					HUD.HEALTH -=999;
				}
			}
			
		}
	}

	@Override
	public void render(Graphics g) {
		
		if(id == ID.Player)
		{
			g.setColor(Color.white);
		}
		g.fillRect((int)x,(int)y,32,32);
		
	}

}
