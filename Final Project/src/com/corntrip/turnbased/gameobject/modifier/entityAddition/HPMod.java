/*
 * Anthony Tornetta & Troy Cope | P5 | 3/31/18
 * This is our own work: ACT & TC
 * During unit spawn there should be a ArrayList of these bad boys and it should have a random chance to spawn a boss
 * Which then links it to a new Creation of one of these
 */

package com.corntrip.turnbased.gameobject.modifier.entityAddition;

import java.util.ArrayList;
import java.util.List;

import com.corntrip.turnbased.gameobject.living.LivingEntity;
import com.corntrip.turnbased.gameobject.modifier.Modified;

/**
 * During unit spawn there should be a List of these bad boys and it should have a random chance to spawn a boss
 * Which then links it to a new Creation of one of these
 */
public class HPMod extends Modified
{
	private List<String> modNames = new ArrayList<String>();
	
	public HPMod()
	{
		modNames.add("Giant");
		modNames.add("Huge");
		modNames.add("Gigantic");
		modNames.add("Large");
		modNames.add("Big");
	}
	
	@Override
	public void modifyStats(LivingEntity a)
	{
		a.setMaxHealth(a.getMaxHealth() + 10);
		a.setHealth(a.getMaxHealth());
	}
	
	@Override
	public String modifiedName(String n) 
	{
		return modNames.get((int)(Math.random()*modNames.size())) + " " + n;
	}
}
