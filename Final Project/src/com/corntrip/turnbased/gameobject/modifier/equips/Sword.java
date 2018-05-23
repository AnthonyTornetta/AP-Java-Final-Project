package com.corntrip.turnbased.gameobject.modifier.equips;

import java.util.ArrayList;

import org.newdawn.slick.SpriteSheet;

import com.corntrip.turnbased.gameobject.Entity;
import com.corntrip.turnbased.gameobject.living.LivingEntity;

public class Sword extends Weapon
{
	SpriteSheet swordSprite = null;
	
	
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
		//test for damage and stuff until there is a better way
		ArrayList<Entity> enemiesHit = generateHitbox(owner.getX(), owner.getY(), owner.getWidth(), -owner.getHeight());
		
		for(Entity hitEnemy : enemiesHit)
		{
			if(hitEnemy != null)
			{
				((LivingEntity) hitEnemy).takeDamage((int)(damage+0.5));;
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
