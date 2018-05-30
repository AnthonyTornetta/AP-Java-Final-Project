/*
 * Anthony Tornetta & Troy Cope | P5 | 3/31/18
 * This is our own work: ACT & TC
 * A fun 2D game based on adventure and collecting resources
 */

package com.corntrip.turnbased;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.util.Reference;
import com.corntrip.turnbased.util.Resources;
import com.corntrip.turnbased.world.World;
import com.corntrip.turnbased.world.WorldLoader;

public class CornTrip extends BasicGame
{
	// (-|r| + 90) / 90 = f(r)?
	
	private World world;
	
	public CornTrip()
	{
		super("CornTrip");
	}
	
	public static void main(String[] args) throws SlickException
	{
        AppGameContainer app = new AppGameContainer(new CornTrip());
        app.setDisplayMode(Reference.WINDOW_WIDTH, Reference.WINDOW_HEIGHT, false);
        app.start();
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
		
		initializeResources();
		
		try
		{
			world = WorldLoader.generateWorldFromImage(ImageIO.read(new File("res/maps/map.png")));
		}
		catch (IOException e)
		{
			e.printStackTrace();
			throw new IllegalStateException("Failed to Create World.");
		}
	}
	
	private void initializeResources()
	{
		Resources.registerSpriteSheet("tiles", "tiles.png", Reference.TILE_DIMENSIONS, Reference.TILE_DIMENSIONS);
		Resources.registerSpriteSheet("bows", "bows.png", Reference.TILE_DIMENSIONS, Reference.TILE_DIMENSIONS);
		Resources.registerSpriteSheet("swords", "swords.png", Reference.TILE_DIMENSIONS, Reference.TILE_DIMENSIONS);
		Resources.registerSpriteSheet("enemy", "enemies.png", Reference.TILE_DIMENSIONS, Reference.TILE_DIMENSIONS);
		
		Resources.registerImage("player", "player.png");
		Resources.registerImage("arrow", "arrow.png");
		Resources.registerImage("wall", "wall.png");
		Resources.registerImage("enemy", "enemy.png");
		Resources.registerImage("tree", "tree.png");
		Resources.registerImage("gold", "gold.png");
		Resources.registerImage("deposit", "resource-deposit.png");
		Resources.registerImage("generator", "resource-generator.png");
		Resources.registerImage("townhall", "townhall.png");
		Resources.registerImage("health", "health.png");
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{
		world.update(gc, delta);
	}
}
