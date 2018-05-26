package com.corntrip.turnbased.gameobject.modifier.equips;

import org.newdawn.slick.SpriteSheet;

import com.corntrip.turnbased.gameobject.Entity;
import com.corntrip.turnbased.gameobject.modifier.equips.weaponUtil.Arrows;

public class Bow extends Weapon
{

	public Bow(Entity owner, SpriteSheet a)
	{
		this.owner = owner;
		tier = 1;
		damage = (float) (tier*4.212);
	}
	
	@Override
	public void attack() 
	{
		Arrows a = new Arrows(owner.getX(), owner.getY(), owner.getWidth(), owner.getHeight(), owner.getWorld(), this);
		
	}

	@Override
	public float getDamage() 
	{return damage;}

	@Override
	public Bow upgrade() {
		tier++;
		return new Bow(owner, null);
	}

	@Override
	public SpriteSheet getSprite() {
		// TODO Auto-generated method stub
		return null;
	}

}
