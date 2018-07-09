/*
 * Anthony Tornetta & Troy Cope | P5 | 3/31/18
 * This is our own work: ACT & TC
 * Travels in a given direction based off the shooter's rotation with slight variation
 */

package com.corntrip.turnbased.gameobject.modifier.equips.weaponUtil;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.gameobject.Entity;
import com.corntrip.turnbased.gameobject.GameObject;
import com.corntrip.turnbased.gameobject.living.LivingEntity;
import com.corntrip.turnbased.gameobject.living.Player;
import com.corntrip.turnbased.gameobject.modifier.equips.Weapon;
import com.corntrip.turnbased.world.World;

/**
 * An {@link Entity} that travels in a fixed direction
 */
public abstract class Projectile extends Entity
{
	/**
	 * host item that uses the projectile
	 */
	private Weapon wep;
	
	/**
	 * velocities, used to calc next x and/or y and rotation
	 */
	private float velX, velY;
	
	/**
	 * Total distance traveled
	 */
	private float distanceTrav;
	
	/**
	 * Image to draw
	 */
	private Image image;
		
	/**
	 * An {@link Entity} that travels in a fixed direction
	 * @param startX: starting coord for Entity
	 * @param startY: same as x but y
	 * @param w: width
	 * @param h: height
	 * @param world: world the Entity is in
	 * @param wep: owner of the projecile (host)
	 * @param rotation: where the player is pointing in radians
	 */
	public Projectile(float startX, float startY, float w, float h, World world, Weapon wep, float rotation, Image image)
	{
		super(startX, startY, w, h, world);
		setRotation(rotation);
		this.image = image;
		this.wep = wep;
		distanceTrav = 0;
		
		setProjectileDirection();
	}
	
	/**
	 * how fast the projectile is going
	 * @return Better freaking be < 32
	 */
	public abstract float flightSpeed();
	
	/**
	 * sets how far the arrow will go
	 * @return sets how far the arrow will go
	 */
	public float flightMax()
	{
		return wep.getTier() * 600 * flightSpeed();
	}
	
	/**
	 * removes the projectile from the world
	 */
	public void endPath()
	{
		getWorld().removeObject(this);
	}
	
	/**
	 * uses super maths to set where the x,y will be aiming
	 */
	public void setProjectileDirection()
	{
		velX = (float) Math.cos(Math.toRadians(getRotation()));
		velY = (float) Math.sin(Math.toRadians(getRotation()));
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{
		//once it reaches it's distance it's removed from the world
		//the issue with this is that the diagonals will be shorter than the verticals, I think
		if(distanceTrav >= flightMax())
		{
			endPath();
		}
		
		//setting the new x,y coordinates according to the algorithm
		setX(getX() + velX * flightSpeed());
		setY(getY() + velY * flightSpeed());
		
		//checks the enemies hit
		List<GameObject> thingsHit = wep.generateHitbox(super.getX(), super.getY(), super.getWidth(), super.getHeight());
		
		//hits the first enemy and destroys the projectile
		if(thingsHit.size() > 0)
		{
			for(GameObject go : thingsHit)
			{
				if(!(go instanceof LivingEntity))
				{
					endPath();
					return;
				}
			}
			
			((LivingEntity)thingsHit.get(0)).takeDamage((int)(wep.getDamage()+0.5));
			
			if(getWeapon().getOwner() instanceof Player)
			{
				Player p = (Player)getWeapon().getOwner();
				p.addXp(1);
			}
			
			endPath();
		}
		
		//increases the current distance traveled
		distanceTrav += flightSpeed();
	}

	//get and sets
	public Image getImage() { return image; }

	public void setImage(Image image) { this.image = image;}

	public Weapon getWeapon() { return wep; }
}
