package com.corntrip.turnbased.world;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.gameobject.Entity;
import com.corntrip.turnbased.gameobject.GameObject;
import com.corntrip.turnbased.gameobject.living.Enemy;
import com.corntrip.turnbased.gameobject.living.TestEnemy;
import com.corntrip.turnbased.rendering.Camera;
import com.corntrip.turnbased.rendering.IRenderable;
import com.corntrip.turnbased.util.Reference;

public class World implements IRenderable
{	
	/**
	 * Keeps track of the Entity to be treated as the player
	 */
	private Entity player = null;
	
	/**
	 * Every GameObject in the scene (including Entities) is stored in here
	 */
	private List<GameObject> gameObjects = new ArrayList<>();
	
	/**
	 * Every Entity in the scene is stored here (each entity will also have a copy in the gameObjects List)
	 */
	private List<Entity> entities = new ArrayList<>();
	
	/**
	 * The Camera used when rendering the world; if no player is defined it simply remains in its default position
	 */
	private Camera cam;
	
	/**
	 * Time in ms since the last spawn
	 */
	private int timeSinceLastSpawn = 0;
	
	/**
	 * Keeps track of which wave of enemies it is on
	 */
	private int wave = 1;
	
	/**
	 * The dimensions of the world
	 */
	private final int WIDTH, HEIGHT;
	
	/**
	 * Every tile in the world
	 */
	private Tile[][] tiles;
	
	/**
	 * Keeps track of objects to be removed after being messed with
	 */
	private List<GameObject> objectsToBeRemoved = new ArrayList<>();
	
	/**
	 * A world holds every object in a scene and handles rendering and updating them all
	 * @param width The total width of the world
	 * @param height The total height of the world
	 */
	public World(int width, int height)
	{
		WIDTH = width;
		HEIGHT = height;
		
		int tilesAmtY = HEIGHT / Reference.TILE_DIMENSIONS;
		int tilesAmtX = WIDTH  / Reference.TILE_DIMENSIONS;
		
		tiles = new Tile[tilesAmtY][tilesAmtX];
		
		// Fills the tiles array with new tiles (TODO: Replace with an image-loaded scene)
		for(int ty = 0; ty < tilesAmtY; ty++)
		{
			for(int tx = 0; tx < tilesAmtX; tx++)
			{
				tiles[ty][tx] = new Tile(tx * Reference.TILE_DIMENSIONS, ty * Reference.TILE_DIMENSIONS, 
											Reference.TILE_DIMENSIONS, Reference.TILE_DIMENSIONS);
			}
		}
		
		if(Reference.DEBUG)
			System.out.println("Total Tiles: " + tiles.length * tiles[0].length);
		
		cam = new Camera(0, 0, Reference.WINDOW_WIDTH, Reference.WINDOW_HEIGHT, WIDTH, HEIGHT);
	}
	
	/**
	 * Renders each object in the world with a specified offset added to the camera's offset
	 */
	@Override
	public void render(GameContainer gc, Graphics gfx, float passedXOff, float passedYOff) throws SlickException
	{
		float camXOff = cam.getXOffset();
		float camYOff = cam.getYOffset();
		
		for(int y = (int) (camYOff / Reference.TILE_DIMENSIONS); y < (camYOff + cam.getScreenHeight()) / Reference.TILE_DIMENSIONS; y++)
		{
			for(int x = (int) (camXOff / Reference.TILE_DIMENSIONS); x < (camXOff + cam.getScreenWidth()) / Reference.TILE_DIMENSIONS; x++)
			{
				tiles[y][x].render(gc, gfx, camXOff, camYOff);
			}
		}
		
		for(GameObject o : gameObjects)
		{
			if(o.getX() + o.getWidth() > cam.getXOffset() && o.getX() < cam.getXOffset() + cam.getScreenWidth())
			{
				if(o.getY() + o.getHeight() > cam.getYOffset() && o.getY() < cam.getYOffset() + cam.getScreenHeight())
				{
					gfx.pushTransform(); // Makes sure this drawing doesn't mess w/ any others
					o.render(gc, gfx, camXOff + passedXOff, camYOff + passedYOff);
					gfx.popTransform();
				}
			}
		}
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		render(gc, gfx, 0, 0);
	}
	
	public void update(GameContainer gc, int delta) throws SlickException
	{
		for(int i = 0;  i < entities.size(); i++)
		{
			Entity e = entities.get(i);
			e.update(gc, delta);
			if(i >= entities.size() || !entities.get(i).equals(e))
			{
				i--; // The entity was removed from the world; TODO: Do this a bettwe way
			}
		}
		
		if(player != null)
			cam.slippyCenter(player);
		
		timeSinceLastSpawn += delta;
		if(timeSinceLastSpawn > 1000)
		{
			//spawnEnemies();
			timeSinceLastSpawn = 0;
			wave++;
		}
		
		while(objectsToBeRemoved.size() > 0)
		{
			GameObject go = objectsToBeRemoved.remove(0);
			removeObjectUnsafely(go);
		}
	}
	
	public void spawnEnemies()
	{
		int enemyW = 32;
		int enemyH = 32;
		
		int numOfEnemies = (int)Math.pow(6, wave * 0.1);

		for(int i = 0; i < numOfEnemies; i++)
		{
			float x, y;
			
			// Loop for searching for a good position
			searchLoop:
			do
			{
				x = (float) (Math.random() * WIDTH);
				y = (float) (Math.random() * HEIGHT);
				
				// Make sure it's not colliding with any objects in the scene
				for(GameObject go : gameObjects)
				{
					if(go.collidingWith(x, y, enemyW, enemyH))
					{
						continue searchLoop;
					}
				}
			} while(x + enemyW >= cam.getXOffset() && x <= cam.getXOffset() + cam.getScreenWidth() 
				 && y + enemyH >= cam.getYOffset() && y <= cam.getYOffset() + cam.getScreenHeight()); // Makes sure it won't spawn in the player's viewpoint
			
			Enemy e = new TestEnemy(x, y, enemyW, enemyH, this, getPlayer());
			addObject(e);
		}
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
	
	/**
	 * Adds an object to be handled by the world
	 * @param obj The object to add to the world
	 */
	public void addObject(GameObject obj)
	{
		if(obj instanceof Entity)
			entities.add((Entity)obj);
		
		gameObjects.add(obj);
	}
	
	/**
	 * Removes an object from the world after the world is done updating
	 * @param obj The object to remove
	 */
	public void removeObject(GameObject obj)
	{
		objectsToBeRemoved.add(obj);
	}
	
	/**
	 * Removes an object from the world regardless of if things are updating or not
	 * @param obj The object to remove
	 */
	private void removeObjectUnsafely(GameObject obj)
	{
		gameObjects.remove(obj);
		if(obj instanceof Entity)
			entities.remove(obj);
	}
	
	public List<GameObject> getGameObjects() { return gameObjects; }
	public List<Entity> getEntities() { return entities; }
	
	public Entity getPlayer() { return player; }

	public float getWidth() { return WIDTH; }
	public float getHeight() { return HEIGHT; }
}
