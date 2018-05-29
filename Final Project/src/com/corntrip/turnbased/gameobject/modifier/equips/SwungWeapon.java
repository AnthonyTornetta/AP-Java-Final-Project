package com.corntrip.turnbased.gameobject.modifier.equips;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class SwungWeapon extends Weapon
{
	private float x, y, width, height;
	private float rotation;
	private int timeSinceLastSwing;
	private int waitTimesBetweenSwings;
	
	public SwungWeapon(float x, float y, float w, float h, int waitTime)
	{
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.waitTimesBetweenSwings = timeSinceLastSwing = waitTime;
		rotation = 0;
	}
	
	@Override
	public void update(int delta)
	{
		if(rotation > 0)
			rotation -= 5;
		else
			rotation = 0;
		
		timeSinceLastSwing += delta;
	}
	
	@Override
	public void renderAt(GameContainer gc, Graphics gfx, float x, float y)
	{
		gfx.rotate(x + getImage().getWidth() / 2, y + getImage().getHeight(), rotation);
		
		super.renderAt(gc, gfx, x, y);
	}
	
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
