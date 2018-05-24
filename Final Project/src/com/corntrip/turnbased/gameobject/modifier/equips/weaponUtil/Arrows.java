package com.corntrip.turnbased.gameobject.modifier.equips.weaponUtil;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.gameobject.modifier.equips.Weapon;
import com.corntrip.turnbased.world.World;

public class Arrows extends Projectile
{

	public Arrows(float startX, float startY, float w, float h, World world, Weapon wep) {
		super(startX, startY, w, h, world, wep, wep.getOwner().getRotation());
		// TODO Auto-generated constructor stub
	}

	@Override
	public float flightSpeed() 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void render(GameContainer gc, Graphics gfx, float offsetX, float offsetY) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// TODO Auto-generated method stub
		
	}

}
