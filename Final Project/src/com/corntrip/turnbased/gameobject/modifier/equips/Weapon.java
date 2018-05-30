package com.corntrip.turnbased.gameobject.modifier.equips;

import java.util.ArrayList;

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
	public ArrayList<Entity> generateHitbox(float startX, float startY, float width, float height)
	{
		ArrayList<Entity> itemsHit = new ArrayList<>();
		
		for(GameObject a: owner.getWorld().getEntities())
		{
			if(a instanceof LivingEntity)
			{
				//hahah no longer hits owner
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