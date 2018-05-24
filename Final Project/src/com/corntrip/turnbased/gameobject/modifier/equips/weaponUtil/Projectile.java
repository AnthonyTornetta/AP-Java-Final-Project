package com.corntrip.turnbased.gameobject.modifier.equips.weaponUtil;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.gameobject.Entity;
import com.corntrip.turnbased.gameobject.modifier.equips.Weapon;
import com.corntrip.turnbased.world.World;

public abstract class Projectile extends Entity
{
	private Weapon wep;
	
	private float velX, velY;
	
	public Projectile(float startX, float startY, float w, float h, World world, Weapon wep, float rotation)
	{
		super(startX, startY, w, h, world);
		this.wep = wep;
	}
	
	public abstract float flightSpeed();
	
	public void endPath()
	{
		getWorld().removeObject(this);
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{
//		setX(getX() + flightSpeed() * velX);
//		setY(get)
		
		
	}
	
	//change this to the weapon items, like the bow's arrows
	//they should extend the weapon and 'be' it's hitbox
	public void shoot(float startX, float startY)
	{
		
	}
}
