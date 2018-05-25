package com.corntrip.turnbased.gameobject.modifier.equips.weaponUtil;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.gameobject.Entity;
import com.corntrip.turnbased.gameobject.living.LivingEntity;
import com.corntrip.turnbased.gameobject.modifier.equips.Weapon;
import com.corntrip.turnbased.world.World;

public abstract class Projectile extends Entity
{
	private Weapon wep;
	
	private float velX, velY;
	private float rot;
	
	public Projectile(float startX, float startY, float w, float h, World world, Weapon wep, float rotation)
	{
		super(startX, startY, w, h, world);
		this.wep = wep;
		rot = rotation;
	}
	
	public abstract float flightSpeed();
	
	public void endPath()
	{
		getWorld().removeObject(this);
	}
	
	public void advanceProjectile()
	{
		velX = rot / 90;
		if(velX > 1)
		{
			while(velX > 1)
			{
				velX--;
			}
			velY = 1-velX;
		}
		else if(velX < -1)
		{
			while(velX < -1)
			{
				velX++;
			}
			velY = 1+velX;
		}
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{
		advanceProjectile();
		
		ArrayList<Entity> enemiesHit = wep.generateHitbox(super.getX()+velX, super.getY()+velY, super.getWidth(), super.getHeight());
		
		for(Entity hitEnemy : enemiesHit)
		{
			if(hitEnemy != null)
			{
				((LivingEntity) hitEnemy).takeDamage((int)(wep.getDamage()));;
			}
		}
		
		/*Why doesn't this work?
		 * for(Entity a : wep.generateHitbox(super.getX()+velX, super.getY()+velY, super.getY(), super.getWidth()));
		 *	{
		 *		if( a instanceof LivingEntity)
		 *			((LivingEntity)a).takeDamage((int)wep.getDamage());
		 *	}
		 */
	}
}
