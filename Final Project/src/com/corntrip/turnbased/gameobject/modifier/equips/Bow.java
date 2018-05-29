package com.corntrip.turnbased.gameobject.modifier.equips;

import org.newdawn.slick.Image;

import com.corntrip.turnbased.gameobject.Entity;
import com.corntrip.turnbased.gameobject.modifier.equips.weaponUtil.Arrow;
import com.corntrip.turnbased.util.Resources;

public class Bow extends Weapon
{
	private int timeSinceLastShot = 0;
	private int waitTimesBetweenShots = 2000;
	
	public Bow(Entity owner, Image a, int tier)
	{
		setOwner(owner);
		setTier(tier);
		waitTimesBetweenShots = timeSinceLastShot = 2000 / tier;
		setDamage((float)(tier*4.212));
		setImage(a);
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
		return new Bow(getOwner(), getImage(), getTier() + 1);
	}
}
