package com.corntrip.turnbased.util;

public class Helper
{
	public static float clamp(float val, float min, float max)
	{
		if(val < min)
			return min;
		if(val > max)
			return max;
		return val;
	}
}
