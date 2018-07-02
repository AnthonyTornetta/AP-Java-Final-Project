/*
 * Anthony Tornetta & Troy Cope | P5 | 3/31/18
 * This is our own work: ACT & TC
 * A test enemy that moves to you in a "creative" way
 */

package com.corntrip.turnbased.gameobject.living;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.util.Helper;
import com.corntrip.turnbased.util.Resources;
import com.corntrip.turnbased.world.World;

/**
 * An {@link Enemy} that goes after the target in (basically) a straight line and goes through walls
 */
public class TestEnemy extends Enemy
{
	/**
	 * Thing to draw
	 */
	private Image img;
	
	/**
	 * Dictates texture & difficulty
	 */
	private int tier;
	
	/**
	 * An {@link Enemy} that goes after the target in (basically) a straight line and goes through walls
	 * @param startX The x to start at
	 * @param startY The y to start at
	 * @param w The width of the enemy
	 * @param h The height of the enemy
	 * @param world The world the enemy is in
	 * @param target The LivingEntity to go after
	 * @param name The name of the enemy - they have identities too!
	 * @param tier The tier of the enemy (dictates texture & difficulty)
	 */
	public TestEnemy(float startX, float startY, float w, float h, World world, LivingEntity target, String name, int tier)
	{
		super(startX, startY, w, h, world, target, name);
		this.tier = Helper.clamp(tier, 0, getMaxTier());
		img = Resources.getSpriteImage("enemy", tier - 1, 0);
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		render(gc, gfx, 0, 0);
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx, float offsetX, float offsetY) throws SlickException
	{
		renderGUI(gc, gfx, offsetX, offsetY);
		
		gfx.setColor(Color.red);
		gfx.rotate(getAnchorPointX(offsetX), getAnchorPointY(offsetY), getRotation());
		img.draw(getX() - offsetX, getY() - offsetY);
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{
		LivingEntity target = getTarget();
		
		if(target != null) // the target has been set
		{
			float targetX = target.getX();
			float targetY = target.getY();
			
			// Add some randomness in the path I guess so it's not a legit straight line
			float wibbleWobbleX = Helper.clamp(targetX - getX(), -tier, tier);
			float wibbleWobbleY = Helper.clamp(targetY - getY(), -tier, tier);
			
			float moveX = (float)Math.random() * 10 - 5 + wibbleWobbleX; 
			float moveY = (float)Math.random() * 10 - 5 + wibbleWobbleY;
			
			setX(getX() + moveX);
			setY(getY() + moveY);
			
			setRotation(Helper.getAngle(getAnchorPointX(), getAnchorPointY(), target.getAnchorPointX(), target.getAnchorPointY()));
			
			if(this.collidingWith(target))
			{
				getWorld().removeObject(this);
				target.takeDamage(tier);
			}
		}
		
		updateGUIPos();
	}
	
	@Override
	public LivingEntity clone()
	{
		return new TestEnemy(getX(), getY(), getWidth(), getHeight(), getWorld(), getTarget(), getName(), tier);
	}
	
	@Override
	public int getMaxTier() { return 5; }
}
