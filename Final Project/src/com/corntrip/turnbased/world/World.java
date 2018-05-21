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
	
	@Override
	public void renderWithOffset(GameContainer gc, Graphics gfx, float passedXOff, float passedYOff)
	{
		float camXOff = cam.getXOffset();
		float camYOff = cam.getYOffset();
		
		for(int y = (int) (camYOff / Reference.TILE_DIMENSIONS); y < (camYOff + cam.getScreenHeight()) / Reference.TILE_DIMENSIONS; y++)
		{
			for(int x = (int) (camXOff / Reference.TILE_DIMENSIONS); x < (camXOff + cam.getScreenWidth()) / Reference.TILE_DIMENSIONS; x++)
			{
				tiles[y][x].renderWithOffset(gc, gfx, camXOff, camYOff);
			}
		}
		
		for(GameObject o : gameObjects)
			o.renderWithOffset(gc, gfx, camXOff + passedXOff, camYOff + passedYOff);
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		renderWithOffset(gc, gfx, 0, 0);
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
	
	/**
	 * Tells the world who to center the camera on and other things that are player-specific. This also adds the player to the object list.
	 * @param ent The Entity to treat as the player
	 */
	public void setPlayer(Entity ent)
	{
		player = ent;
		cam.center(player);
		
		addObject(player);
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
	
	public List<GameObject> getGameObjects() { return gameObjects; }
	public List<Entity> getEntities() { return entities; }
	
	public Entity getPlayer() { return player; }
}
