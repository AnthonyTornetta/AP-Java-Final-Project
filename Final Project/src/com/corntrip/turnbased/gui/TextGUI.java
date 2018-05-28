package com.corntrip.turnbased.gui;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

public class TextGUI extends GUIElement
{
	private String text;
	private Color textColor;
	private boolean centered = false;
	
	private int fontWidth = 0;
	private TrueTypeFont font = new TrueTypeFont(new Font("Verdana", Font.PLAIN, 16), true);
	
	public TextGUI(float x, float y, String text, Color textColor)
	{
		super(x, y);
		this.text = text;
		this.textColor = textColor;
		
		fontWidth = font.getWidth(text);
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
			font.drawString(getX() - fontWidth / 2 - offsetX, getY() - offsetY, text, textColor);
		}
	}
	
	public void setCentered(boolean c) { centered = c; }
	public boolean isCentered() { return centered; }

	public String getText() { return text; }	
	public void setText(String text) { this.text = text; }

	public Color getTextColor() { return textColor; }
	public void setTextColor(Color textColor) { this.textColor = textColor; }

	public TrueTypeFont getFont() { return font; }
	public void setFont(TrueTypeFont font) { this.font = font; }
}
