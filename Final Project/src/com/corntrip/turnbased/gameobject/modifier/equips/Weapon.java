/*
 * Anthony Tornetta & Troy Cope | P5 | 3/31/18
 * This is our own work: ACT & TC
 * A weapon that has an attack function
 * Whenever an enemy is hit, if the owner is a player it's xp goes up
 */

package com.corntrip.turnbased.gameobject.modifier.equips;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.corntrip.turnbased.gameobject.Entity;
import com.corntrip.turnbased.gameobject.GameObject;
import com.corntrip.turnbased.gameobject.living.LivingEntity;
import com.corntrip.turnbased.gameobject.modifier.Equipable;

public abstract class Weapon extends Equipable
{
	//simply the stuffers
	private Entity owner;
	private float damage;
	private Image image;
	private int tier;
	
	//Start x and y are the owner's hit box
	public List<GameObject> generateHitbox(float startX, float startY, float width, float height)
	{
		List<GameObject> itemsHit = new ArrayList<>();
		
		for(GameObject a: owner.getWorld().getGameObjects())
		{
			//hahah no longer hits owner
			
			if(a instanceof LivingEntity)
			{
				if((!owner.equals(a) && ((LivingEntity)a).collidingWith(startX, startY, width, height)))
				{
					itemsHit.add((Entity) a);
				}
			}
		}
		return itemsHit;
	}
	
	//draws the items
	public void renderAt(GameContainer gc, Graphics gfx, float x, float y)
	{
		getImage().draw(x, y);
	}
	
	//defined in depth later
	public abstract void attack();
	
	public abstract Weapon upgrade();
	public abstract void update(int delta);
	
	// Getters & Setters //
	
	public void setTier(int t) { tier = t; }
	public int getTier() { return tier; }
	public float getDamage() { return damage; }
	public Entity getOwner() { return owner; }
	public Image getImage() { return image; }
	public void setImage(Image image) { this.image = image; }
	public void setOwner(Entity owner) { this.owner = owner; }
	public void setDamage(float damage) { this.damage = damage; }

	public abstract boolean isMaxTier();
}