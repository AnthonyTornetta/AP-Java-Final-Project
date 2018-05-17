package com.corntrip.turnbased.util;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

/**
 * Handles all the resources of the game
 * @author Cornchip
 *
 */
public class Resources
{
	private static Map<String, Image> images;
	private static Map<String, SpriteSheet> sprites;
	private static Map<String, Sound> sounds;
	
	public Resources()
	{
		images = new HashMap<>();
		sprites = new HashMap<>();
		sounds = new HashMap<>();
		
		images.put("grass", loadImage("/res/grass.png"));
	}
	
	private SpriteSheet loadSpriteSheet(String path, int tileWidth, int tileHeight)
	{
		return new SpriteSheet(loadImage(path), tileWidth, tileHeight);
	}
	
	private Image loadImage(String path)
	{
		Image i = null;
		try
		{
			i = new Image(path, false, Image.FILTER_NEAREST);
		}
		catch(SlickException ex)
		{
			Logger.getLogger("error").log(Level.SEVERE, "Image [" + path + "] failed to load.", ex);
		}
		return i;
	}
	
	public Image getImage(String name)
	{
		return images.get(name);
	}
}
