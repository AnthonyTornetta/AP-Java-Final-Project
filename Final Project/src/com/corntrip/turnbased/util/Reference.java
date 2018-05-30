package com.corntrip.turnbased.util;

import java.awt.Color;
import java.awt.Font;

import org.newdawn.slick.TrueTypeFont;

public class Reference
{
	/**
	 * Indicates whether or not the game is currently being debugged
	 */
	public static final boolean DEBUG = true;
	
	/**
	 * The maximum FPS the window should reach
	 */
	public static final int MAX_FPS = 60;
	
	/**
	 * Each tile in the world is 32x32
	 */
	public static final int TILE_DIMENSIONS = 32;
	
	/**
	 * The dimensions of the window
	 */
	public static final int WINDOW_WIDTH = 720, WINDOW_HEIGHT = (int)(WINDOW_WIDTH * 12.0/16);
	
	/**
	 * If a float is +- off a number by this amount they're basically equal
	 */
	public static final float ALMOST_EQUAL = 0.004f;
	
	public static final Color RESOURCE_SPAWN_POINT_KEY = new Color(255, 0, 0);
	public static final Color TREE_SPAWN_KEY           = new Color(0, 255, 0);
	public static final Color WALL_SPAWN_KEY           = new Color(0, 0, 255);
	public static final Color TOWN_HALL_KEY            = new Color(255, 0, 255);
	public static final Color DEPOSIT_KEY              = new Color(0, 0, 0);
	public static final Color PLAYER_KEY               = new Color(255, 255, 0);
	
	public static final Color TEST_ENEMY               = new Color(255, 0, 128);
	
	public static final TrueTypeFont FONT_DEFAULT = new TrueTypeFont(new Font("Verdana", Font.PLAIN, 16), true);
	public static final TrueTypeFont FONT_BOLD    = new TrueTypeFont(new Font("Verdana", Font.BOLD , 16), true);
}
