package com.corntrip.turnbased.physics;

import com.corntrip.turnbased.util.Vector2;

public interface ICollidable
{
	public double getWidth();
	public double getHeight();
	
	public double getX();
	public double getY();
	
	public Vector2<Double, Double> getPosition();
	public Vector2<Double, Double> getDimensions();
	
	public boolean collidingWith(ICollidable other);
}
