package com.corntrip.turnbased.rendering;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface IRenderable
{
	/**
	 * Draws the object at a position on the window
	 * @param gc The main game's container
	 * @param gfx The Graphics object to use to draw
	 * @throws SlickException Potentially thrown when drawing
	 */
	public void render(GameContainer gc, Graphics gfx) throws SlickException;
	
	/**
	 * Draws the object at a specified position on the window
	 * @param gc The main game's container
	 * @param gfx The Graphics object to use to draw
	 * @param x 
	 * @param y
	 * @throws SlickException Potentially thrown when drawing
	 */
	public void renderAt(GameContainer gc, Graphics gfx, float x, float y);
}
