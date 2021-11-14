package test;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import test.Game.STATE;

public class KeyInput extends KeyAdapter
{
	private Handler handler;
	
	Game game;
	
	//system for checking if both down and up are pressed at same time
	private boolean upPressed = false;
	private boolean downPressed = false;
	private boolean leftPressed = false;
	private boolean rightPressed = false;
	
	public KeyInput(Handler handler, Game game)
	{
		this.handler = handler;
		
		this.game = game;
	}
	
	public void keyPressed(KeyEvent e ) 
	{
		int key = e.getKeyCode();
		
		for(int i =0; i<handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() ==ID.Player)
			{
				//all key events for player 1
				
				if(key == KeyEvent.VK_W)  
				{
					tempObject.setVelY(-5); upPressed = true;
				}
				if(key == KeyEvent.VK_A)  
				{
					tempObject.setVelX(-5); leftPressed = true;
				}
				if(key == KeyEvent.VK_S)  
				{
					tempObject.setVelY(5); downPressed = true;
				}
				if(key == KeyEvent.VK_D)  
				{
					tempObject.setVelX(5); rightPressed = true;
				}
				
			}
		}
		if(key == KeyEvent.VK_P)
		{
			if(game.gameState == STATE.Game)
			{
				if(Game.paused) Game.paused = false;
				else Game.paused = true;
			}
			
		}
		if(key == KeyEvent.VK_ESCAPE)
		{
			System.exit(1);
		}
		if(key == KeyEvent.VK_SPACE)
		{
			if(game.gameState == STATE.Game)
			{
				game.gameState = STATE.Shop;
			}
			else if(game.gameState == STATE.Shop)
			{
				game.gameState = STATE.Game;
			}
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		for(int i =0; i<handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() ==ID.Player)
			{
				//all key events for player 1
				
				if(key == KeyEvent.VK_W)  
				{
					upPressed = false;
					if(downPressed)
					{
						tempObject.setVelY(handler.spd);
					}
					else
					{
						tempObject.setVelY(0);
					}
				}
				if(key == KeyEvent.VK_A)  
				{
					leftPressed = false;
					if(rightPressed)
					{
						tempObject.setVelX(handler.spd);
					}
					else
					{
						tempObject.setVelX(0);
					}
				}
				if(key == KeyEvent.VK_S)  
				{
					downPressed = false;
					if(upPressed)
					{
						tempObject.setVelY(-handler.spd);
					}
					else
					{
						tempObject.setVelY(0);
					}
				}
				if(key == KeyEvent.VK_D)  
				{
					rightPressed = false;
					if(leftPressed)
					{
						tempObject.setVelX(-handler.spd);
					}
					else
					{
						tempObject.setVelX(0);
					}
				}
				
			}
		}
	}
	
}
