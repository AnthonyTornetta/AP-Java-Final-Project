package com.corntrip.turnbased.gameobject.nonliving.resources;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.gameobject.GameObject;

public class ResourceDeposit extends GameObject
{
	public ResourceDeposit(float startX, float startY, float w, float h)
	{
		super(startX, startY, w, h);
	}

	@Override
	public void render(GameContainer gc, Graphics gfx, float offsetX, float offsetY) throws SlickException
	{		
		gfx.setColor(Color.black);
		gfx.fillRect(getX() - offsetX, getY() - offsetY, getWidth(), getHeight());
	}
	
	public int dropResource(Resource r)
	{
		return r.getPtsValue();
	}
}
