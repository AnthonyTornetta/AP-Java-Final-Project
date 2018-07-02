/*
 * Anthony Tornetta & Troy Cope | P5 | 3/31/18
 * This is our own work: ACT & TC
 * Something that is collidable in the scene with other objects
 */

package com.corntrip.turnbased.physics;

/**
 * Any object that can collide with others in the scene and has x, y coordinates and a width and height should implement this interface to denote this
 */
public interface ICollidable
{
	/**
	 * Sees if this collidable object is colliding with another collidable object
	 * @param other The object to check if the collision is happening with
	 * @return true if it is colliding, false if not
	 */
	public boolean collidingWith(ICollidable other);
	
	/**
	 * Checks if the specified position and dimensions would collide with another collidable object
	 * @param other The object to check if the collision is happening with
	 * @param x The x coordinate to check
	 * @param y The y coordinate to check
	 * @param width The width of the area to check
	 * @param height The height of the area to check
	 * @return true if it is colliding, false if not
	 */
	public boolean collidingWith(float x, float y, float width, float height);
	
	// Getters & Setters //
	public float getWidth();
	public float getHeight();
	
	public float getX();
	public float getY();
}
