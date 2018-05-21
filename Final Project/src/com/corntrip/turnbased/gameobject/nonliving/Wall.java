package com.corntrip.turnbased.gameobject.nonliving;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.corntrip.turnbased.gameobject.GameObject;

public class Wall extends GameObject
{
	/**
	 * Sits there and acts like a wall.
	 * @param startX The x to sit at
	 * @param startY The y to sit at
	 * @param w The width of the wall
	 * @param h The height of the wall
	 */
	public Wall(float startX, float startY, float w, float h)
	{
		super(startX, startY, w, h);
	}

	@Override
	public void renderWithOffset(GameContainer gc, Graphics gfx, float xOffset, float yOffset)
	{
		gfx.setColor(Color.gray);
		gfx.fillRect(getX() - xOffset, getY() - yOffset, getWidth(), getHeight());
	}

	@Override
	public boolean equals(Object other)
	{
		return false;
	}
}
