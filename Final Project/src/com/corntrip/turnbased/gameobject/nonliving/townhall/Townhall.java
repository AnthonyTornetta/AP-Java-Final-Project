/*
 * Anthony Tornetta & Troy Cope | P5 | 3/31/18
 * This is our own work: ACT & TC
 * Acts as the upgrading center for the player
 */

package com.corntrip.turnbased.gameobject.nonliving.townhall;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.gameobject.GameObject;
import com.corntrip.turnbased.util.Resources;

public class Townhall extends GameObject
{
	private Image img;
	
	/**
	 * Builds a new townhall into the world
	 * @param startX: starting x
	 * @param startY: starting y
	 * @param w: width
	 * @param h: hieght
	 */
	public Townhall(float startX, float startY, float w, float h)
	{
		super(startX, startY, w, h);
		
		img = Resources.getImage("townhall");
	}

	@Override
	/**
	 * shows the townhall
	 */
	public void render(GameContainer gc, Graphics gfx, float offsetX, float offsetY) throws SlickException
	{
		img.draw(getX() - offsetX, getY() - offsetY);
	}
	
	/**
	 * 
	 * @param go: game object
	 * @return True/False if the object is in range
	 */
	public boolean withinRange(GameObject go)
	{
		float thisAnchorX = getX() + getWidth() / 2;
		float thisAnchorY = getY() + getHeight() / 2;
		
		float theirAnchorX = go.getX() + go.getWidth() / 2;
		float theirAnchorY = go.getY() - go.getHeight() / 2;
		
		//calculation to see if they're in range
		double distanceAway = Math.sqrt(Math.pow(theirAnchorX - thisAnchorX, 2) + Math.pow(theirAnchorY - thisAnchorY, 2));
		
		return distanceAway < (getWidth() + getHeight());
	}
}
