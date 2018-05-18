package com.corntrip.turnbased;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.gameobject.living.Player;
import com.corntrip.turnbased.gameobject.nonliving.Wall;
import com.corntrip.turnbased.util.Reference;
import com.corntrip.turnbased.util.Resources;
import com.corntrip.turnbased.world.World;

public class GameNameHere extends BasicGame
{
	@SuppressWarnings("unused")
	private Resources res; // TODO: use me
	
	private World world;
	private Player p;
	
	public GameNameHere()
	{
		super("a (good?) game.");
		
		world = new World(960 * 2, 640 * 2);
		p = new Player(50, 50, 50, 50, world);
		world.setPlayer(p);
		
		world.addObject(p);
		world.addObject(new Wall(200, 200, 50, 50));
	}

	public static void main(String[] args)
	{
		try
        {
            AppGameContainer app = new AppGameContainer(new GameNameHere());
            app.setDisplayMode(Reference.WINDOW_WIDTH, Reference.WINDOW_HEIGHT, false);
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
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException
	{
		gc.setMaximumLogicUpdateInterval(Reference.MAX_FPS);
		gc.setTargetFrameRate(Reference.MAX_FPS);
		gc.setAlwaysRender(true);
		gc.setShowFPS(Reference.DEBUG);
		gc.setVSync(true);
		
		// Loads all the resources into memory [TODO]
		res = new Resources();
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{
		world.update(gc, delta);
	}
}
