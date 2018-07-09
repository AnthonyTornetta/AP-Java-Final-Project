/*
 * Anthony Tornetta & Troy Cope | P5 | 3/31/18
 * This is our own work: ACT & TC
 * Shoots an arrow when fired
 */

package com.corntrip.turnbased.gameobject.modifier.equips;

import com.corntrip.turnbased.gameobject.Entity;
import com.corntrip.turnbased.gameobject.modifier.equips.weaponUtil.Arrow;
import com.corntrip.turnbased.util.Resources;

/**
 * A {@link Weapon} that fires an {@link Arrow} in the direction it is being held
 * @author Cornchip
 *
 */
public class Bow extends Weapon
{
	private int timeSinceLastShot = 0;
	private int waitTimesBetweenShots = 2000;
	
	/**
	 * A {@link Weapon} that fires an {@link Arrow} in the direction it is being held
	 * @param owner: who the weapon belongs to
	 * @param tier: what stage the weapon is in (how strong)
	 */
	public Bow(Entity owner, int tier)
	{
		setOwner(owner);
		setTier(tier);
		waitTimesBetweenShots = timeSinceLastShot = 2000 / tier;
		setDamage((float)(tier*4.212));
		setImage(Resources.getSpriteImage("bows", tier - 1, 0));
	}
	
	@Override
	public void attack() 
	{
		if(timeSinceLastShot < waitTimesBetweenShots)
			return;
		timeSinceLastShot = 0;
		
		getOwner().getWorld().addObject(new Arrow(getOwner().getX() + getOwner().getWidth() / 2,
							getOwner().getY() + getOwner().getHeight() / 2, 
							15.0f, 10.0f, 
							getOwner().getWorld(), this, Resources.getImage("arrow")));
	}
	
	@Override
	public void update(int delta)
	{
		timeSinceLastShot += delta;
	}
	
	@Override
	public Bow upgrade() 
	{
		return new Bow(getOwner(), getTier() + 1);
	}
	
	@Override
	public boolean isMaxTier() { return getTier() >= 5; }
}
