package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import test.Game.STATE;

public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private Random r = new Random();
	private HUD hud;
	
	public Menu(Game game, Handler handler, HUD hud)
	{
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}

	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.Menu)
		{
			//play button
			if(mouseOver(mx,my,210,150,200,64))
			{
				//game.gameState = STATE.Game;
				//handler.addObject(new Player(Game.WIDTH/2, Game.HEIGHT/2, ID.Player, handler));
				//handler.clearEnemies();
				//handler.addObject(new BasicEnemy(100, 100, ID.BasicEnemy, handler));
				game.gameState = STATE.Select;
				return;
			}
			
			//help button
			if(mouseOver(mx,my,210, 250, 200, 64))
			{
				game.gameState = STATE.Help;
			}
			
			//quit button
			if(mouseOver(mx,my,210, 350, 200, 64))
			{
				System.exit(1);
			}
		}
		
		if(game.gameState == STATE.Select)
		{
			//normal difficulty button
			if(mouseOver(mx,my,210,150,200,64))
			{
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2, Game.HEIGHT/2, ID.Player, handler));
				handler.clearEnemies();
				handler.addObject(new BasicEnemy(100, 100, ID.BasicEnemy, handler));
				
				game.diff = 0;
			}
			
			//hard difficulty button
			if(mouseOver(mx,my,210, 250, 200, 64))
			{
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2, Game.HEIGHT/2, ID.Player, handler));
				handler.clearEnemies();
				handler.addObject(new HardEnemy(100, 100, ID.BasicEnemy, handler));
				
				game.diff = 1;
			}
			
			//back button
			if(mouseOver(mx,my,210, 350, 200, 64))
			{
				game.gameState = STATE.Menu;
				return;
			}
		}
		
		
		//back button for help
		if(game.gameState == STATE.Help)
		{
			if(mouseOver(mx,my,210,350,200,64))
			{
				game.gameState = STATE.Menu;
				return;
			}
		}
		
		//try again button at game's end
		if(game.gameState == STATE.End)
		{
			if(mouseOver(mx,my,210,350,200,64))
			{
				game.gameState = STATE.Menu;
				hud.setLevel(1);
				hud.setScore(0);
			}
		}
		
	}
	public void mouseReleased(MouseEvent e)
	{
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height)
	{
		if(mx>x&& mx<x+width  &&my>y && my<y+height)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g)
	{
		if(game.gameState == STATE.Menu)
		{
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Wave", 240,70);
			
			g.setFont(fnt2);
			g.setColor(Color.WHITE);     //must set color before drawrect or it doesnt update
			g.drawRect(210, 150, 200, 64);
			g.drawString("Play", 270, 190);
			
			g.setColor(Color.WHITE);     //must set color before drawrect or it doesnt update
			g.drawRect(210, 250, 200, 64);
			g.drawString("Help", 270, 290);
			
			g.setColor(Color.WHITE);     //must set color before drawrect or it doesnt update
			g.drawRect(210, 350, 200, 64);
			g.drawString("Quit", 270, 390);
		}
		else if(game.gameState == STATE.Help)
		{
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 240, 70);
			
			g.setFont(fnt3);
			g.drawString("this is the help menu. use WASD to move and dodge", 40, 200);
			
			g.setFont(fnt2);
			g.drawRect(210,350,200,64);
			g.drawString("Back", 270, 390);
		}
		else if(game.gameState == STATE.End)
		{
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game Over", 180, 70);
			
			g.setFont(fnt3);
			g.drawString("You lost with a score of " +hud.getScore(), 175, 200);
			
			g.setFont(fnt2);
			g.drawRect(210,350,200,64);
			g.drawString("Try Again", 245, 390);
		} 
		else if(game.gameState == STATE.Select)
		{
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("SELECT DIFFICULTY", 140,70);
			
			g.setFont(fnt2);
			g.setColor(Color.WHITE);     //must set color before drawrect or it doesnt update
			g.drawRect(210, 150, 200, 64);
			g.drawString("NORMAL", 270, 190);
			
			g.setColor(Color.WHITE);     //must set color before drawrect or it doesnt update
			g.drawRect(210, 250, 200, 64);
			g.drawString("HARD", 270, 290);
			
			g.setColor(Color.WHITE);     //must set color before drawrect or it doesnt update
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 270, 390);
		}
		
	}
	
}
