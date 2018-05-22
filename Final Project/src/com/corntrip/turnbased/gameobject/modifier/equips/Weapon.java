package com.corntrip.turnbased.gameobject.modifier.equips;

import com.corntrip.turnbased.gameobject.modifier.Equipable;

public abstract class Weapon extends Equipable
{
	public float damage;
	public abstract void attack();
	public abstract float getDamage();
	public abstract Sword upgrade();
	
}