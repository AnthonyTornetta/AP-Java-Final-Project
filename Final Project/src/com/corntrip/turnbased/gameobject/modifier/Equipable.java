package com.corntrip.turnbased.gameobject.modifier;

import com.corntrip.turnbased.gameobject.Entity;

public abstract class Equipable
{
	public abstract void modifyStats(Entity a);
	
	public abstract void setSprite(int x, int y, int height, int width);
}
//tacking on swords and stuff