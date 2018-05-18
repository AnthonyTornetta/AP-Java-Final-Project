package com.corntrip.turnbased;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.gameobject.Player;
import com.corntrip.turnbased.gameobject.Wall;
import com.corntrip.turnbased.util.Reference;
import com.corntrip.turnbased.util.Resources;
import com.corntrip.turnbased.world.World;

public class GameNameHere extends BasicGame
{
	private World world;
	private Player p;
	private Wall wall;
	
	public GameNameHere()
	{
		super("a (good?) game.");
		
		world = new World(960, 640);
		
		p = new Player(50, 50, 50, 50, world);
		wall = new Wall(200, 200, 50, 50);
		
		world.addObject(p);
		world.addObject(wall);
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
		gfx.clear();
		world.render(gc, gfx);
		
		p.render(gc, gfx);
		wall.render(gc, gfx);
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
		p.update(gc, delta);
	}
}
