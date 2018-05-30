/*
 * Anthony Tornetta & Troy Cope | P5 | 3/31/18
 * This is our own work: ACT & TC
 * Displays an image in the scene
 */

package com.corntrip.turnbased.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageGUI extends GUIElement
{
	private Image image;
	
	public ImageGUI(float x, float y, Image image)
	{
		super(x, y);
		this.image = image;
	}

	@Override
	public void render(GameContainer gc, Graphics gfx, float offsetX, float offsetY) throws SlickException
	{
		image.draw(getX() - offsetX, getY() - offsetY);
	}

	public Image getImage() { return image; }
	public void setImage(Image image) { this.image = image; }
}
