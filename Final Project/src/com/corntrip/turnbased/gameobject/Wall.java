package com.corntrip.turnbased.gameobject;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Wall extends GameObject
{
	public Wall(float startX, float startY, float w, float h)
	{
		super(startX, startY, w, h);
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{
		// sit here, and do nothing.
	}

	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		renderWithOffset(gc, gfx, 0, 0);
	}

	@Override
	public void renderWithOffset(GameContainer gc, Graphics gfx, float offsetX, float offsetY)
	{
		gfx.setColor(Color.gray);
		gfx.fillRect(offsetX + getX(), offsetY + getY(), getWidth(), getHeight());
	}
}
