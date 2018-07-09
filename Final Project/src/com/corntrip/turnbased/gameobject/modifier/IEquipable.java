/*
 * Anthony Tornetta & Troy Cope | P5 | 3/31/18
 * This is our own work: ACT & TC
 * Something that can be held
 */

package com.corntrip.turnbased.gameobject.modifier;

import org.newdawn.slick.Image;

import com.corntrip.turnbased.gameobject.Entity;

/**
 * Anything that can be held by an {@link Entity}.
 */
public interface IEquipable
{	
	// Getters & Setters //
	public Image getImage();
	public Entity getOwner();
}
