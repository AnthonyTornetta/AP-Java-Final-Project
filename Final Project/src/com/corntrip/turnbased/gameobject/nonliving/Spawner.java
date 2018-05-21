package com.corntrip.turnbased.gameobject.nonliving;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.gameobject.Entity;
import com.corntrip.turnbased.gameobject.living.LivingEntity;
import com.corntrip.turnbased.world.World;

public class Spawner extends Entity
{
	/**
	 * The LivingEntity to spawn
	 */
	private LivingEntity spawnable;
	
	/**
	 * Keeps track of the time passed since the last time this spawner spawned a LivingEntity
	 */
	private int timeSinceLastSpawn = 0;
	
	/**
	 * How often a LivingEntity should be spawned in milliseconds
	 */
	private int spawnRate;
	
	/**
	 * Spawns a given LivingEntity around the spawner
	 * @param startX The x of the spawner
	 * @param startY The y of the spawner
	 * @param w The width of the spawner
	 * @param h The height of the spawner
	 * @param world The world the spawner is in
	 * @param spawnRate Milliseconds between each spawn
	 * @param spawnable The LivingEntity to spawn **Must have a working clone() function**
	 */
	public Spawner(float startX, float startY, float w, float h,
					World world, int spawnRate, LivingEntity spawnable)
	{
		super(startX, startY, w, h, world);
		this.spawnRate = spawnRate;
		this.spawnable = spawnable;
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		renderWithOffset(gc, gfx, 0, 0);
	}

	@Override
	public void renderWithOffset(GameContainer gc, Graphics gfx, float offsetX, float offsetY)
	{
		gfx.setColor(Color.pink);
		gfx.drawRect(getX() - offsetX, getY() - offsetY, 30, 30);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{
		timeSinceLastSpawn += delta;
		
		if(timeSinceLastSpawn >= spawnRate)
		{
			LivingEntity spawned = spawnable.clone();
			timeSinceLastSpawn = 0;
			spawned.setX((int)(getX() + (Math.random() * 100) - 50));
			spawned.setY((int)(getY() + (Math.random() * 100) - 50));
			getWorld().addObject(spawned);
		}
	}
}
