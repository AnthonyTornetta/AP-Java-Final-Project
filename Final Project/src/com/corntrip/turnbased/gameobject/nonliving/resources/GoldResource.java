package com.corntrip.turnbased.gameobject.nonliving.resources;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class GoldResource extends Resource
{
	public GoldResource(float startX, float startY, float w, float h)
	{
		super(startX, startY, w, h);
	}
	
	@Override
	public int getPtsValue()
	{
		return 1;
	}
	
	@Override
	public void renderWithOffset(GameContainer gc, Graphics gfx, float offsetX, float offsetY)
	{
		gfx.setColor(Color.yellow);
		gfx.fillRect(getX() - offsetX, getY() - offsetY, getWidth(), getHeight());
	}
	
	@Override
	public Resource createNew()
	{
		return new GoldResource(getX(), getY(), getWidth(), getHeight());
	}
}
