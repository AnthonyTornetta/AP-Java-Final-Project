package com.corntrip.turnbased;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.gameobject.living.Player;
import com.corntrip.turnbased.gameobject.nonliving.Wall;
import com.corntrip.turnbased.gameobject.nonliving.resources.GoldResource;
import com.corntrip.turnbased.gameobject.nonliving.resources.ResourceGenerator;
import com.corntrip.turnbased.util.Reference;
import com.corntrip.turnbased.util.Resources;
import com.corntrip.turnbased.world.World;
import com.corntrip.turnbased.world.WorldLoader;

public class GameNameHere extends BasicGame
{
	/*
	 * rotX = x + .5 (width / root(3))
	 * rotY = y + .5 (width / root(3))
	 * 
	 * 30 60 90 triangles
	 */
	
	private Resources res; // TODO: use me
	
	private World world;
	
	public GameNameHere()
	{
		super("a (good?) game.");
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
				
		try
		{
			world = WorldLoader.generateWorldFromImage(ImageIO.read(new File("res/map.png")));
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new IllegalStateException("Failed to Create World.");
		}
		
		world.setPlayer(new Player(50, 50, 50, 50, world));
		world.addObject(new Wall(200, 200, 50, 50));
		world.addObject(new ResourceGenerator(500, 500, 50, 50, world, 1000, new GoldResource(0, 0, 16, 16)));
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{
		world.update(gc, delta);
	}
}
