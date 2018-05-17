package com.corntrip.turnbased;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import com.corntrip.turnbased.util.Reference;
import com.corntrip.turnbased.util.Resources;

public class GameNameHere extends BasicGame
{	
	public GameNameHere()
	{
		super("a (good?) game.");
	}

	public static void main(String[] args)
	{
		try
        {
            AppGameContainer app = new AppGameContainer(new GameNameHere());
            app.setDisplayMode(960, 640, false);
            app.start();
        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }
	}

	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		gfx.fill(new Rectangle(50, 50, 500, 50));
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException
	{
		gc.setMaximumLogicUpdateInterval(Reference.MAX_FPS);
		gc.setTargetFrameRate(Reference.MAX_FPS);
		gc.setAlwaysRender(true);
		gc.setShowFPS(Reference.DEBUG);
		gc.setVSync(true);
		
		// Loads all the resources into memory
		new Resources();
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{
		
	}
}
