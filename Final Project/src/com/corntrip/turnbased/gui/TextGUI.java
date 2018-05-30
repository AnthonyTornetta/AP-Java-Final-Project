package com.corntrip.turnbased.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.util.Reference;

public class TextGUI extends GUIElement
{
	private String text;
	private Color textColor;
	private boolean centered = false;
	
	private int fontWidth = 0;
	
	public TextGUI(float x, float y, String text, Color textColor)
	{
		super(x, y);
		this.text = text;
		this.textColor = textColor;
		
		fontWidth = Reference.FONT_DEFAULT.getWidth(text);
	}

	@Override
	public void render(GameContainer gc, Graphics gfx, float offsetX, float offsetY) throws SlickException
	{
		gfx.setColor(textColor);
		if(!centered)
		{
			Reference.FONT_DEFAULT.drawString(getX() - offsetX, getY() - offsetY, text, textColor);
		}
		else
		{
			Reference.FONT_DEFAULT.drawString(getX() - fontWidth / 2 - offsetX, getY() - offsetY, text, textColor);
		}
	}
	
	public void setCentered(boolean c) { centered = c; }
	public boolean isCentered() { return centered; }

	public String getText() { return text; }	
	public void setText(String text) { this.text = text; }

	public Color getTextColor() { return textColor; }
	public void setTextColor(Color textColor) { this.textColor = textColor; }
}
