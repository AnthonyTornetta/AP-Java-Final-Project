/*
 * Anthony Tornetta & Troy Cope | P5 | 3/31/18
 * This is our own work: ACT & TC
 * Displays text in the scene
 */

package com.corntrip.turnbased.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import com.corntrip.turnbased.util.Reference;

public class TextGUI extends GUIElement
{
	private String text;
	private Color textColor;
	private boolean centered = false;
	private TrueTypeFont font;
	
	private int fontWidth = 0;
	
	/**
	 * Displays text at a given point in the window
	 * @param x Start x
	 * @param y Start y
	 * @param text What is displayed
	 * @param textColor What color the text is
	 * @param font The font to use
	 */
	public TextGUI(float x, float y, String text, Color textColor, TrueTypeFont font)
	{
		super(x, y);
		this.text = text;
		this.textColor = textColor;
		
		this.font = font;
		fontWidth = font.getWidth(text);
	}
	
	/**
	 * Displays text at a given point in the window
	 * @param x Start x
	 * @param y Start y
	 * @param text What is displayed
	 * @param textColor What color the text is
	 */
	public TextGUI(float x, float y, String text, Color textColor)
	{
		this(x, y, text, textColor, Reference.FONT_DEFAULT);
	}

	@Override
	public void render(GameContainer gc, Graphics gfx, float offsetX, float offsetY) throws SlickException
	{
		gfx.setColor(textColor);
		
		if(!centered)
		{
			font.drawString(getX() - offsetX, getY() - offsetY, text, textColor);
		}
		else
		{
			// Centers the text using fancy maths
			font.drawString(getX() - fontWidth / 2 - offsetX, getY() - offsetY, text, textColor);
		}
	}
	
	// Getters & Setters //
	
	public void setCentered(boolean c) { centered = c; }
	public boolean isCentered() { return centered; }

	public String getText() { return text; }	
	public void setText(String text) 
	{
		this.text = text;
		fontWidth = font.getWidth(text); 
	}

	public Color getTextColor() { return textColor; }
	public void setTextColor(Color textColor) { this.textColor = textColor; }

	public void setFont(TrueTypeFont ttf)
	{
		font = ttf;
		fontWidth = font.getWidth(text);
	}
}
