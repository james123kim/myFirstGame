package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBoss extends GameObject{
	
	private Handler handler;
	private Random r = new Random();

	private int timer =80;   //menacingly move into screen
	private int timer2 =50; //pause 
	
	public EnemyBoss(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX = 0;
		velY = 2;
	}
	
	@Override
	public void tick() {
		x+= velX;
		y+= velY;
		
		if(timer<=0) {velY=0; timer2--;}
		else timer--;
		
		if(timer2 <=0) 
		{
			if(velX ==0)
			{
				velX = 2;
			}
			int spawn = r.nextInt(10);
			if(spawn == 0)
			{
				handler.addObject(new EnemyBossBullet((int)x+48,(int)y+48, ID.BasicEnemy, handler));
			}
		}
		if(x<=0 || x>=Game.WIDTH - 96) //bounce
		{
			velX *=-1;
		}
		
		
		
		//handler.addObject(new Trail(x,y,Color.red ,96,96,0.008f,ID.Trail,handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x,(int) y, 96, 96);
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x,(int)y,96,96);
	}
	
}
