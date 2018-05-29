package com.corntrip.turnbased.gameobject.nonliving.resources;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.corntrip.turnbased.util.Resources;

public class GoldResource extends Resource
{
	private Image img;
	
	public GoldResource(float startX, float startY, float w, float h)
	{
		super(startX, startY, w, h);
		
		img = Resources.getImage("gold");
	}
	
	@Override
	public int getPtsValue()
	{
		return 1;
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx, float offsetX, float offsetY)
	{
		img.draw(getX() - offsetX, getY() - offsetY);
	}
	
	@Override
	public Resource createNew()
	{
		return new GoldResource(getX(), getY(), getWidth(), getHeight());
	}
}
