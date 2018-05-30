/*
 * Anthony Tornetta & Troy Cope | P5 | 3/31/18
 * This is our own work: ACT & TC
 * ?
 */

package com.corntrip.turnbased.gameobject.modifier.entityAddition;

import java.util.ArrayList;

import com.corntrip.turnbased.gameobject.living.LivingEntity;
import com.corntrip.turnbased.gameobject.modifier.Modified;

/*
 * During unit spawn there should be a ArrayList of these bad boys and it should have a random chance to spawn a boss
 * Which then links it to a new Creation of one of these
 */


public class HPMod extends Modified
{

	private ArrayList<String> modNames;
	
	HPMod()
	{
		modNames = new ArrayList<String>();
		modNames.add("Giant");
		modNames.add("Huge");
		modNames.add("Gigantic");
		modNames.add("Large");
		modNames.add("Big");
	}
	
	public void modifyStats(LivingEntity a)
	{
		a.setHealth(a.getHealth() + 10);
	}

	/* 
	 * n: simply the name before
	 * a: list of possible modifiers
	 */
	public String modifiedName(String n) 
	{
		return modNames.get((int)(Math.random()*modNames.size())) + " " + n;
	}

}
