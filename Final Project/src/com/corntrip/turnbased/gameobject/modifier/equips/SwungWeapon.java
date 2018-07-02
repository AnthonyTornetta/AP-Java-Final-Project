/*
 * Anthony Tornetta & Troy Cope | P5 | 3/31/18
 * This is our own work ACT & TC
 * A weapon that is swang'd instead of shot
 */

package com.corntrip.turnbased.gameobject.modifier.equips;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * A {@link Weapon} that is swung by the player to attack
 */
public abstract class SwungWeapon extends Weapon
{
	// Getting the place of the item and it's rotation
	private float x, y, width, height;
	private float rotation;
	
	// These two are dedicated to creating a limiter on swings
	private int timeSinceLastSwing;
	private int waitTimesBetweenSwings;
	
	/**
	 * A {@link Weapon} that is swung by the player to attack
	 * @param x Where the item's x will start
	 * @param y Where the item's y will start
	 * @param w How wide the object will be
	 * @param h How tall the object will be
	 * @param waitTime Time between swings in milliseconds
	 */
	public SwungWeapon(float x, float y, float w, float h, int waitTime)
	{
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.waitTimesBetweenSwings = timeSinceLastSwing = waitTime;
		rotation = 0;
	}
	
	/**
	 * Resets the swing to it's normal position
	 */
	@Override
	public void update(int delta)
	{
		if(rotation - 5 > 0)
			rotation -= 5;
		else
			rotation = 0;
		
		timeSinceLastSwing += delta;
	}
	
	/**
	 * Shows the actual image and it's movement when needed
	 */
	@Override
	public void renderAt(GameContainer gc, Graphics gfx, float x, float y)
	{
		gfx.rotate(x + getImage().getWidth() / 2, y + getImage().getHeight(), rotation);
		
		super.renderAt(gc, gfx, x, y);
	}
	
	// Tons of Getters & Setters //
	public float getX() { return x; }
	public float getY() { return y; }
	public float getWidth() { return width; }
	public float getHeight() { return height; }

	public void setX(float x) { this.x = x; }
	public void setY(float y) { this.y = y; }
	public void setWidth(float width) { this.width = width; }
	public void setHeight(float height) { this.height = height; }

	public float getRotation() { return rotation; }
	public void setRotation(float rotation) { this.rotation = rotation; }

	public int getTimeSinceLastSwing() { return timeSinceLastSwing; }
	public void setTimeSinceLastSwing(int t) { this.timeSinceLastSwing = t; }

	public int getWaitTimesBetweenSwings() { return waitTimesBetweenSwings; }
	public void setWaitTimesBetweenSwings(int w) { this.waitTimesBetweenSwings = w; }
}
