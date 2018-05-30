/*
 * Anthony Tornetta & Troy Cope | P5 | 3/31/18
 * This is our own work: ACT & TC
 * Creates a resources in the scene every time the time ellapsed is greator than the spawn delay
 */

package com.corntrip.turnbased.gameobject.nonliving.resources;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.gameobject.Entity;
import com.corntrip.turnbased.gameobject.GameObject;
import com.corntrip.turnbased.util.Resources;
import com.corntrip.turnbased.world.World;

public class ResourceGenerator extends Entity
{
	private int timeSinceLastSpawn = 0;
	private int spawnDelayMS;
	private Resource resource;
	private Resource resourceLaidDown = null;
	
	private Image img;
	
	/**
	 * 
	 * @param startX: starting x
	 * @param startY: starting y
	 * @param w: width
	 * @param h: hieght
	 * @param world: world that the generator exists in
	 * @param spawnDelayMS: delay in miliseconds
	 * @param resource: the items that it's spawning
	 */
	public ResourceGenerator(float startX, float startY, float w, float h, World world, int spawnDelayMS, Resource resource)
	{
		super(startX, startY, w, h, world);
		this.spawnDelayMS = spawnDelayMS;
		this.resource = resource;
		
		img = Resources.getImage("generator");
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		render(gc, gfx, 0, 0);
	}
	
	@Override
	/**
	 * brings the object into the game
	 */
	public void render(GameContainer gc, Graphics gfx, float offsetX, float offsetY)
	{
		img.draw(getX() - offsetX, getY() - offsetY);
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{
		//checks to see if it has a resource yet
		if(resourceLaidDown != null)
		{
			if(!getWorld().containsObject(resourceLaidDown))
			{
				resourceLaidDown = null;
			}
		}
		
		//resets the timer
		if(resourceLaidDown == null)
			timeSinceLastSpawn += delta; // Only spawn if no resource is present
		else
			timeSinceLastSpawn = 0;
		
		//throws a delay so there aren't too many resources
		if(timeSinceLastSpawn >= spawnDelayMS)
		{
			resourceLaidDown = resource.createNew();
			timeSinceLastSpawn = 0;
			
			boolean colliding = false;
			
			//places a resource into the world while keeping it out of the generator (randomly)
			do
			{
				colliding = false;
				resourceLaidDown.setX((int)(getX() + (Math.random() * 200) - 50));
				resourceLaidDown.setY((int)(getY() + (Math.random() * 200) - 50));
				
				for(GameObject go : getWorld().getGameObjects())
				{
					if(go.collidingWith(resourceLaidDown))
					{
						colliding = true;
						break;
					}
				}
				
				//creates a new one when colliding
			} while(colliding);
			
			getWorld().addObject(resourceLaidDown);
		}
	}
}
