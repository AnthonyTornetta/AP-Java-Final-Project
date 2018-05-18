package com.corntrip.turnbased.physics;

import com.corntrip.turnbased.util.Vector2;

public interface ICollidable
{
	/**
	 * Sees if this collidable object is colliding with another collidable object
	 * @param other The other object to check if it's colliding with
	 * @return true if it is colliding, false if not
	 */
	public boolean collidingWith(ICollidable other);
	
	/**
	 * Checks if the specified 
	 * @param other
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public boolean collidingWith(ICollidable other, float x, float y, float width, float height);
	
	// Getters & Setters //
	public float getWidth();
	public float getHeight();
	
	public float getX();
	public float getY();
	
	public Vector2<Float, Float> getPosition();
	public Vector2<Float, Float> getDimensions();
}
