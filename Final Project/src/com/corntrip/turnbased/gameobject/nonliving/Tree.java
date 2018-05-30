/*
 * Anthony Tornetta & Troy Cope | P5 | 3/31/18
 * This is our own work: ACT & TC
 * A simple tree that sits there and is a tree
 */

package com.corntrip.turnbased.gameobject.nonliving;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.corntrip.turnbased.gameobject.GameObject;
import com.corntrip.turnbased.util.Resources;

public class Tree extends GameObject
{
	private Image texture;
	
	/**
	 * Sits there and acts like a wall.
	 * @param startX The x to sit at
	 * @param startY The y to sit at
	 * @param w The width of the wall
	 * @param h The height of the wall
	 */
	public Tree(float startX, float startY, float w, float h)
	{
		super(startX, startY, w, h);
		
		texture = Resources.getImage("tree");
	}

	@Override
	public void render(GameContainer gc, Graphics gfx, float xOffset, float yOffset)
	{
		texture.draw(getX() - xOffset, getY() - yOffset);
	}
}
