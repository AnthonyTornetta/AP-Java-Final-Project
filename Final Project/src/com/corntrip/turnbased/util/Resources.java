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
		
		/// Load Spritesheets Here
		//sprites.put("tiles", loadSprite("/res/spritesheets/tiles.png", 32, 32));
		
		/// Load Images Here
		//images.put("grass", loadImage("/res/grass.png"));
		
		/// Load Sounds Here
		//sounds.put("a random sound", loadSound("/res/sounds.png"));
	}
	
	private SpriteSheet loadSprite(String path, int tileWidth, int tileHeight)
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
	
	private Sound loadSound(String path)
	{
		Sound s = null;
		try
		{
			s = new Sound(path);
		}
		catch(SlickException ex)
		{
			Logger.getLogger("error").log(Level.SEVERE, "Sound [" + path + "] failed to load.", ex);
		}
		return s;
	}
	
	// Getters & Setters //
	
	public Sound getSound(String name)
	{
		return sounds.get(name);
	}
	
	public SpriteSheet getSpriteSheet(String name)
	{
		return sprites.get(name);
	}
	
	public Image getSpriteImage(String name, int x, int y)
	{
		return sprites.get(name).getSubImage(x, y);
	}
	
	public Image getImage(String name)
	{
		return images.get(name);
	}
}
