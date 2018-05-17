package com.corntrip.turnbased;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class GameNameHere extends BasicGame
{
	public GameNameHere()
	{
		super("a game.");
	}

	public static void main(String[] args)
	{
		try
        {
            AppGameContainer app = new AppGameContainer(new GameNameHere());
            app.setDisplayMode(300, 200, false);
            app.start();
        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException
	{
		
	}

	@Override
	public void init(GameContainer arg0) throws SlickException
	{
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException
	{
		System.out.println("asdf");
	}
}
