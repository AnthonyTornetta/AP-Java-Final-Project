package com.corntrip.turnbased.gameobject;

import com.corntrip.turnbased.physics.ICollidable;
import com.corntrip.turnbased.rendering.IRenderable;
import com.corntrip.turnbased.util.Vector2;

public abstract class GameObject implements ICollidable, IRenderable
{
	/**
	 * Position of the object
	 */
	private float x, y;
	/**
	 * Dimensions of the object
	 */
	private float width, height;
	
	/**
	 * The most general form of something in the game scene.<br>
	 * @param startX The x the object will start at
	 * @param startY The y the object will start at
	 * @param w The width of the object
	 * @param h The height of the object
	 */
	public GameObject(float startX, float startY, float w, float h)
	{
		x = startX;
		y = startY;
		
		width = w;
		height = h;
	}
	
	@Override
	public boolean collidingWith(ICollidable other)
	{
		return collidingWith(other, getX(), getY(), getWidth(), getHeight());
	}
	
	@Override
	public boolean collidingWith(ICollidable other, float x, float y, float width, float height)
	{
		if(x + width >= other.getX() && x <= other.getX() + other.getWidth())
		{
			if(y + height >= other.getY() && y <= other.getY() + other.getHeight())
			{
				return true;
			}
		}
		return false;
	}
	
	// Getters & Setters
	
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

	@Override
	public Vector2<Float, Float> getPosition()
	{
		return new Vector2<>(x, y);
	}

	@Override
	public Vector2<Float, Float> getDimensions()
	{
		return new Vector2<>(width, height);
	}
}
