package com.corntrip.turnbased.util;

import java.awt.Color;

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
	public static final int WINDOW_WIDTH = 960, WINDOW_HEIGHT = 640;
	
	
	public static final Color RESOURCE_SPAWN_POINT_KEY = new Color(255, 0, 0);
	
	public static final Color TREE_SPAWN_KEY = new Color(0, 255, 0);
	
	public static final Color WALL_SPAWN_KEY = new Color(0, 0, 255);
	
	public static final Color TOWN_HALL_KEY = new Color(255, 0, 255);
}
