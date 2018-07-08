/*
 * Anthony Tornetta & Troy Cope | P5 | 3/31/18
 * This is our own work: ACT & TC
 * The player is a LivingEntity controlled by the user
 */

package com.corntrip.turnbased.gameobject.living;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;

import com.corntrip.turnbased.commands.CommandManager;
import com.corntrip.turnbased.gameobject.GameObject;
import com.corntrip.turnbased.gameobject.modifier.equips.Bow;
import com.corntrip.turnbased.gameobject.modifier.equips.Sword;
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

public class Player extends LivingEntity implements KeyListener
{
	/**
	 * Used for calculating movement
	 */
	private float velX = 0, velY = 0;
	
	/**
	 * Stores the experience for getting upgrades
	 */
	private int xp = 0;
	
	/**
	 * Holds if they are typing a command by pressing the '/' key
	 */
	boolean typingCommand = false;
	
	/**
	 * Holds the raw command of what the player has typed
	 */
	private TextGUI rawCmd = new TextGUI(10, 50 + 160, "", Color.white);
	
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
	
	/**
	 * Stores messages sent and recieved
	 */
	private TextGUI[] chatLog   = new TextGUI[10];
	private int[]     chatFades = new int[chatLog.length];
	
	/**
	 * What the chatLogFade resets to whenever a new message is added
	 */
	private final int CHATLOG_FADE_RESET = 5000;
	
	/**
	 * 1337 h@x0r m0d3
	 */
	private boolean invulnerable = false;
	
	/**
	 * For keys n stuff
	 */
	private Input input;
	
	// A bunch of GUIs
	private TextGUI nameGUI;
	private TextGUI xpGUI;
	private TextGUI scoreGUI;
	
	private ImageGUI[] upgradeSlots = new ImageGUI[3];
	private Weapon[] weapons = new Weapon[2];
	private int curWeapon = 0;
	
