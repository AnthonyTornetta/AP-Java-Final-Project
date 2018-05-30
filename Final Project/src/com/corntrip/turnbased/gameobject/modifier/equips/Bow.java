package com.corntrip.turnbased.gameobject.modifier.equips;

import com.corntrip.turnbased.gameobject.Entity;
import com.corntrip.turnbased.gameobject.modifier.equips.weaponUtil.Arrow;
import com.corntrip.turnbased.util.Resources;

public class Bow extends Weapon
{
	private int timeSinceLastShot = 0;
	private int waitTimesBetweenShots = 2000;
	
	/**
	 * 
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
	/**
	 * checks to make sure you're within attack time then creates a new arrow at the bow's centre
	 */
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
	/**
	 * delta is the game timer, increases as the game goes
	 */
	public void update(int delta)
	{
		timeSinceLastShot += delta;
	}
	
	@Override
	/**
	 * creates the new bow in the old one's place and upgrades it
	 */
	public Bow upgrade() 
	{
		return new Bow(getOwner(), getTier() + 1);
	}
	
	@Override
	/*
	 * makes sure the the tier is not higher than the max
	 */
	public boolean isMaxTier() { return getTier() >= 5; }
}
