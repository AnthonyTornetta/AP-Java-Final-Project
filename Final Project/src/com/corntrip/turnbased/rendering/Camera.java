package com.corntrip.turnbased.rendering;

import com.corntrip.turnbased.world.World;

public class Camera
{
	private float xOffset, yOffset;
	private float screenWidth, screenHeight;
	private World world;
	
	public Camera(float x, float y, float sw, float sh, World w)
	{
		xOffset = x;
		yOffset = y;
		screenWidth = sw;
		screenHeight = sh;
		world = w;
	}
	
	public World getWorld() { return world; }

	public float getxOffset() { return xOffset; }
	public void setxOffset(float xOffset) { this.xOffset = xOffset; }

	public float getyOffset() { return yOffset; }
	public void setyOffset(float yOffset) { this.yOffset = yOffset; }

	public float getScreenWidth() { return screenWidth; }
	public void setScreenWidth(float screenWidth) { this.screenWidth = screenWidth; }
	
	public float getScreenHeight() { return screenHeight; }
	public void setScreenHeight(float screenHeight) { this.screenHeight = screenHeight; }
}
