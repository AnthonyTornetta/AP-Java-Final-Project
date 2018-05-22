package com.corntrip.turnbased.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.gameobject.modifier.Equipable;

public class Inventory extends GUIElement
{
	private final int SLOT_SIZE = 32;
	private Equipable[] items;
	
	public Inventory(int screenWidth, int screenHeight, int size)
	{
		super(screenWidth, screenHeight);
		items = new Equipable[size];
	}
	
	public Equipable getItem(int i)
	{
		return items[i];
	}
	
	public void setItem(int i, Equipable e)
	{
		items[i] = e;
	}

	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		renderWithOffset(gc, gfx, 0, 0);
	}

	@Override
	public void renderWithOffset(GameContainer gc, Graphics gfx, float offsetX, float offsetY) throws SlickException
	{
		int ofx = SLOT_SIZE * items.length / 2;
		int drawY = getScreenHeight() - SLOT_SIZE;
		
		for(int i = 0; i < items.length; i++)
		{
			
			gfx.drawRect(ofx + i * SLOT_SIZE - offsetX, drawY - offsetY, SLOT_SIZE, SLOT_SIZE);
		}
	}

	@Override
	public void handleKey(int k)
	{
		
	}
}
