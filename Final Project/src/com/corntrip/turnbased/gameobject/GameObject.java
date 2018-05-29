/*
 * Anthony Tornetta & Troy Cope | P5 | 5/31/18
 * This is our own work except the Slick2D library - ACT & TC
 * The most general form of something in the game scene.
 */

package com.corntrip.turnbased.gameobject;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.physics.ICollidable;
import com.corntrip.turnbased.rendering.IRenderable;

public abstract class GameObject implements ICollidable, IRenderable, Cloneable
{
	/**
	 * Used to differentiate between objects
	 */
	private long objectId;
	
	/**
	 * Incremented every time a new GameObject is created.
	 * This allows for each object to have a unique id to differentiate it from objects of the same type.
	 */
	private static long masterId = 0;
	
	/**
	 * Position of the object
	 */
	private float x, y;
	
	/**
	 * Dimensions of the object
	 */
	private float width, height;
	
	private float rotation = 0;
	
	/**
	 * The most general form of something in the game scene.<br>
	 * @param startX The x the object will start at
	 * @param startY The y the object will start at
	 * @param w The width of the object
	 * @param h The height of the object
	 */
	public GameObject(float startX, float startY, float w, float h)
	{
		objectId = masterId;
		masterId++;
		
		x = startX;
		y = startY;
		
		width = w;
		height = h;
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		render(gc, gfx, 0, 0);
	}
	
	@Override
	public boolean collidingWith(ICollidable other)
	{
		return collidingWith(other.getX(), other.getY(), other.getWidth(), other.getHeight());
	}
	
	@Override
	public boolean collidingWith(float x, float y, float width, float height)
	{
		if(x + width >= getX() && x <= getX() + getWidth())
		{
			if(y + height >= getY() && y <= getY() + getHeight())
			{
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean equals(Object other)
	{
		if(other instanceof GameObject)
		{
			return ((GameObject)other).getObjectId() == getObjectId();
		}
		return false;
	}
	
	// Getters & Setters //
	
	@Override
	public float getWidth() { return width; }
	@Override
	public float getHeight() { return height; }
	@Override
	public float getX() { return x; }
	@Override
	public float getY() { return y; }
	
	public void setX(float x) { this.x = x; }
	public void setY(float y) { this.y = y; }
	public void setWidth(float w) { this.width = w; }
	public void setHeight(float h) { this.height = h; }
	
	public float getRotation()
	{
		return rotation;
	}
	
	public void setRotation(float rotation)
	{
		setRotation(rotation, getAnchorPointX(), getAnchorPointY());
	}
	
	public void setRotation(float rotation, float anchorX, float anchorY)
	{
		this.rotation = rotation;
	}	
	public long getObjectId() { return objectId; }
	
	public float getAnchorPointX() { return getAnchorPointX(0); }
	public float getAnchorPointY() { return getAnchorPointY(0); }
	public float getAnchorPointX(float off) { return getX() + getWidth() / 2 - off; }
	public float getAnchorPointY(float off) { return getY() + getHeight() / 2 - off; }
}
