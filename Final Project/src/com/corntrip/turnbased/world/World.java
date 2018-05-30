/*
 * Anthony Tornetta & Troy Cope | P5 | 3/31/18
 * This is our own work: ACT & TC
 * Stores everything in a given scene and updates & renders them
 */

package com.corntrip.turnbased.world;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.gameobject.Entity;
import com.corntrip.turnbased.gameobject.GameObject;
import com.corntrip.turnbased.gameobject.living.Enemy;
import com.corntrip.turnbased.gameobject.living.LivingEntity;
import com.corntrip.turnbased.gameobject.living.TestEnemy;
import com.corntrip.turnbased.gameobject.nonliving.townhall.Townhall;
import com.corntrip.turnbased.rendering.Camera;
import com.corntrip.turnbased.rendering.IRenderable;
import com.corntrip.turnbased.util.Helper;
import com.corntrip.turnbased.util.Reference;
import com.corntrip.turnbased.util.Resources;

public class World implements IRenderable
{	
	/**
	 * Keeps track of the Entity to be treated as the player
	 */
	private LivingEntity player = null;
	
	/**
	 * Keeps track of the GameObject to be treated as the town hall
	 */
	private Townhall townhall = null;
	
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
											Reference.TILE_DIMENSIONS, Reference.TILE_DIMENSIONS, 
											Resources.getSpriteImage("tiles", (int)(Math.random() + 0.5), (int)(Math.random() + 0.5)));
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
		
		for(int y = (int) (camYOff / Reference.TILE_DIMENSIONS); y < (camYOff + cam.getScreenHeight()) / Reference.TILE_DIMENSIONS && y < HEIGHT / Reference.TILE_DIMENSIONS; y++)
		{
			for(int x = (int) (camXOff / Reference.TILE_DIMENSIONS); x < (camXOff + cam.getScreenWidth()) / Reference.TILE_DIMENSIONS && x < WIDTH / Reference.TILE_DIMENSIONS; x++)
			{
				tiles[y][x].render(gc, gfx, camXOff, camYOff);
			}
		}
		
		for(GameObject o : gameObjects)
		{
			if(!o.equals(getPlayer()))
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
		
		if(getPlayer() != null) // Render the player last so it's not behind walls n stuff
			getPlayer().render(gc, gfx, camXOff + passedXOff, camYOff + passedYOff);
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
		}
		
		if(player != null)
			cam.slippyCenter(player);
		
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
		
		int numOfEnemies = (int)Math.pow(6, wave * 0.5);
		
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
			
			Enemy e = new TestEnemy(x, y, enemyW, enemyH, this, getPlayer(), "Evil Ghost", Helper.clamp((int)Math.ceil(wave / 3 * Math.random()), 1, 5));
			
			System.out.println(e);
			
			addObject(e);
		}
	}
	
	/**
	 * Tells the world who to center the camera on and other things that are player-specific. This also adds the player to the object list.
	 * @param ent The Entity to treat as the player
	 */
	public void setPlayer(LivingEntity ent)
	{
		player = ent;
		cam.center(player);
		addObject(player);
	}
	
	/**
	 * Assigns the town hall in the world to be the given object, and adds it to the world's registered objects.
	 * @param th The Townhall to be added
	 */
	public void setTownhall(Townhall th)
	{
		addObject(th);
		townhall = th;
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
	
	public boolean containsObject(GameObject obj)
	{
		for(GameObject o : gameObjects)
			if(o.equals(obj))
				return true;
		return false;
	}
	
	public void initateNextWave()
	{
		System.out.println("spawning");
		spawnEnemies();
		wave++;
	}
	public int getWave() { return wave; }
	
	public List<GameObject> getGameObjects() { return gameObjects; }
	public List<Entity> getEntities() { return entities; }
	
	public LivingEntity getPlayer() { return player; }
	
	public Townhall getTownhall() { return townhall; }

	public float getWidth() { return WIDTH; }
	public float getHeight() { return HEIGHT; }
}
