/*
 * Anthony Tornetta & Troy Cope | P5 | 3/31/18
 * This is our own work: ACT & TC
 * A resource that looks like gold
 */

package com.corntrip.turnbased.gameobject.nonliving.resources;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.corntrip.turnbased.util.Resources;

public class GoldResource extends Resource
{
	private Image img;
	
	/**
	 * Builds a new gold resource
	 * @param startX: starting x
	 * @param startY: starting y
	 * @param w: width
	 * @param h: height
	 */
	public GoldResource(float startX, float startY, float w, float h)
	{
		super(startX, startY, w, h);
		
		img = Resources.getImage("gold");
	}
	
	@Override
	/**
	 * returns how many points the player has earned
	 */
	public int getPtsValue()
	{
		return 1;
	}
	
	@Override
	/**
	 * actually places the image into the game
	 */
	public void render(GameContainer gc, Graphics gfx, float offsetX, float offsetY)
	{
		img.draw(getX() - offsetX, getY() - offsetY);
	}
	
	@Override
	/**
	 * adds another gold resource if it was destroyed
	 */
	public Resource createNew()
	{
		return new GoldResource(getX(), getY(), getWidth(), getHeight());
	}
}
