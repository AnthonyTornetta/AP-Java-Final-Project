package com.corntrip.turnbased.gameobject.modifier.equips.weaponUtil;

import com.corntrip.turnbased.gameobject.modifier.equips.Weapon;

public abstract class Projectile 
{
	protected float startX, startY;
	protected Weapon wep;
	
	public Projectile()
	{
		startX = wep.owner.getX();
		startY = wep.owner.getY();
	}
	
	public abstract float flightSpeed();
	
	public void endPath(Projectile p)
	{
		p = null;
	}
	
	//change this to the weapon items, like the bow's arrows
	//they should extend the weapon and 'be' it's hitbox
	public void shoot(Projectile p, float endX, float endY)
	{
		if(p.startX == endX)
			endPath(p);
		
		wep.generateHitbox();
	}
}
