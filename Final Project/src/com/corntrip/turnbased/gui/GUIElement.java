/*
 * Anthony Tornetta & Troy Cope | P5 | 3/31/18
 * This is our own work: ACT & TC
 * Something that is renderable in the scene as a GUI element
 */

package com.corntrip.turnbased.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.rendering.IRenderable;

public abstract class GUIElement implements IRenderable
{
	private float x, y;
	
	/**
	 * Assigning the starting spot
	 * @param x; start x
	 * @param y; start y
	 */
	public GUIElement(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	@Override
	/**
	 * rendering with offesets
	 */
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		render(gc, gfx, 0, 0);
	}
	
	@Override
	/**
	 * rendering that'll be done with other GUI items
	 */
	public abstract void render(GameContainer gc, Graphics gfx, float offsetX, float offsetY) throws SlickException;
	
	//public abstract void handleKey(int key); <-- TODO: Put me somewhere else

	public float getX() { return x; }
	public void setX(float x) { this.x = x; }

	public float getY() { return y; }
	public void setY(float y) { this.y = y; }
}