	/**
	 * Controllable Entity by the user
	 * @param startX The x to start the player at
	 * @param startY The y to start the player at
	 * @param w The width of the player
	 * @param h The height of the player
	 * @param world The world the player is in
	 */
	public Player(float startX, float startY, float w, float h, World world, Input in)
	{
		super(startX, startY, w, h, world, 20);
		
		healthBar = new HealthBarGUI(getX(), getY() - 8, getWidth(), 6, Color.red, Color.green, getHealth(), getMaxHealth());
		nameGUI = new TextGUI(getX(), getY() - 20, "Joe Shmoe", Color.blue);
		nameGUI.setCentered(true);
		
		setHealth(getHealth());
		healthBar.setHealth(getHealth());
		
		xpGUI = new TextGUI(30, 30, "0", Color.blue);
		scoreGUI = new TextGUI(Reference.WINDOW_WIDTH - 80, 30, "0", Color.blue);
		
		upgradeSlots[0] = new ImageGUI(0, 0, Resources.getSpriteImage("swords", 0, 0));
		upgradeSlots[1] = new ImageGUI(0, 0, Resources.getSpriteImage("bows", 0, 0));
		upgradeSlots[2] = new ImageGUI(0, 0, Resources.getImage("health"));
		
		weapons[1] = new Bow(this, 1);
		weapons[0] = new Sword(getX() + getWidth(), getY(), 32, 32, this, 1);
		
		input = in;
		
		input.addKeyListener(this);
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{
		getHeldWeapon().update(delta);
		
		// Basically what the friction is
		float subVal = 0.8f;
		
		// How much to move the player by based off any frames gained/missed
		float moveBy = Reference.MAX_FPS * delta / 1000.0f;
		
		// Factor in friction
		velX -= Math.signum(velX) * subVal * moveBy;
		velY -= Math.signum(velY) * subVal * moveBy;
		
		// If the velocities are somewhere near 0, just set them to 0 so it doesn't slide forever or jitter back and forth
		if(velX <= subVal && velX >= -subVal)
			velX = 0;
		if(velY <= subVal && velY >= -subVal)
			velY = 0;
		
		if(!typingCommand)
		{
			if(input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP))
				velY += -2.0f * moveBy;
			if(input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN))
				velY += 2.0f * moveBy;
			
			if(input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT))
				velX += -2.0f * moveBy;
			if(input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT))
				velX += 2.0f * moveBy;
			
			if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
			{
				getHeldWeapon().attack();
			}
			
			if(displayUpgradeGUI)
			{
				if(input.isKeyPressed(Input.KEY_Z))
				{
					if(!weapons[0].isMaxTier())
					{
						if(xp - 7 >= 0)
						{
							weapons[0] = weapons[0].upgrade();
							xp -= 7;
						}
					}
				}
				else if(input.isKeyPressed(Input.KEY_X))
				{
					if(!weapons[1].isMaxTier())
					{
						if(xp - 10 >= 0)
						{
							weapons[1] = weapons[1].upgrade();
							xp -= 10;
						}
					}
				}
				else if(input.isKeyPressed(Input.KEY_C))
				{
					if(xp - 2 >= 0)
					{
						heal(getMaxHealth());
						xp -= 2;
					}
				}
			}
			
			if(input.isKeyDown(Input.KEY_1))
				curWeapon = 0;
			else if(input.isKeyDown(Input.KEY_2))
				curWeapon = 1;
		}
		
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
					else if(!(go instanceof Enemy))
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
		
		// Make sure you're within the bounds of the world
		setX(Helper.clamp(getX(), 0, getWorld().getWidth() - getWidth()));
		setY(Helper.clamp(getY(), 0, getWorld().getHeight() - getHeight()));
		
		// If it's a swung weapon it needs to know where it's being held
		if(getHeldWeapon() instanceof SwungWeapon)
		{
			SwungWeapon sw = (SwungWeapon)getHeldWeapon();
			
			float xCalc = (90 - Math.abs(getRotation())) / 90.0f;
			sw.setX(getX() + getWidth() * xCalc);
			
			float r = getRotation();
			if(Math.abs(r) > 90)
				r = r - 2 * (r - Math.signum(r) * 90); // big maths
			
			float yCalc = r / 90;
			sw.setY(getY() + getHeight() * yCalc);
		}
		
		// Update the gui positions
		healthBar.setX(getX());
		healthBar.setY(getY() - 16);
		healthBar.setHealth(getHealth());
		
		nameGUI.setX(getX() + getWidth() / 2);
		nameGUI.setY(getY() - 46);
		
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
		
		// Make the chat blocks disapear after a given time
		for(int i = chatFades.length - 1; i >= 0; i--)
		{
			if(chatLog[i] != null)
			{
				chatFades[i] -= delta;
				if(chatFades[i] <= 0)
				{
					removeLastMessage(); // it will always be the last one
				}
			}
		}
		
		xpGUI.setText(getXp() + "xp");
		scoreGUI.setText(getWorld().getScore() + " score");
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
		nameGUI.render(gc, gfx, offsetX, offsetY);
		
		// The offset isn't passed to make sure they "stick" to the screen regardless of the camera's position in the world
		xpGUI.render(gc, gfx);
		scoreGUI.render(gc, gfx);
		
		if(displayUpgradeGUI)
		{
			for(GUIElement elem : upgradeSlots)
			{
				elem.render(gc, gfx, offsetX, offsetY);
			}
		}
		
		for(TextGUI gui : chatLog)
		{
			if(gui != null)
				gui.render(gc, gfx);
			else // if it's null, there's no text there or past that, so no point in iterating further
				break;
		}
		
		if(!rawCmd.getText().isEmpty())
			rawCmd.render(gc, gfx);
		
		setRotation(Helper.getAngle(anchorX, anchorY, mouseX, mouseY));
		
		if(Reference.DEBUG)
		{
			gfx.setColor(Color.green);
			gfx.drawLine(anchorX, anchorY, input.getMouseX(), input.getMouseY());
		}
		
		if(Reference.DEBUG)
		{
			if(getHeldWeapon() instanceof SwungWeapon)
			{
				gfx.setColor(Color.blue);
				SwungWeapon sw = (SwungWeapon)getHeldWeapon();
				gfx.drawRect(sw.getX() - offsetX, sw.getY() - offsetY, getWidth(), getHeight());
			}
		}
		
		gfx.rotate(anchorX, anchorY, getRotation());
		
		float drawX = getX() - offsetX;
		float drawY = getY() - offsetY;
		texture.draw(drawX, drawY);
		
		getHeldWeapon().renderAt(gc, gfx, drawX + getWidth(), drawY);
	}
	
	/**
	 * Adds the resource's point value to the score, and initiates the next wave
	 * @param r The resource to score
	 */
	private void scoreResource(Resource r)
	{
		if(r != null)
		{
			getWorld().addToScore(r.getPtsValue());
			getWorld().initateNextWave();
		}
	}
	
	/**
	 * Adds a message to the player's chat box
	 * @param msg The message to add
	 */
	public void sendMessage(String msg)
	{
		addMessage(msg);
		// maybe do a message sound here?
	}
	
	/**
	 * Adds a message to the list of messages that will be displayed to the player and kicks out any messages past the 9th to make room
	 * @param msg The message to add
	 */
	public void addMessage(String msg)
	{
		for(int i = chatLog.length - 1; i > 0; i--)
		{
			if(chatLog[i - 1] != null)
			{
				TextGUI old = chatLog[i - 1];
				chatLog[i] = new TextGUI(old.getX(), old.getY() + 16, old.getText(), Color.white);
				chatFades[i] = chatFades[i - 1];
			}
		}
		
		chatLog[0] = new TextGUI(10, 50, msg, Color.white);
		chatFades[0] = CHATLOG_FADE_RESET;
	}
	
	/**
	 * Removes the last message in the list that isn't null
	 */
	private void removeLastMessage()
	{
		for(int i = chatLog.length - 1; i >= 0; i--)
		{
			if(chatLog[i] != null)
			{
				chatLog[i] = null;
				chatFades[i] = 0; // Not that it matters if I do this since it won't use this value unless the slot isn't null, and this value is overwritten when chatLog[i] is defined :p. It just feels proper I guess.
				break;
			}
		}
	}
	
	@Override
	public void takeDamage(int amt)
	{
		if(!invulnerable)
			super.takeDamage(amt);
	}
	
	// Key Input
	@Override
	public void keyPressed(int keyCode, char charRepresentation)
	{
		/*
		 * Commands start by the player entering a '/'
		 * Then all input is funneled into the rawCmd variable
		 * The command input ends when the enter key is pressed
		 */
		
		if(!typingCommand && keyCode == Input.KEY_ENTER)
		{
			typingCommand = true;
		}
		else if(typingCommand && keyCode == Input.KEY_ENTER)
		{
			int spaceIndex = rawCmd.getText().indexOf(" ");
			
			if(spaceIndex == -1)
			{
				CommandManager.sendCommand(rawCmd.getText(), new String[] {}, this);
			}
			else
			{
				String cmd = rawCmd.getText().substring(0, spaceIndex);
				String[] args = rawCmd.getText().substring(spaceIndex + 1).split(" ");
				
				CommandManager.sendCommand(cmd, args, this);
			}
			
			rawCmd.setText("");
			typingCommand = false;
		}
		else if(typingCommand)
		{
			if(keyCode == Input.KEY_BACK)
			{
				if(rawCmd.getText().length() > 0)
				{
					rawCmd.setText(rawCmd.getText().substring(0, rawCmd.getText().length() - 1));
				}
			}
			else if(keyCode != Input.KEY_LSHIFT && keyCode != Input.KEY_RSHIFT && keyCode != Input.KEY_CAPITAL && keyCode != Input.KEY_NUMLOCK) // These dont matter as slick2d handles them for us
				rawCmd.setText(rawCmd.getText() + charRepresentation);
		}
	}
	
	@Override
	public void keyReleased(int keyCode, char charRepresentation)
	{}
	
	@Override
	public boolean isAcceptingInput() {	return true; }

	@Override
	public void setInput(Input in) { input = in; }
	
	@Override
	public void inputEnded()
	{}

	@Override
	public void inputStarted()
	{}
	
	// For creating copies of this	
	@Override
	public LivingEntity clone()
	{
		return new Player(getX(), getY(), getWidth(), getHeight(), getWorld(), input);
	}
	
	// Getters & Setters //
	
	public Weapon getHeldWeapon() { return weapons[curWeapon]; }
	
	public int getXp() { return xp; }
	public void addXp(int amt) { xp += amt; }
	public void setXp(int xp) { this.xp = xp; }
	
	public void setScore(int score) { getWorld().setScore(score); }	
	public int getScore() { return getWorld().getScore(); }
	
	public void setInvulnerable(boolean inv) { invulnerable = inv; }
	public boolean isInvulnerable() { return invulnerable; }	
}
