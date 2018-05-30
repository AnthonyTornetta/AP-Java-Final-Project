/*
 * Anthony Tornetta & Troy Cope | P5 | 3/31/18
 * This is our own work: ACT & TC
 * Hits in a box around it's width, height, x, and y.
 */

package com.corntrip.turnbased.gameobject.modifier.equips;

import java.util.List;

import com.corntrip.turnbased.gameobject.Entity;
import com.corntrip.turnbased.gameobject.GameObject;
import com.corntrip.turnbased.gameobject.living.LivingEntity;
import com.corntrip.turnbased.gameobject.living.Player;
import com.corntrip.turnbased.util.Resources;

public class Sword extends SwungWeapon
{
	/**
	 * 
	 * @param x: x start
	 * @param y: y start
	 * @param w: width
	 * @param h: height
	 * @param owner: the entity who controls it
	 * @param tier: how upgraded the sword is
	 */
	public Sword(float x, float y, float w, float h, Entity owner, int tier)
	{
		super(x, y, w, h, 500 / tier);
		setTier(tier);
		setDamage((float) (tier*6.862));
		setOwner(owner);
		setImage(Resources.getSpriteImage("swords", tier - 1, 0));
	}
	
	@Override
	/**
	 * swings the sword and brings a hitbox to the game
	 * also adds the damage and kills items
	 */
	public void attack()
	{
		if(getTimeSinceLastSwing() < getWaitTimesBetweenSwings())
			return;
		setTimeSinceLastSwing(0);
		
		List<GameObject> thingsHit = generateHitbox(getX(), getY(), getWidth(), getHeight());
		
		for(GameObject hitEnemy : thingsHit)
		{
			if(hitEnemy instanceof LivingEntity)
			{
				((LivingEntity) hitEnemy).takeDamage((int)(getDamage()+0.5));;
				
				if(getOwner() instanceof Player)
				{
					Player p = (Player)getOwner();
					p.addXp(getTier() * 5);
				}
			}
		}
		
		setRotation(90);
	}
	
	@Override
	/**
	 * upgrades the sword like the bow
	 */
	public Sword upgrade() 
	{		
		return new Sword(getX(), getY(), getWidth(), getHeight(), getOwner(), getTier() + 1);
	}
	
	@Override
	/**
	 * max tier as seen before
	 */
	public boolean isMaxTier() { return getTier() >= 4; }
}
