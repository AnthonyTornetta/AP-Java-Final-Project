/*
 * Anthony Tornetta & Troy Cope | P5 | 3/31/18
 * This is our own work: ACT & TC
 * A projectile that is an arrow
 */

package com.corntrip.turnbased.gameobject.modifier.equips.weaponUtil;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.gameobject.modifier.equips.Weapon;
import com.corntrip.turnbased.world.World;

/**
 * A {@link Projectile} that on contact with a LivingEntity will deal damage to it.
 */
public class Arrow extends Projectile
{
	/**
	 * A {@link Projectile} that on contact with a LivingEntity will deal damage to it.
	 * @param startX Starting x coord for Projectile
	 * @param startY Starting y coord for Projectile
	 * @param w Width of Projectile
	 * @param h Height of Projectile
	 * @param world World the Projectile is in
	 * @param wep Owner of the Projectile (host)
	 * @param image The texture of the arrow
	 */
	public Arrow(float startX, float startY, float w, float h, World world, Weapon wep, Image image)
	{
		super(startX, startY, w, h, world, wep, wep.getOwner().getRotation(), image);	
	}

	/**
	 * Individual flight speed
	 */
	@Override
	public float flightSpeed() 
	{
		return 25.0f;
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx, float offsetX, float offsetY) throws SlickException
	{
		//rotating so it adds correctly and then drawing it
		gfx.rotate((getX() - offsetX) + getWidth() / 2, (getY() - offsetY) + getHeight() / 2, getRotation());
		getImage().draw(getX() - offsetX, getY() - offsetY);
	}
}
