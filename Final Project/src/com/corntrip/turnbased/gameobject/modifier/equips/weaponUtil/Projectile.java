package com.corntrip.turnbased.gameobject.modifier.equips.weaponUtil;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.gameobject.Entity;
import com.corntrip.turnbased.gameobject.living.LivingEntity;
import com.corntrip.turnbased.gameobject.modifier.equips.Weapon;
import com.corntrip.turnbased.world.World;

public abstract class Projectile extends Entity
{
	//host item that uses the projectile
	private Weapon wep;
	//velocities, used to calc next x and/or y and rotation
	private float velX, velY;
	private float rot;
	//total distance traveled
	private float distanceTrav;
	private Image image;
	
	/**
	 * 
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
		this.image = image;
		this.wep = wep;
		rot = rotation;
		distanceTrav = 0;
		
		setProjectileDirection();
	}
	
	/**
	 * how fast the projectile is going
	 * @return Better freaking be < 32
	 */
	public abstract float flightSpeed();
	
	/**
	 * 
	 * @return sets how far the arrow will go
	 */
	public float flightMax()
	{
		return 120*(flightSpeed());
	}
	
	/**removes the projectile from the world
	 * 
	 */
	public void endPath()
	{
		getWorld().removeObject(this);
	}
	
	/**uses super maths to set where the x,y will be aiming
	 * 
	 */
	public void setProjectileDirection()
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
		//once it reaches it's distance it's removed from the world
		//the issue with this is that the diagonals will be shorter than the verticals, I think
		if(distanceTrav < flightMax())
		{
			endPath();
		}
		
		//setting the new x,y coordinates according to the algorithm
		super.setX(getX() + velX*flightSpeed());
		super.setY(getY() + velY*flightSpeed());
		
		
		//checks the enemies hit
		List<Entity> enemiesHit = wep.generateHitbox(super.getX(), super.getY(), super.getWidth(), super.getHeight());
		
		//hits the first enemy and destroys the projectile
		if(enemiesHit.size() > 0)
		{
			((LivingEntity)enemiesHit.get(0)).takeDamage((int)(wep.getDamage()+0.5));
			endPath();
		}
		
		//increases the current distance traveled
		distanceTrav += flightSpeed();
	}

	public Image getImage() { return image; }

	public void setImage(Image image) { this.image = image;}
}
