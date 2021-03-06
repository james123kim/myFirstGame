package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject{
	
	private Handler handler;

	public BasicEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX = 4;
		velY = 4;
	}
	
	@Override
	public void tick() {
		x+= velX;
		y+= velY;
		
		if(y<=0 || y>=Game.HEIGHT - 32)
		{
			velY *=-1;
		}
		if(x<=0 || x>=Game.WIDTH - 16)
		{
			velX *=-1;
		}
		
		handler.addObject(new Trail(x,y,Color.red , 16,16,0.02f,ID.Trail,handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x,(int) y, 16, 16);
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x,(int)y,16,16);
	}
	
}
