/*
 * Anthony Tornetta & Troy Cope | P5 | 3/31/18
 * This is our own work: ACT & TC
 * An Enemy that has a given target to chase after and displays its name and healthbar above it
 */

package com.corntrip.turnbased.gameobject.living;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.gui.HealthBarGUI;
import com.corntrip.turnbased.gui.TextGUI;
import com.corntrip.turnbased.world.World;

/**
 * A {@link LivingEntity} that goes after the target and will do damage to them
 */
public abstract class Enemy extends LivingEntity
{
	/**
	 * The target to go after
	 */
	private LivingEntity target;
	
	//simply name
	private String name;
	
	//health and it's Gui
	private HealthBarGUI healthBar;
	private TextGUI nameGUI;
	
	/**
	 * The Antagonist of the story
	 * @param startX The starting x of the enemy
	 * @param startY The starting y of the enemy
	 * @param w The width of the player
	 * @param h The height of the player
	 * @param world The world the enemy is in
	 * @param target The target for the enemy (doesn't have to be the player)
	 */
	public Enemy(float startX, float startY, float w, float h, World world, LivingEntity target, String name)
	{
		super(startX, startY, w, h, world, 2);
		
		this.target = target;
		this.name = name;
		
		// x & y don't matter since im updating them afterwards so 0, 0 is fine for temp
		healthBar = new HealthBarGUI(0, 0, w, 6, Color.red, Color.green, getMaxHealth(), getMaxHealth());		
		nameGUI = new TextGUI(0, 0, name, Color.red);
		nameGUI.setCentered(true);
		
		updateGUIPos();
	}
	
	@Override
	/**
	 * Forces them to take damage as passed
	 */
	public void takeDamage(int dmg)
	{
		super.takeDamage(dmg);
		healthBar.setHealth(getHealth());
	}
	
	@Override
	/*
	 * restores some of the HP lost
	 */
	public void heal(int amt)
	{
		super.heal(amt);
		healthBar.setHealth(getHealth());
	}
	
	/**
	 * updates the GUI's x and y to match the movement
	 */
	public void updateGUIPos()
	{
		healthBar.setX(getX());
		healthBar.setY(getY() - 16);
		
		nameGUI.setX(getX() + getWidth() / 2);
		nameGUI.setY(getY() - 20);
	}
	
	/**
	 * 
	 * @param gc: game container
	 * @param gfx: the graphics used
	 * @param offsetX: simply the offset of x
	 * @param offsetY: simply the y offset
	 * @throws SlickException: makes sure it doesn't break, no try/catch
	 */
	public void renderGUI(GameContainer gc, Graphics gfx, float offsetX, float offsetY) throws SlickException
	{
		healthBar.render(gc, gfx, offsetX, offsetY);
		nameGUI.render(gc, gfx, offsetX, offsetY);
	}
	
	// Getters & Setters //
	
	public LivingEntity getTarget() { return target; }
	public void setTarget(LivingEntity t) { this.target = t; }

	public String getName() { return name; }
	public void setName(String name)
	{
		this.name = name;
		nameGUI.setText(name);
	}

	public HealthBarGUI getHealthBar() { return healthBar; }
	public void setHealthBar(HealthBarGUI healthBar) { this.healthBar = healthBar; }
	
	public abstract int getMaxTier();
}
