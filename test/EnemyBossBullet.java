package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBossBullet extends GameObject{
	
	private Handler handler;
	Random r= new Random();

	private int timer =80;
	private int timer2 =50;
	
	public EnemyBossBullet(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX = r.nextInt(10)-5;
		velY = 5;
	}
	
	@Override
	public void tick() {
		x+= velX;
		y+= velY;
		
		/*if(y<=0 || y>=Game.HEIGHT - 32)
		{
			velY *=-1;
		}
		if(x<=0 || x>=Game.WIDTH - 16)
		{
			velX *=-1;
		}*/ 
		if(y >=Game.HEIGHT)
		{
			handler.removeObject(this);
		}
		
		
		
		handler.addObject(new Trail(x,y,Color.red ,16,16,0.02f,ID.Trail,handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect((int)x-4,(int) y-4, 24, 24);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}
	
}
