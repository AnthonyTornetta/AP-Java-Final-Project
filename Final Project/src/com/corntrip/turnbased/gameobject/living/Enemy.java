package com.corntrip.turnbased.gameobject.living;

import com.corntrip.turnbased.world.World;

public abstract class Enemy extends LivingEntity
{
	/**
	 * The target to go after
	 */
	private LivingEntity target;
	
	private String name;
	
	/**
	 * The Antagonist of the story
	 * @param startX The starting x of the enemy
	 * @param startY The starting y of the enemy
	 * @param w The width of the player
	 * @param h The height of the player
	 * @param world The world the enemy is in
	 * @param target The target for the enemy (doesn't have to be the player)
	 */
	public Enemy(float startX, float startY, float w, float h, World world, LivingEntity target, String name)
	{
		super(startX, startY, w, h, world, 2);
		
		this.target = target;
		this.name = name;
	}
	
	// Getters & Setters //
	
	public LivingEntity getTarget() { return target; }
	public void setTarget(LivingEntity t) { this.target = t; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
}
