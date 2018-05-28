package com.corntrip.turnbased.gameobject.living;

import com.corntrip.turnbased.gameobject.Entity;
import com.corntrip.turnbased.world.World;

public abstract class Enemy extends LivingEntity
{
	/**
	 * The target to go after
	 */
	private Entity target;
	
	/**
	 * The Antagonist of the story
	 * @param startX The starting x of the enemy
	 * @param startY The starting y of the enemy
	 * @param w The width of the player
	 * @param h The height of the player
	 * @param world The world the enemy is in
	 * @param target The target for the enemy (doesn't have to be the player)
	 */
	public Enemy(float startX, float startY, float w, float h, World world, Entity target)
	{
		super(startX, startY, w, h, world, 20);
		
		this.target = target;
	}
	
	// Getters & Setters //
	
	public Entity getTarget() { return target; }
	public void setTarget(Entity t) { this.target = t; }
}
