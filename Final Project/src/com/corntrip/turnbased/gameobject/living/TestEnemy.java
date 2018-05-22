package com.corntrip.turnbased.gameobject.living;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.gameobject.Entity;
import com.corntrip.turnbased.util.Helper;
import com.corntrip.turnbased.world.World;

public class TestEnemy extends Enemy
{
	public TestEnemy(float startX, float startY, float w, float h, World world, Entity target)
	{
		super(startX, startY, w, h, world, target);
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		render(gc, gfx, 0, 0);
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx, float offsetX, float offsetY)
	{
		gfx.setColor(Color.red);
		gfx.fillRect(getX() - offsetX, getY() - offsetY, getWidth(), getHeight());
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{
		Entity target = getTarget();
		
		if(target != null) // the target has been set
		{
			float targetX = target.getX();
			float targetY = target.getY();
			
			float moveX = Helper.clamp(targetX - getX(), -5, 5);
			float moveY = Helper.clamp(targetY - getY(), -5, 5);
			
			setX(getX() + moveX);
			setY(getY() + moveY);
		}
	}

	@Override
	public LivingEntity clone()
	{
		return new TestEnemy(getX(), getY(), getWidth(), getHeight(), getWorld(), getTarget());
	}
}
