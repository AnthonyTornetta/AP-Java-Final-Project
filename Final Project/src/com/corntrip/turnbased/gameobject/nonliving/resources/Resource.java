/*
 * Anthony Tornetta & Troy Cope | P5 | 3/31/18
 * This is our own work: ACT & TC
 * Something that is collectible by the player and can be used to gain points
 */

package com.corntrip.turnbased.gameobject.nonliving.resources;

import com.corntrip.turnbased.gameobject.GameObject;

public abstract class Resource extends GameObject
{
	/**
	 * 
	 * @param startX: starting x
	 * @param startY: starting y
	 * @param w: width
	 * @param h: hieght
	 */
	public Resource(float startX, float startY, float w, float h)
	{
		super(startX, startY, w, h);
	}
	
	// Getters & Setters //
	
	public abstract int getPtsValue();
	public abstract Resource createNew();
}
