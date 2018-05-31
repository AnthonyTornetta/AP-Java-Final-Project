/*
 * Anthony Tornetta & Troy Cope | P5 | 3/31/18
 * This is our own work: ACT & TC
 * Displays a GUI with elements in it
 */

package com.corntrip.turnbased.gui;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.rendering.IRenderable;

public class GUI implements IRenderable
{
	/**
	 * Each element present in the array
	 */
	private List<GUIElement> elements = new ArrayList<>();
	
	/**
	 * Is a GUI holder and renders each
	 * @param e All the elements to initialize the GUI with
	 */
	public GUI(GUIElement... e)
	{
		for(GUIElement elem : e)
		{
			elements.add(elem);
		}
	}

	@Override
	public void render(GameContainer gc, Graphics gfx, float offsetX, float offsetY) throws SlickException
	{
		for(GUIElement elem : elements)
		{
			elem.render(gc, gfx, offsetX, offsetY);
		}
	}

	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		render(gc, gfx, 0, 0);
	}
}
