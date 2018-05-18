package com.corntrip.turnbased.gameobject.nonliving;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.gameobject.GameObject;

public class Wall extends GameObject
{
	public Wall(float startX, float startY, float w, float h)
	{
		super(startX, startY, w, h);
	}

	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		renderWithOffset(gc, gfx, 0, 0);
	}

	@Override
	public void renderWithOffset(GameContainer gc, Graphics gfx, float xOffset, float yOffset)
	{
		gfx.setColor(Color.gray);
		gfx.fillRect(getX() - xOffset, getY() - yOffset, getWidth(), getHeight());
	}
}
