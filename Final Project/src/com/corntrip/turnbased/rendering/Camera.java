/*
 * Anthony Tornetta & Troy Cope | P5 | 3/31/18
 * This is our own work: ACT & TC
 * Just stores variables for the offset and some helpful centering functions
 */

package com.corntrip.turnbased.rendering;

import com.corntrip.turnbased.gameobject.GameObject;
import com.corntrip.turnbased.util.Helper;

public class Camera
{
	private float xOffset, yOffset;
	private float visibleWidth, visibleHeight;
	private float mapWidth, mapHeight;
	
	/**
	 * Creates a Camera Object used to center around a given GameObject using the screen's width & height
	 * @param x The starting x offset of the camera
	 * @param y The starting y offset of the camera
	 * @param vw The screen's width
	 * @param vh The screen's height
	 * @param mw The total area the camera can travel in's width
	 * @param mh The total area the camera can travel in's height
	 */
	public Camera(float x, float y, float vw, float vh, float mw, float mh)
	{
		xOffset = x;
		yOffset = y;
		visibleWidth = vw;
		visibleHeight = vh;
		mapHeight = mh;
		mapWidth = mw;
	}
	
	/**
	 * Puts the object to center around in the center of the camera's viewpoint, but by slowly gliding/slipping there
	 * @param go The GameObject to center around
	 */
	public void slippyCenter(GameObject go)
	{
		float slippyFactor = 0.1f;
		
		xOffset += slippyFactor * (go.getX() - visibleWidth / 2 + go.getWidth() / 2 - xOffset);
		yOffset += slippyFactor * (go.getY() - visibleHeight / 2 + go.getHeight() / 2 - yOffset);
		
		xOffset = Helper.clamp(xOffset, 0, mapWidth - visibleWidth);
		yOffset = Helper.clamp(yOffset, 0, mapHeight - visibleHeight);
	}
	
	/**
	 * Puts the object to center around in the center of the camera's viewpoint
	 * @param go The GameObject to center around
	 */
	public void center(GameObject go)
	{
		if(go == null)
			return;
		xOffset = go.getX() - visibleWidth / 2 + go.getWidth() / 2;
		yOffset = go.getY() - visibleHeight / 2 + go.getHeight() / 2;
		
		xOffset = Helper.clamp(xOffset, 0, mapWidth - visibleWidth);
		yOffset = Helper.clamp(yOffset, 0, mapHeight - visibleHeight);
	}
	
	// Getters & Setters //
	
	public float getXOffset() { return xOffset; }
	public void setXOffset(float xOffset) { this.xOffset = xOffset; }

	public float getYOffset() { return yOffset; }
	public void setYOffset(float yOffset) { this.yOffset = yOffset; }

	public float getScreenWidth() { return visibleWidth; }
	public void setScreenWidth(float screenWidth) { this.visibleWidth = screenWidth; }
	
	public float getScreenHeight() { return visibleHeight; }
	public void setScreenHeight(float screenHeight) { this.visibleHeight = screenHeight; }
}
