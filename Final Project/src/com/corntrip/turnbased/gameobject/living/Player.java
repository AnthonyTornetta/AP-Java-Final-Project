package com.corntrip.turnbased.gameobject.living;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.gameobject.GameObject;
import com.corntrip.turnbased.gameobject.modifier.equips.Bow;
import com.corntrip.turnbased.gameobject.modifier.equips.SwungWeapon;
import com.corntrip.turnbased.gameobject.modifier.equips.Weapon;
import com.corntrip.turnbased.gameobject.modifier.equips.weaponUtil.Projectile;
import com.corntrip.turnbased.gameobject.nonliving.resources.Resource;
import com.corntrip.turnbased.gameobject.nonliving.resources.ResourceDeposit;
import com.corntrip.turnbased.gameobject.nonliving.townhall.Townhall;
import com.corntrip.turnbased.gui.GUIElement;
import com.corntrip.turnbased.gui.HealthBarGUI;
import com.corntrip.turnbased.gui.ImageGUI;
import com.corntrip.turnbased.gui.TextGUI;
import com.corntrip.turnbased.util.Helper;
import com.corntrip.turnbased.util.Reference;
import com.corntrip.turnbased.util.Resources;
import com.corntrip.turnbased.world.World;

public class Player extends LivingEntity
{
	/**
	 * Used for calculating movement
	 */
	private float velX = 0, velY = 0;
	
	/**
	 * Holds the points aquired from scoring resources
	 */
	private int pts = 0;
	
	/**
	 * Holds the resource the player may or may not be carrying
	 */
	private Resource resourceCarrying = null;
	
	/**
	 * A flag to tell if the upgrade GUI should be displayed
	 */
	private boolean displayUpgradeGUI = false;
	
	/**
	 * The texture of the player
	 */
	private Image texture = Resources.getImage("player");
	
	/**
	 * Max velocity the player should move
	 */
	private static final float MAX_MOVE_SPEED = 5.0f;
	
	/**
	 * A healthbar to display
	 */
	private HealthBarGUI healthBar;
	
	private TextGUI txt;
	
	private ImageGUI[] upgradeSlots = new ImageGUI[3];
	
	private Weapon weapon;
	
