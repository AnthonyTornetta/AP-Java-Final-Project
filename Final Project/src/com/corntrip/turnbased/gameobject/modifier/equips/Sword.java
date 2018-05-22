package com.corntrip.turnbased.gameobject.modifier.equips;

import org.newdawn.slick.SpriteSheet;

import com.corntrip.turnbased.gameobject.Entity;
import com.corntrip.turnbased.gameobject.living.LivingEntity;

public class Sword extends Weapon
{
	SpriteSheet swordSprite = null;
	Entity owner;
	
	public Sword(Entity owner, SpriteSheet a)
	{
		//swordSprite = ''
		tier = 1;
		damage = (float) (tier*6.862);
		this.owner = owner;
	}
	
	public SpriteSheet getSprite() 
	{ return swordSprite;}

	@Override
	public void attack() 
	{
		for(Entity a: owner.getWorld().getEntities())
		{
			if(a instanceof LivingEntity)
			{
				if(((LivingEntity)a).collidingWith(owner.getX(), owner.getY(), owner.getWidth(), -owner.getHeight()))
				{
					((LivingEntity) a).takeDamage((int)damage);;
				}
			}
		}
	}

	@Override
	public float getDamage()
		{return damage;}

	@Override
	public Sword upgrade() 
	{
		tier++;
		return new Sword(owner, null);
	}
}
