/*
 * Anthony Tornetta & Troy Cope | P5 | 3/31/18
 * This is our own work: ACT & TC
 * ?
 */

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
	
	//changes stats to correspond with the name
	public abstract void modifyStats(LivingEntity a);
	
	/**
	 * Modifies the name i guess??
	 * @param n simply the name before
	 * @return idk pls comment this troy
	 */
	public abstract String modifiedName(String n);
}