package com.corntrip.turnbased.gameobject.living;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.gameobject.GameObject;
import com.corntrip.turnbased.util.Helper;
import com.corntrip.turnbased.world.World;

public class Player extends LivingEntity
{
	/**
	 * Used for calculating movement
	 */
	private float velX = 0, velY = 0;
	
	/**
	 * Controllable Entity by the user
	 * @param startX The x to start the player at
	 * @param startY The y to start the player at
	 * @param w The width of the player
	 * @param h The height of the player
	 * @param world The world the player is in
	 */
	public Player(float startX, float startY, float w, float h, World world)
	{
		super(startX, startY, w, h, world);
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{
		float subVal = 0.5f; // TODO: Get thru surface friction
		
		velX -= Math.signum(velX) * subVal;
		velY -= Math.signum(velY) * subVal;
		
		// If the velocities are somewhere near 0, just set them to 0 so it doesn't slide forever
		if(velX <= subVal && velX >= -subVal)
			velX = 0;
		if(velY <= subVal && velY >= -subVal)
			velY = 0;
		
		// Fun movement calcs in here
		
		Input in = gc.getInput();
		if(in.isKeyDown(Input.KEY_W) || in.isKeyDown(Input.KEY_UP))
			velY += -1.1;
		if(in.isKeyDown(Input.KEY_S) || in.isKeyDown(Input.KEY_DOWN))
			velY += 1.1;
		
		if(in.isKeyDown(Input.KEY_A) || in.isKeyDown(Input.KEY_LEFT))
			velX += -1.1;
		if(in.isKeyDown(Input.KEY_D) || in.isKeyDown(Input.KEY_RIGHT))
			velX += 1.1;
		
		// End movement calcs
		
		velX = Helper.clamp(velX, -5.0f, 5.0f);
		velY = Helper.clamp(velY, -5.0f, 5.0f);
		
		boolean didMove = false;
		
		List<GameObject> objs = getWorld().getGameObjects();
		for(int i = 0; i < objs.size(); i++)
		{
			if(!objs.get(i).equals(this))
			{
				if(collidingWith(objs.get(i), getX() + velX, getY() + velY, getWidth(), getHeight()))
				{
					while(!collidingWith(objs.get(i), getX() + velX, getY(), getWidth(), getHeight()))
					{
						setX(getX() + Math.signum(velX));
						setY(getY() + Math.signum(velY));
					}
					didMove = true;
				}
			}
		}
		
		if(!didMove)
		{
			setX(getX() + velX);
			setY(getY() + velY);
		}
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		renderWithOffset(gc, gfx, 0, 0);
	}

	@Override
	public void renderWithOffset(GameContainer gc, Graphics gfx, float offsetX, float offsetY)
	{
		gfx.setColor(Color.green);
		
		//gfx.rotate(rx, ry, ang);
		
		gfx.fillRect(getX() - offsetX, getY() - offsetY, getWidth(), getHeight());
	}

	@Override
	public LivingEntity clone()
	{
		return new Player(getX(), getY(), getWidth(), getHeight(), getWorld());
	}
}
