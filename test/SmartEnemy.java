package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject{
	
	private Handler handler;
	private GameObject player;

	public SmartEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		for(int i =0; i<handler.object.size(); i++)
		{
			if(handler.object.get(i).getId() == ID.Player)
			{
				player= handler.object.get(i);
			}
		}
		
		velX = 4;
		velY = 4;
	}
	
	@Override
	public void tick() {
		x+= velX;
		y+= velY;
		
		float diffX = x-player.getX()-16;
		float diffY = y-player.getY()-16;
		float distance = (float) Math.sqrt((x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY()));
		velX = (float) ((-1.0/distance) * diffX);
		velY = (float) ((-1.0/distance) * diffY);
		
		handler.addObject(new Trail(x,y,Color.green , 16,16,0.02f,ID.Trail,handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y, 16, 16);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}
	
}
