package com.corntrip.turnbased.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.rendering.IRenderable;

public abstract class GUIElement implements IRenderable
{
	private float x, y;
	
	public GUIElement(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		render(gc, gfx, 0, 0);
	}
	
	@Override
	public abstract void render(GameContainer gc, Graphics gfx, float offsetX, float offsetY) throws SlickException;
	
	//public abstract void handleKey(int key); <-- TODO: Put me somewhere else

	public float getX() { return x; }
	public void setX(float x) { this.x = x; }

	public float getY() { return y; }
	public void setY(float y) { this.y = y; }
}
