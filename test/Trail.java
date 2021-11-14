package test;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Trail extends GameObject {
	private float alpha = 1;
	private Color color;
	private float life;
	private Handler handler;
	private float width, height;   //life = 0.001 - 0.1
	
	public Trail(float x, float y, Color color,float width, float height, float life, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.color = color;
		this.width = width;
		this.height = height;
		this.life = life;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		if(alpha >life)
		{
			alpha -= life - 0.001f;
		}
		else
		{
			handler.removeObject(this);
		}
		
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		
		g.setColor(color);
		g.fillRect((int) x,(int)y,(int)width,(int)height);
		
		g2d.setComposite(makeTransparent(1));
		
	}
	
	public AlphaComposite makeTransparent(float alpha)
	{
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
