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
	public void render(GameContainer gc, Graphics gfx, float offsetX, float offsetY)
	{
		img.draw(getX() - offsetX, getY() - offsetY);
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{
		if(resourceLaidDown != null)
		{
			if(!getWorld().containsObject(resourceLaidDown))
			{
				resourceLaidDown = null;
			}
		}
		
		if(resourceLaidDown == null)
			timeSinceLastSpawn += delta; // Only spawn if no resource is present
		else
			timeSinceLastSpawn = 0;
		
		if(timeSinceLastSpawn >= spawnDelayMS)
		{
			resourceLaidDown = resource.createNew();
			timeSinceLastSpawn = 0;
			
			boolean colliding = false;
			
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
				
				
			} while(colliding);
			
			getWorld().addObject(resourceLaidDown);
		}
	}
}
