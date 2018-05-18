package com.corntrip.turnbased.gameobject;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.util.Helper;

public class Player extends GameObject
{
	private float velX = 0;
	private float velY = 0;
	
	public Player(float startX, float startY, float w, float h)
	{
		super(startX, startY, w, h);
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{		
		float subVal = 0.5f;
		
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
		
		setX(getX() + velX);
		setY(getY() + velY);
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
		gfx.fillRect(offsetX + getX(), offsetY + getY(), getWidth(), getHeight());
	}
}
