package com.corntrip.turnbased.gui;

public abstract class GUIBar extends GUIElement
{
	private float width, height;
	public GUIBar(float x, float y, float w, float h)
	{
		super(x, y);
		width = w;
		height = h;
	}
	
	// Getters & Setters //
	
	public float getWidth() { return width; }
	public void setWidth(float width) { this.width = width; }
	
	public float getHeight() { return height; }
	public void setHeight(float height) { this.height = height; }
}
