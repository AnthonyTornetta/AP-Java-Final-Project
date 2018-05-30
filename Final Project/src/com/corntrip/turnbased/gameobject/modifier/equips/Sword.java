package com.corntrip.turnbased.gameobject.modifier.equips;

import java.util.ArrayList;

import org.newdawn.slick.Image;

import com.corntrip.turnbased.gameobject.Entity;
import com.corntrip.turnbased.gameobject.living.LivingEntity;
import com.corntrip.turnbased.gameobject.living.Player;

public class Sword extends SwungWeapon
{
	public Sword(float x, float y, float w, float h, Entity owner, Image a, int tier)
	{
		super(x, y, w, h, 500 / tier);
		setTier(tier);
		setDamage((float) (tier*6.862));
		setOwner(owner);
		setImage(a);
	}
	
	@Override
	public void attack()
	{
		if(getTimeSinceLastSwing() < getWaitTimesBetweenSwings())
			return;
		setTimeSinceLastSwing(0);
		
		ArrayList<Entity> enemiesHit = generateHitbox(getX(), getY(), getWidth(), getHeight());
		
		for(Entity hitEnemy : enemiesHit)
		{
			if(hitEnemy instanceof LivingEntity)
			{
				((LivingEntity) hitEnemy).takeDamage((int)(getDamage()+0.5));;
				
				if(getOwner() instanceof Player)
				{
					Player p = (Player)getOwner();
					p.addXp(getTier() * 5);
				}
			}
		}
		
		setRotation(90);
	}
	
	@Override
	public Sword upgrade() 
	{		
		return new Sword(getX(), getY(), getWidth(), getHeight(), getOwner(), getImage(), getTier() + 1);
	}
}
