package com.corntrip.turnbased.gameobject.modifier.equips.weaponUtil;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.gameobject.modifier.equips.Weapon;
import com.corntrip.turnbased.world.World;

public class Arrow extends Projectile
{
	//building of an arrow based off the projectile
	public Arrow(float startX, float startY, float w, float h, World world, Weapon wep, Image image)
	{
		super(startX, startY, w, h, world, wep, wep.getOwner().getRotation(), image);	
	}

	/**
	 * individual flight speed
	 */
	@Override
	public float flightSpeed() 
	{
		return 25.0f;
	}

	//actually adding the arrow to the game
	@Override
	public void render(GameContainer gc, Graphics gfx, float offsetX, float offsetY) throws SlickException
	{
		//rotating so it adds correctly and then drawing it
		gfx.rotate((getX() - offsetX) + getWidth() / 2, (getY() - offsetY) + getHeight() / 2, getRotation());
		getImage().draw(getX() - offsetX, getY() - offsetY);
	}
}
