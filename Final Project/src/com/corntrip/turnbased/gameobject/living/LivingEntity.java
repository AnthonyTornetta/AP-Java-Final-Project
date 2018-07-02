/*
 * Anthony Tornetta & Troy Cope | P5 | 3/31/18
 * This is our own work: ACT & TC
 * An entity that is also living, that has health and a max health and can die
 */

package com.corntrip.turnbased.gameobject.living;

import com.corntrip.turnbased.gameobject.Entity;
import com.corntrip.turnbased.util.Helper;
import com.corntrip.turnbased.world.World;

/**
 * An {@link Entity} that has health and can take damage n stuff
 */
public abstract class LivingEntity extends Entity
{
	private int maxHealth;
	private int health;
	
	/**
	 * An Entity but with health and a clone function
	 * @param startX The x position it starts at
	 * @param startY THe y position it starts at
	 * @param w The width of the LivingEntity
	 * @param h The height of the LivingEntity
	 * @param world The world the entity is in
	 */
	public LivingEntity(float startX, float startY, float w, float h, World world, int maxHealth)
	{
		super(startX, startY, w, h, world);
		health = maxHealth;
		this.maxHealth = maxHealth;
	}
	
	/**
	 * Heals the LivingEntity a given amount
	 * @param amt The amount to heal it by
	 */
	public void heal(int amt)
	{
		health = (int) Helper.clamp(amt + getHealth(), 0, getMaxHealth());
	}
	
	/**
	 * Deals damage to the LivingEntity, and if the health <= 0 it dies
	 * @param amt
	 */
	public void takeDamage(int amt)
	{
		health -= amt;
		if(health <= 0)
			die();
	}
	
	/**
=	 * Removes the LivingEntity from the world when it dies
	 */
	public void die()
	{
		getWorld().removeObject(this);
	}
	
	@Override
	public abstract LivingEntity clone();
	
	// Getters & Setters //
	
	public int getMaxHealth() { return maxHealth; }
	public void setMaxHealth(int maxHealth) { this.maxHealth = maxHealth; }
	
	public int getHealth() { return health; }
	public void setHealth(int health) { this.health = health; }
}
