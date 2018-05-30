/*
 * Anthony Tornetta & Troy Cope | P5 | 3/31/18
 * This is our own work: ACT & TC
 * Displays a healthbar in the scene based off the amount of health and the max health
 */

package com.corntrip.turnbased.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class HealthBarGUI extends GUIBar
{
	private Color healthBackground, healthColor;
	private int health, maxHealth;
	
	/**
	 * 
	 * @param x: start x
	 * @param y: start y
	 * @param w: width
	 * @param h: height
	 * @param healthBackground: what shows when hp is lost
	 * @param healthColor: what hp looks like
	 * @param health: numerical representation of health
	 * @param maxHealth: maximum health
	 */
	public HealthBarGUI(float x, float y, float w, float h, Color healthBackground, Color healthColor, int health, int maxHealth)
	{		
		super(x, y, w, h);
		this.healthBackground = healthBackground;
		this.healthColor = healthColor;
		this.health = health;
		this.maxHealth = maxHealth;
	}

	/**
	 * Sets the health bar above the entity's head when called
	 */
	@Override
	public void render(GameContainer gc, Graphics gfx, float offsetX, float offsetY) throws SlickException
	{
		gfx.setColor(healthBackground);
		gfx.fillRect(getX() - offsetX, getY() - offsetY, getWidth(), getHeight());
		
		gfx.setColor(healthColor);
		gfx.fillRect(getX() - offsetX, getY() - offsetY, health / (float)maxHealth * getWidth(), getHeight());
	}
	
	// Getters & Setters //
	
	public Color getHealthBackground() { return healthBackground; }
	public void setHealthBackground(Color healthBackground) { this.healthBackground = healthBackground; }

	public Color getHealthColor() { return healthColor; }
	public void setHealthColor(Color healthColor) { this.healthColor = healthColor; }

	public int getHealth() { return health; }
	public void setHealth(int health) { this.health = health; }

	public int getMaxHealth() { return maxHealth; }
	public void setMaxHealth(int maxHealth) { this.maxHealth = maxHealth; }
}
