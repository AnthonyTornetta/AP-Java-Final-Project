package com.corntrip.turnbased.rendering;

import com.corntrip.turnbased.gameobject.GameObject;
import com.corntrip.turnbased.util.Helper;

public class Camera
{
	private float xOffset, yOffset;
	private float screenWidth, screenHeight;
	private float areaWidth, areaHeight;
	
	public Camera(float x, float y, float sw, float sh, float areaWidth, float areaHeight)
	{
		xOffset = x;
		yOffset = y;
		screenWidth = sw;
		screenHeight = sh;
		this.areaHeight = areaHeight;
		this.areaWidth = areaWidth;
	}
	
	public void slippyCenter(GameObject go)
	{
		float slippyFactor = 0.1f;
		
		xOffset += slippyFactor * (go.getX() - screenWidth / 2 + go.getWidth() / 2 - xOffset);
		yOffset += slippyFactor * (go.getY() - screenHeight / 2 + go.getHeight() / 2 - yOffset);
		
		xOffset = Helper.clamp(xOffset, 0, areaWidth - screenWidth);
		yOffset = Helper.clamp(yOffset, 0, areaHeight - screenHeight);
	}
	
	public void center(GameObject go)
	{
		xOffset = go.getX() - screenWidth / 2 + go.getWidth() / 2;
		yOffset = go.getY() - screenHeight / 2 + go.getHeight() / 2;
		
		xOffset = Helper.clamp(xOffset, 0, areaWidth - screenWidth);
		yOffset = Helper.clamp(yOffset, 0, areaHeight - screenHeight);
	}
	
	public float getXOffset() { return xOffset; }
	public void setXOffset(float xOffset) { this.xOffset = xOffset; }

	public float getYOffset() { return yOffset; }
	public void setYOffset(float yOffset) { this.yOffset = yOffset; }

	public float getScreenWidth() { return screenWidth; }
	public void setScreenWidth(float screenWidth) { this.screenWidth = screenWidth; }
	
	public float getScreenHeight() { return screenHeight; }
	public void setScreenHeight(float screenHeight) { this.screenHeight = screenHeight; }
}
