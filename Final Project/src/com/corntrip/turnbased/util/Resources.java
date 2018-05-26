package com.corntrip.turnbased.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.BufferedImageUtil;

/**
 * Handles all the resources of the game
 */
public class Resources
{
	/**
	 * Stores Images in accordance with their keys
	 */
	private static Map<String, Image> images = new HashMap<>();
	
	/**
	 * Stores SpriteSheets in accordance with their keys
	 */
	private static Map<String, SpriteSheet> spriteSheets = new HashMap<>();
	
	/**
	 * Stores Sounds in accordance with their keys
	 */
	private static Map<String, Sound> sounds = new HashMap<>();
	
	/**
	 * Loads a SpriteSheet found at a given file path starting in res/images/spritesheets/
	 * @param path The path to the file in the res/images/spritesheets/ directory
	 * @return The SpriteSheet found, or null if the file was not found or unable to be parsed
	 */
	public static SpriteSheet loadSpriteSheet(String path, int tileWidth, int tileHeight)
	{
		path = "spritesheets/" + path;
		return new SpriteSheet(loadImage(path), tileWidth, tileHeight);
	}
	
	/**
	 * Loads an Image found at a given file path starting in res/images/
	 * @param path The path to the file in the res/images/ directory
	 * @return The Image found, or null if the file was not found or unable to be parsed
	 */
	public static Image loadImage(String path)
	{
		path = "res/images/" + path;
		Image i = null;
		try
		{
			System.out.println(path);
			
			// The PNG loader breaks whenever I try and load a png, so I had to do this :(
			BufferedImage bufferedImage = ImageIO.read(new File(path));
		    Texture texture = BufferedImageUtil.getTexture("", bufferedImage);
		    i = new Image(texture.getImageWidth(), texture.getImageHeight());
		    i.setTexture(texture);
		    i.setFilter(Image.FILTER_NEAREST);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			Logger.getLogger("error").log(Level.SEVERE, "Image [" + path + "] failed to load.", ex);
		}
		return i;
	}
	
	/**
	 * Loads a Sound found at a given file path starting in res/sounds/
	 * @param path The path to the file in the res/sounds/ directory
	 * @return The Sound found, or null if the file was not found or unable to be parsed
	 */
	public static Sound loadSound(String path)
	{
		Sound s = null;
		try
		{
			s = new Sound("res/sounds/" + path);
		}
		catch(SlickException ex)
		{
			Logger.getLogger("error").log(Level.SEVERE, "Sound [" + path + "] failed to load.", ex);
		}
		return s;
	}
	
	/**
	 * Registers a sound that can be found by a given sound name.
	 * @param soundName The String key to recieve the sound by. <b>Note: If a sound with this name already exists it will be overwritten</b>
	 * @param sound The Sound to store
	 */
	public static void registerSound(String soundName, Sound sound)
	{
		sounds.put(soundName, sound);
	}
	
	/**
	 * Registers an image that can be found by a given image name.
	 * @param imageName The String key to recieve the image by. <b>Note: If an image with this name already exists it will be overwritten</b>
	 * @param img The Image to store
	 */
	public static void registerImage(String imageName, Image img)
	{
		images.put(imageName, img);
	}
	
	/**
	 * Registers a spritesheet that can be found by a given sound name.
	 * @param soundName The String key to recieve the spritesheet by. <b>Note: If a spritesheet with this name already exists it will be overwritten</b>
	 * @param sound The SpriteSheet to store
	 */
	public static void registerSpriteSheet(String spriteSheetName, SpriteSheet sheet)
	{
		spriteSheets.put(spriteSheetName, sheet);
	}
	
	/**
	 * Returns the Sound found at the given String key
	 * @param name The key the Sound is stored at
	 * @return The Sound found at the given String key if found, null if not present.
	 */
	public static Sound getSound(String name)
	{
		return sounds.get(name);
	}
	
	/**
	 * Returns the SpriteSheet found at the given String key
	 * @param name The key the SpriteSheet is stored at
	 * @return The SpriteSheet found at the given String key if found, null if not present.
	 */
	public static SpriteSheet getSpriteSheet(String name)
	{
		return spriteSheets.get(name);
	}
	
	/**
	 * Returns the Image found at the given String key
	 * @param name The key the Image is stored at
	 * @return The Image found at the given String key if found, null if not present.
	 */
	public static Image getSpriteImage(String name, int x, int y)
	{
		return spriteSheets.get(name).getSubImage(x, y);
	}
	
	/**
	 * Returns the Image found at the given String key
	 * @param name The key the Image is stored at
	 * @return The Image found at the given String key if found, null if not present.
	 */
	public static Image getImage(String name)
	{
		return images.get(name);
	}
}
