package com.corntrip.turnbased.gameobject.modifier.equips.weaponUtil;

import com.corntrip.turnbased.gameobject.Entity;
import com.corntrip.turnbased.gameobject.modifier.equips.Weapon;
import com.corntrip.turnbased.world.World;

public abstract class Projectile extends Entity
{
	protected Weapon wep;
	
	public Projectile(float startX, float startY, float w, float h, World world)
	{
		super(startX, startY, w, h, world);
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
