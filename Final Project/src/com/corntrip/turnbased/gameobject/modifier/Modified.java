package com.corntrip.turnbased.gameobject.modifier;
import com.corntrip.turnbased.gameobject.living.LivingEntity;

public abstract class Modified
{
	LivingEntity assigned = null;
	//get and sets to what it's assigned
	public  void setAssinged(LivingEntity a)
	{
		assigned = a;
	}
	public LivingEntity getAssigned()
	{
		return assigned;
	}
	
	//lol we can have epic trees and super entities like bosses
	public abstract void modifyStats(LivingEntity a);
	
	//changes name
	public abstract String modifiedName(String n);
}