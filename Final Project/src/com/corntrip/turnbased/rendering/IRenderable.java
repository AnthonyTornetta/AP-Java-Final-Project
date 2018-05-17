package com.corntrip.turnbased.rendering;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface IRenderable
{
	public void render(GameContainer gc, Graphics gfx) throws SlickException;
}
