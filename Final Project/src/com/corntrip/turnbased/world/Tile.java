package com.corntrip.turnbased.world;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.rendering.IRenderable;

public class Tile implements IRenderable
{
	private float x, y;
	private float width, height;

	/**
	 * A simple thing to render
	 * @param x The x position to draw at
	 * @param y The y position to draw at
	 * @param w The width of the tile to draw
	 * @param h The height of the tile to draw
	 */
	public Tile(float x, float y, float w, float h)
	{
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		renderWithOffset(gc, gfx, 0, 0);
	}
	
	@Override
	public void renderWithOffset(GameContainer gc, Graphics gfx, float offsetX, float offsetY) throws SlickException
	{
		gfx.setColor(new Color(0, 48, 100));
		gfx.fillRect(x - offsetX, y - offsetY, width, height);
		gfx.setColor(Color.black);
		gfx.drawRect(x - offsetX, y - offsetY, width, height);
	}
}
