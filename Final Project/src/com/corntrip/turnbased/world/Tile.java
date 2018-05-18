package com.corntrip.turnbased.world;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.rendering.IRenderable;
import com.corntrip.turnbased.util.Vector2;

public class Tile implements IRenderable
{
	private Vector2<Float, Float> position;
	private Vector2<Float, Float> dimensions;

	public Tile(float x, float y, float w, float h)
	{
		position = new Vector2<>(x, y);
		dimensions = new Vector2<>(w, h);
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		renderWithOffset(gc, gfx, 0, 0);
	}
	
	@Override
	public void renderWithOffset(GameContainer gc, Graphics gfx, float offsetX, float offsetY)
	{
		gfx.setColor(new Color(0, 48, 100));
		gfx.fillRect(offsetX + position.getX(), offsetY + position.getY(), dimensions.getX(), dimensions.getY());
		gfx.setColor(Color.black);
		gfx.drawRect(offsetX + position.getX(), offsetY + position.getY(), dimensions.getX(), dimensions.getY());
	}
}
