package com.corntrip.turnbased.gui;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.rendering.IRenderable;

public class GUI implements IRenderable
{
	List<GUIElement> elements = new ArrayList<>();
	
	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		render(gc, gfx, 0, 0);
	}

	@Override
	public void render(GameContainer gc, Graphics gfx, float offsetX, float offsetY) throws SlickException
	{
		for(GUIElement e : elements)
		{
			e.render(gc, gfx, offsetX, offsetY);
		}
	}
	
	public void addElement(GUIElement elem)
	{
		elements.add(elem);
	}
	
	public boolean removeElement(GUIElement elem)
	{
		return elements.remove(elem);
	}
	
	public void keypress(int key)
	{
		for(GUIElement e : elements)
		{
			e.handleKey(key);
		}
	}
}
