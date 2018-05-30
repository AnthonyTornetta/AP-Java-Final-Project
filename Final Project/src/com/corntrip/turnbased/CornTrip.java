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
		registerSpriteSheet("tiles", "tiles.png", Reference.TILE_DIMENSIONS, Reference.TILE_DIMENSIONS);
		registerSpriteSheet("bows", "bows.png", Reference.TILE_DIMENSIONS, Reference.TILE_DIMENSIONS);
		registerSpriteSheet("swords", "swords.png", Reference.TILE_DIMENSIONS, Reference.TILE_DIMENSIONS);
		
		registerImage("player", "player.png");
		registerImage("arrow", "arrow.png");
		registerImage("wall", "wall.png");
		registerImage("enemy", "enemy.png");
		registerImage("tree", "tree.png");
		registerImage("gold", "gold.png");
		registerImage("deposit", "resource-deposit.png");
		registerImage("generator", "resource-generator.png");
		registerImage("townhall", "townhall.png");
		registerImage("health", "health.png");
	}
	
	private void registerImage(String name, String location)
	{
		Resources.registerImage(name, Resources.loadImage(location));
	}
	
	private void registerSpriteSheet(String name, String location, int w, int h)
	{
		Resources.registerSpriteSheet(name, Resources.loadSpriteSheet(location, w, h));
	}
	
	@SuppressWarnings("unused")
	private void registerSound(String name, String location)
	{
		Resources.registerSound(name, Resources.loadSound(location));
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{
		world.update(gc, delta);
	}
}
