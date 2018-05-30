/*
 * Anthony Tornetta & Troy Cope | P5 | 3/31/18
 * This is our own work: ACT & TC
 * A background Tile in the scene
 */

package com.corntrip.turnbased.world;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.rendering.IRenderable;

public class Tile implements IRenderable
{
	private float x, y;
	private float width, height;
	
	private Image texture;

	/**
	 * A simple thing to render
	 * @param x The x position to draw at
	 * @param y The y position to draw at
	 * @param w The width of the tile to draw
	 * @param h The height of the tile to draw
	 */
	public Tile(float x, float y, float w, float h, Image texture)
	{
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.texture = texture;
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		render(gc, gfx, 0, 0);
	}
	
	@Override
	/**
	 * draws the tile into the world
	 */
	public void render(GameContainer gc, Graphics gfx, float offsetX, float offsetY) throws SlickException
	{
		texture.draw(x - offsetX, y - offsetY);
	}

	// Getters & Setters //
	
	public float getX() { return x; }
	public void setX(float x) { this.x = x; }

	public float getY() { return y; }
	public void setY(float y) { this.y = y; }

	public float getWidth() { return width; }
	public void setWidth(float width) { this.width = width; }

	public float getHeight() { return height; }
	public void setHeight(float height) { this.height = height; }
}
