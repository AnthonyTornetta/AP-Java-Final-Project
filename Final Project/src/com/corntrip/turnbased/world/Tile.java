package com.corntrip.turnbased.world;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.opengl.Texture;

import com.corntrip.turnbased.rendering.IRenderable;
import com.corntrip.turnbased.util.Vector2;

public class Tile implements IRenderable
{
	private Vector2<Float, Float> position;
	private Vector2<Float, Float> dimensions;
	
	private Texture texture;
	private Shape shape;
	
	public Tile(Texture tex, Vector2<Float, Float> position, Vector2<Float, Float> dimensions)
	{
		texture = tex;
		shape = new Rectangle(position.getX(), position.getY(), dimensions.getX(), dimensions.getY());
		
	}

	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
	}
}