	/**
	 * Controllable Entity by the user
	 * @param startX The x to start the player at
	 * @param startY The y to start the player at
	 * @param w The width of the player
	 * @param h The height of the player
	 * @param world The world the player is in
	 */
	public Player(float startX, float startY, float w, float h, World world)
	{
		super(startX, startY, w, h, world, 20);
		
		healthBar = new HealthBarGUI(getX(), getY() - 8, getWidth(), 6, Color.red, Color.green, getHealth(), getMaxHealth());
		txt = new TextGUI(getX(), getY() - 20, "Joe Shmoe", Color.red);
		txt.setCentered(true);
		
		setHealth(getHealth());
		healthBar.setHealth(getHealth());
		
		upgradeSlots[0] = new ImageGUI(0, 0, Resources.getImage("player"));
		upgradeSlots[1] = new ImageGUI(0, 0, Resources.getImage("player"));
		upgradeSlots[2] = new ImageGUI(0, 0, Resources.getImage("player"));
		
		weapon = new Bow(this, Resources.getImage("bow"), 1);
		//weapon = new Sword(getX() + getWidth(), getY(), 32, 32, this, Resources.getImage("sword"), 1);//new Bow(this, Resources.getImage("bow"));
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{
		weapon.update(delta);
		
		float subVal = 0.8f;
		float moveBy = Reference.MAX_FPS * delta / 1000.0f;
		
		velX -= Math.signum(velX) * subVal * moveBy;
		velY -= Math.signum(velY) * subVal * moveBy;
		
		// If the velocities are somewhere near 0, just set them to 0 so it doesn't slide forever
		if(velX <= subVal && velX >= -subVal)
			velX = 0;
		if(velY <= subVal && velY >= -subVal)
			velY = 0;
		
		// Fun movement calcs in here
		
		Input in = gc.getInput();
		if(in.isKeyDown(Input.KEY_W) || in.isKeyDown(Input.KEY_UP))
			velY += -2.0f * moveBy;
		if(in.isKeyDown(Input.KEY_S) || in.isKeyDown(Input.KEY_DOWN))
			velY += 2.0f * moveBy;
		
		if(in.isKeyDown(Input.KEY_A) || in.isKeyDown(Input.KEY_LEFT))
			velX += -2.0f * moveBy;
		if(in.isKeyDown(Input.KEY_D) || in.isKeyDown(Input.KEY_RIGHT))
			velX += 2.0f * moveBy;
		
		
		if(in.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
		{
			weapon.attack();
		}
		// End movement calcs
				
		if(resourceCarrying == null)
		{
			velX = Helper.clamp(velX, -MAX_MOVE_SPEED, MAX_MOVE_SPEED);
			velY = Helper.clamp(velY, -MAX_MOVE_SPEED, MAX_MOVE_SPEED);
		}
		else
		{
			velX = Helper.clamp(velX, -MAX_MOVE_SPEED / 3, MAX_MOVE_SPEED / 3);
			velY = Helper.clamp(velY, -MAX_MOVE_SPEED / 3, MAX_MOVE_SPEED / 3);
		}
		
		float newX = velX + getX();
		float newY = velY + getY();
		
		List<GameObject> objs = getWorld().getGameObjects();
		for(int i = 0; i < objs.size(); i++)
		{
			if(!objs.get(i).equals(this))
			{
				GameObject go = objs.get(i);
				
				if(go instanceof Townhall)
				{
					Townhall th = (Townhall)go;
					if(th.withinRange(this))
						displayUpgradeGUI = true;
					else
						displayUpgradeGUI = false;
				}
				
				if(go.collidingWith(newX, newY, getWidth(), getHeight()))
				{
					if(go instanceof ResourceDeposit)
					{
						if(resourceCarrying != null)
						{
							scoreResource(resourceCarrying);
							resourceCarrying = null;
						}
					}
					else if(go instanceof Resource)
					{
						if(resourceCarrying == null)
						{
							resourceCarrying = (Resource) go;
							getWorld().removeObject(go);
						}
					}
					else
					{
						if(go.collidingWith(newX, newY, getWidth(), getHeight()))
						{
							if(go instanceof Projectile)
							{
								Projectile proj = (Projectile)go;
								if(proj.getWeapon().getOwner().equals(this))
								{
									continue;
								}
							}
							
							float oldX = getX();
							float oldY = getY();
							
							final float FACTOR = 0.001f; // the lower this is the more precise but the more iterations - this is a decent value
							
							float difX = newX - oldX;
							float difY = newY - oldY;
							
							while(go.collidingWith(getX() + difX, getY(), getWidth(), getHeight()))
								difX -= Math.signum(velX) * FACTOR;
							while(go.collidingWith(getX(), getY() + difY, getWidth(), getHeight()))
								difY -= Math.signum(velY) * FACTOR;								
							
							newX = getX() + difX;
							newY = getY() + difY;
						}
					}
				}
			}
		}
		
		if(newX != getX() + velX) // If the newX is not equal to the original calculation, the object collided into something causing it to change
			velX = 0; // Since you just rammed into something, I don't think your going in that direction any more
		if(newY != getY() + velY)
			velY = 0;
		
		setX(newX);
		setY(newY);
		
		setX(Helper.clamp(getX(), 0, getWorld().getWidth() - getWidth()));
		setY(Helper.clamp(getY(), 0, getWorld().getHeight() - getHeight()));
		
		if(weapon instanceof SwungWeapon)
		{
			SwungWeapon sw = (SwungWeapon)weapon;
			
			sw.setX(getX() + Helper.clamp(-2 * (Math.abs(getRotation()) / 90) + 2 * getWidth(), -1, 1));
			sw.setY(getY() + ((getRotation() % 45) / 45.0f) * getHeight());
		}
		
		healthBar.setX(getX());
		healthBar.setY(getY() - 16);
		healthBar.setHealth(getHealth());
		
		txt.setX(getX() + getWidth() / 2);
		txt.setY(getY() - 46);
		
		upgradeSlots[0].setX(getX() - 48);
		upgradeSlots[0].setY(getY() - 128);
		upgradeSlots[1].setX(getX());
		upgradeSlots[1].setY(getY() - 128);
		upgradeSlots[2].setX(getX() + 48);
		upgradeSlots[2].setY(getY() - 128);
		
		if(getWorld().getTownhall().withinRange(this))
		{
			displayUpgradeGUI = true;
		}
	}
	
	@Override
	public void render(GameContainer gc, Graphics gfx, float offsetX, float offsetY) throws SlickException
	{		
		gfx.setColor(Color.green);
		
		// For rotation
		Input input = gc.getInput();
		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();
		
		float anchorX = getAnchorPointX(offsetX);
		float anchorY = getAnchorPointY(offsetY);
		
		healthBar.render(gc, gfx, offsetX, offsetY);
		txt.render(gc, gfx, offsetX, offsetY);
		
		if(displayUpgradeGUI)
		{
			for(GUIElement elem : upgradeSlots)
			{
				elem.render(gc, gfx, offsetX, offsetY);
			}
		}
		
		setRotation(Helper.getAngle(anchorX, anchorY, mouseX, mouseY));
		
		gfx.setColor(Color.green);
		if(Reference.DEBUG)
			gfx.drawLine(anchorX, anchorY, input.getMouseX(), input.getMouseY());
		
		gfx.rotate(anchorX, anchorY, getRotation());
		
		float drawX = getX() - offsetX;
		float drawY = getY() - offsetY;
		texture.draw(drawX, drawY);
		
		weapon.renderAt(gc, gfx, drawX + getWidth(), drawY);
	}
	
	private void scoreResource(Resource r)
	{
		if(r != null)
			pts += r.getPtsValue();
	}
	
	@Override
	public void die()
	{
		super.die();
		getWorld().removeObject(this);
	}
	
	@Override
	public LivingEntity clone()
	{
		return new Player(getX(), getY(), getWidth(), getHeight(), getWorld());
	}
	
	public int getPoints() { return pts; }
	public void setPoints(int p) { pts = p; }
}
