package com.corntrip.turnbased.gameobject.modifier.equips;

import java.util.ArrayList;

import com.corntrip.turnbased.gameobject.Entity;
import com.corntrip.turnbased.gameobject.living.LivingEntity;
import com.corntrip.turnbased.gameobject.modifier.Equipable;

public abstract class Weapon extends Equipable
{
	public Entity owner;
	public float damage;
	
	
	public abstract void attack();
	public abstract float getDamage();
	public abstract Weapon upgrade();
	
	//Start x and y are the owner's hit box
	public ArrayList<Entity> generateHitbox(float startX, float startY, float width, float height)
	{
		ArrayList<Entity> itemsHit = new ArrayList<>();
		
		for(Entity a: owner.getWorld().getEntities())
		{
			if(a instanceof LivingEntity)
			{
				//hahah no longer hits owner
				if((!owner.equals(a) && ((LivingEntity)a).collidingWith(startX, startY, width, height)))
					itemsHit.add(a);
			}
		}
		return itemsHit;
	}
	
}