package com.corntrip.turnbased.util;

public class Helper
{
	/**
	 * Clamps a value between a minimum and a maximum
	 * @param val The value to clamp between the minimum and maximum
	 * @param min The minimum value to constrain the value between
	 * @param max The maximum value to constrain the value between
	 * @return if the value is less than the minimum, the minimum value passed; if it is greator than the max, then the max is returned.  Otherwise the value passed in is returned.
	 */
	public static float clamp(float val, float min, float max)
	{
		if(val < min)
			return min;
		if(val > max)
			return max;
		return val;
	}
	
	/**
	 * Finds the angle in degrees from point A to point B
	 * @param fromX Point A's x coordinate
	 * @param fromY Point A's y coordinate
	 * @param toX Point B's x coordinate
	 * @param toY Point B's y coordinate
	 * @return The angle in degrees from point A to point B
	 */
	public static float getAngle(float fromX, float fromY, float toX, float toY)
	{
		return (float)Math.toDegrees(Math.atan2(toY - fromY, toX - fromX));
	}
}
