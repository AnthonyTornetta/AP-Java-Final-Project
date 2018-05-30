/*
 * Anthony Tornetta & Troy Cope | P5 | 3/31/18
 * This is our own work: ACT & TC
 * A simple wall that sits there and is a wall
 */

package com.corntrip.turnbased.gameobject.nonliving;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.corntrip.turnbased.gameobject.GameObject;

public class Wall extends GameObject
{
	private Image texture;
	
	/**
	 * Sits there and acts like a wall.
	 * @param startX The x to sit at
	 * @param startY The y to sit at
	 * @param w The width of the wall
	 * @param h The height of the wall
	 */
	public Wall(float startX, float startY, float w, float h, Image texture)
	{
		super(startX, startY, w, h);
		this.texture = texture;
	}

	@Override
	public void render(GameContainer gc, Graphics gfx, float xOffset, float yOffset)
	{
		texture.draw(getX() - xOffset, getY() - yOffset);
	}
}
