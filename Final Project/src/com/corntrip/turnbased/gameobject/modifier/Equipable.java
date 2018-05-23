package com.corntrip.turnbased.gameobject.modifier;

import org.newdawn.slick.SpriteSheet;

public abstract class Equipable
{
	public int tier;
	
	public abstract SpriteSheet getSprite();
}
//tacking on swords and stuff