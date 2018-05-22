package com.corntrip.turnbased.gameobject.nonliving.resources;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.gameobject.Entity;
import com.corntrip.turnbased.world.World;

public class ResourceGenerator extends Entity
{
	private int timeSinceLastSpawn = 0;
	private int spawnDelayMS;
	private Resource resource;
	
	public ResourceGenerator(float startX, float startY, float w, float h, World world, int spawnDelayMS, Resource resource)
	{
		super(startX, startY, w, h, world);
		this.spawnDelayMS = spawnDelayMS;
		this.resource = resource;
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		render(gc, gfx, 0, 0);
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx, float offsetX, float offsetY)
	{
		gfx.setColor(Color.pink);
		gfx.fillRect(getX() - offsetX, getY() - offsetY, getWidth(), getHeight());
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{
		timeSinceLastSpawn += delta;
		
		if(timeSinceLastSpawn >= spawnDelayMS)
		{
			Resource spawned = resource.createNew();
			timeSinceLastSpawn = 0;
			spawned.setX((int)(getX() + (Math.random() * 200) - 50));
			spawned.setY((int)(getY() + (Math.random() * 200) - 50));
			getWorld().addObject(spawned);
		}
	}
}
