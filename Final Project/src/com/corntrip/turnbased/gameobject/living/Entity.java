package com.corntrip.turnbased.gameobject.living;

import com.corntrip.turnbased.gameobject.GameObject;
import com.corntrip.turnbased.world.World;

public abstract class Entity extends GameObject
{
	private World world;
	
	public Entity(float startX, float startY, float w, float h, World world)
	{
		super(startX, startY, w, h);
		
		this.world = world;
	}
	
	public World getWorld() { return world; }
	public void setWorld(World w) { this.world = w; }
}
