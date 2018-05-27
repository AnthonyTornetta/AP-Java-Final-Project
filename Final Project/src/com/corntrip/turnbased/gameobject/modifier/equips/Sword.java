package com.corntrip.turnbased.gameobject.modifier.equips;

import java.util.ArrayList;

import org.newdawn.slick.SpriteSheet;

import com.corntrip.turnbased.gameobject.Entity;
import com.corntrip.turnbased.gameobject.living.LivingEntity;

public class Sword extends Weapon
{	
	public Sword(Entity owner, SpriteSheet a)
	{
		//swordSprite = ''
		tier = 1;
		setDamage((float) (tier*6.862));
		setOwner(owner);
	}

	@Override
	public void attack() 
	{
		//test for damage and stuff until there is a better way
		ArrayList<Entity> enemiesHit = generateHitbox(getOwner().getX(), getOwner().getY(), getOwner().getWidth(), -getOwner().getHeight());
		
		for(Entity hitEnemy : enemiesHit)
		{
			if(hitEnemy != null)
			{
				((LivingEntity) hitEnemy).takeDamage((int)(getDamage()+0.5));;
			}
		}
	}

	@Override
	public Sword upgrade() 
	{
		tier++;
		return new Sword(getOwner(), null);
	}
}
