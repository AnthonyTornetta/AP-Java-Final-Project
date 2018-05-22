package com.corntrip.turnbased.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.rendering.IRenderable;

public abstract class GUIElement implements IRenderable
{
	private int screenWidth, screenHeight;
	
	public GUIElement(int screenWidth, int screenHeight)
	{
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		render(gc, gfx, 0, 0);
	}
	
	@Override
	public abstract void render(GameContainer gc, Graphics gfx, float offsetX, float offsetY) throws SlickException;
	
	public abstract void handleKey(int j);

	public int getScreenWidth() { return screenWidth; }
	public void setScreenWidth(int screenWidth) { this.screenWidth = screenWidth; }

	public int getScreenHeight() { return screenHeight; }
	public void setScreenHeight(int screenHeight) { this.screenHeight = screenHeight; }
}
