package com.corntrip.turnbased.gameobject.nonliving.resources;

import com.corntrip.turnbased.gameobject.GameObject;

public abstract class Resource extends GameObject
{
	public Resource(float startX, float startY, float w, float h)
	{
		super(startX, startY, w, h);
	}
	
	public abstract int getPtsValue();
	public abstract Resource createNew();
}
