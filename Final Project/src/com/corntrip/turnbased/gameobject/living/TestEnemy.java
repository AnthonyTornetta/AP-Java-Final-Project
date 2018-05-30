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

public class TestEnemy extends Enemy
{
	private Image img;
	
	public TestEnemy(float startX, float startY, float w, float h, World world, LivingEntity target, String name)
	{
		super(startX, startY, w, h, world, target, name);
		
		img = Resources.getImage("enemy");
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
			
			float xd = Helper.clamp(targetX - getX(), -1, 1);
			float xdy = Helper.clamp(targetY - getY(), -1, 1);
			
			float moveX = (float)Math.random() * 10 - 5 + xd;
			float moveY = (float)Math.random() * 10 - 5 + xdy;
			
			setX(getX() + moveX);
			setY(getY() + moveY);
			
			setRotation(Helper.getAngle(getAnchorPointX(), getAnchorPointY(), target.getAnchorPointX(), target.getAnchorPointY()));
			
			if(this.collidingWith(target))
			{
				getWorld().removeObject(this);
				target.takeDamage(1);
			}
		}
		
		updateGUIPos();
	}

	@Override
	public LivingEntity clone()
	{
		return new TestEnemy(getX(), getY(), getWidth(), getHeight(), getWorld(), getTarget(), getName());
	}
}
