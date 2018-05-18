package com.corntrip.turnbased.gameobject.living;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

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
	
	public abstract void update(GameContainer gc, int delta) throws SlickException;
	
	public World getWorld() { return world; }
	public void setWorld(World w) { this.world = w; }
}
