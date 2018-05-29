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
	
	public Townhall(float startX, float startY, float w, float h)
	{
		super(startX, startY, w, h);
		
		img = Resources.getImage("townhall");
	}

	@Override
	public void render(GameContainer gc, Graphics gfx, float offsetX, float offsetY) throws SlickException
	{
		img.draw(getX() - offsetX, getY() - offsetY);
	}
	
	public boolean withinRange(GameObject go)
	{
		float thisAnchorX = getX() + getWidth() / 2;
		float thisAnchorY = getY() + getHeight() / 2;
		
		float theirAnchorX = go.getX() + go.getWidth() / 2;
		float theirAnchorY = go.getY() - go.getHeight() / 2;
		
		double distanceAway = Math.sqrt(Math.pow(theirAnchorX - thisAnchorX, 2) + Math.pow(theirAnchorY - thisAnchorY, 2));
		
		return distanceAway < (getWidth() + getHeight());
	}
}
