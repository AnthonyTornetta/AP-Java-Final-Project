package com.corntrip.turnbased.world;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.gameobject.GameObject;
import com.corntrip.turnbased.gameobject.living.Entity;
import com.corntrip.turnbased.rendering.Camera;
import com.corntrip.turnbased.rendering.IRenderable;
import com.corntrip.turnbased.util.Reference;

public class World implements IRenderable
{
	private Entity player = null;
	private List<GameObject> gameObjects = new ArrayList<>();
	private List<Entity> entities = new ArrayList<>();
	private Camera cam;
	
	private final int WIDTH, HEIGHT;
	private Tile[][] tiles;
	
	public World(int width, int height)
	{
		WIDTH = width;
		HEIGHT = height;
		
		int tilesH = HEIGHT / Reference.TILE_DIMENSIONS;
		int tilesW = WIDTH  / Reference.TILE_DIMENSIONS;
		
		tiles = new Tile[tilesH][tilesW];
		
		int ts = 0;
		
		for(int ty = 0; ty < tilesH; ty++)
		{
			for(int tx = 0; tx < tilesW; tx++)
			{
				tiles[ty][tx] = new Tile(tx * Reference.TILE_DIMENSIONS, ty * Reference.TILE_DIMENSIONS, 
											Reference.TILE_DIMENSIONS, Reference.TILE_DIMENSIONS);
				ts++;
			}
		}
		
		System.out.println(ts);
		
		cam = new Camera(0, 0, Reference.WINDOW_WIDTH, Reference.WINDOW_HEIGHT, WIDTH, HEIGHT);
	}
	
	public void setPlayer(Entity ent)
	{
		player = ent;
		cam.center(player);
	}
	
	public void update(GameContainer gc, int delta) throws SlickException
	{
		for(Entity e : entities)
		{
			e.update(gc, delta);
		}
		
		if(player != null)
			cam.slippyCenter(player);
	}
	
	public void addObject(GameObject obj)
	{
		if(obj instanceof Entity)
			entities.add((Entity)obj);
		
		gameObjects.add(obj);
	}
	
	public void removeObject(GameObject obj)
	{
		gameObjects.remove(obj);
	}
	
	public List<GameObject> getGameObjects()
	{
		return gameObjects;
	}
	
	public List<Entity> getEntities()
	{
		return entities;
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		float xoff = cam.getXOffset();
		float yoff = cam.getYOffset();
		
		for(int y = (int) (yoff / Reference.TILE_DIMENSIONS); y < (yoff + cam.getScreenHeight()) / Reference.TILE_DIMENSIONS; y++)
		{
			for(int x = (int) (xoff / Reference.TILE_DIMENSIONS); x < (xoff + cam.getScreenWidth()) / Reference.TILE_DIMENSIONS; x++)
			{
				tiles[y][x].renderWithOffset(gc, gfx, xoff, yoff);
			}
		}
		
		for(GameObject o : gameObjects)
			o.renderWithOffset(gc, gfx, xoff, yoff);
	}
	
	@Override
	public void renderWithOffset(GameContainer gc, Graphics gfx, float x, float y)
	{
		throw new UnsupportedOperationException("Cannot render the world with an offset.");
	}
}
