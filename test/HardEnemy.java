package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class HardEnemy extends GameObject{
	
	private Handler handler;
	
	private Random r = new Random();

	public HardEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX = 4;
		velY = 4;
	}
	
	@Override
	public void tick() {
		x+= velX;
		y+= velY;
		
		
		if(y<=0)
		{
			velY = r.nextInt(7)+5;
		}
		if(y>=Game.HEIGHT - 32)
		{
			velY = (r.nextInt(7)+5)*-1;
		}
		if(x<=0)
		{
			velX = r.nextInt(7)+5;
		}
		if(x>=Game.WIDTH - 16)
		{
			velX = (r.nextInt(7)+5)*-1;
		}
		handler.addObject(new Trail(x,y,Color.yellow , 16,16,0.02f,ID.Trail,handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int)x,(int) y, 16, 16);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}
	
}
