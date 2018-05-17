package com.corntrip.turnbased.physics;

import com.corntrip.turnbased.util.Vector2;

public interface ICollidable
{
	public float getWidth();
	public float getHeight();
	
	public float getX();
	public float getY();
	
	public Vector2<Float, Float> getPosition();
	public Vector2<Float, Float> getDimensions();
	
	public boolean collidingWith(ICollidable other);
}
