package com.corntrip.turnbased.gameobject.living;

import com.corntrip.turnbased.world.World;

public abstract class Enemy extends Entity
{
	private Entity target;
	
	public Enemy(float startX, float startY, float w, float h, World world, Entity target)
	{
		super(startX, startY, w, h, world);
		
		this.target = target;
	}
	
	public Entity getTarget() { return target; }
	public void setTarget(Entity t) { this.target = t; }
}
