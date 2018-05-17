package com.corntrip.turnbased.util;

public class Vector2<T1, T2>
{
	private T1 x;
	private T2 y;
	
	public Vector2()
	{
		x = null;
		y = null;
	}
	
	public Vector2(T1 x, T2 y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Vector2(Vector2<T1, T2> vec)
	{
		x = vec.x;
		y = vec.y;
	}
	
	@Override
	public boolean equals(Object other)
	{
		if(other instanceof Vector2)
		{
			Vector2<?, ?> vec = (Vector2<?, ?>)other;
			
			if(vec.getX().equals(x))
			{
				if(vec.getY().equals(y))
				{
					return true;
				}
			}
		}
		
		return false;
	}

	@Override
	public String toString()
	{
		return "Vector2 [x=" + x + ", y=" + y + "]";
	}

	public T1 getX() { return x; }
	public void setX(T1 x) { this.x = x; }
	
	public T2 getY() { return y; }
	public void setY(T2 y) { this.y = y; }
}
