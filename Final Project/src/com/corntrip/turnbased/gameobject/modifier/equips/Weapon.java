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

/**
 * Something that the holder uses to attack other LivingEntites. This is an equipable thing and thus implements {@link Equipable}.
 */
public abstract class Weapon implements Equipable
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
			// Hits every LivingEntity within it's range excluding the owner
			
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
	
	// Draws the items
	public void renderAt(GameContainer gc, Graphics gfx, float x, float y)
	{
		getImage().draw(x, y);
	}
	
	/**
	 * Called to activate the weapon's abilities (e.g. a bow to shoot, a sword to swing, etc.)
	 */
	public abstract void attack();
	
	/**
	 * Returns the upgraded version of this weapon
	 * @return The upgraded version of this weapon
	 */
	public abstract Weapon upgrade();
	
	/**
	 * Updates the weapon
	 * @param delta Time (in ms) passed since the last call
	 */
	public abstract void update(int delta);
	
	// Getters & Setters //
	
	public void setTier(int t) { tier = t; }
	public int getTier() { return tier; }
	public float getDamage() { return damage; }
	@Override
	public Entity getOwner() { return owner; }
	public Image getImage() { return image; }
	public void setImage(Image image) { this.image = image; }
	public void setOwner(Entity owner) { this.owner = owner; }
	public void setDamage(float damage) { this.damage = damage; }

	public abstract boolean isMaxTier();
}