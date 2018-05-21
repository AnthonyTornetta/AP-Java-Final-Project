package com.corntrip.turnbased.gameobject;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.world.World;

public abstract class Entity extends GameObject
{
	/**
	 * For getting to know your surroundings
	 */
	private World world;
	
	/**
	 * A GameObject that has an update function that runs every game tick
	 * @param startX The starting x of the object
	 * @param startY The starting y of the object
	 * @param w The width of the object
	 * @param h The height of the object
	 * @param world The world the entity is in
	 */
	public Entity(float startX, float startY, float w, float h, World world)
	{
		super(startX, startY, w, h);
		
		this.world = world;
	}
	
	/**
	 * Called every game tick<br>
	 * This should NOT be used for rendering (but I guess you can if u really want)<br>
	 * Put any logic that needs to be called every frame in here
	 * @param gc The GameContainer this is being called from
	 * @param delta The total time passed since the last call
	 * @throws SlickException If something bad were to happen
	 */
	public abstract void update(GameContainer gc, int delta) throws SlickException;
	
	// Getters & Setters //
	
	public World getWorld() { return world; }
	public void setWorld(World w) { this.world = w; }
}
