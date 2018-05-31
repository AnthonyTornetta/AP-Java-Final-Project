/*
 * Anthony Tornetta & Troy Cope | P5 | 3/31/18
 * This is our own work: ACT & TC
 * Displays a black screen w/ score and "You Died!"
 */

package com.corntrip.turnbased.gui;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import com.corntrip.turnbased.util.Reference;

public class DeathScreenGUI extends GUIElement
{
	private TextGUI scoreGUI; // Says your score
	private TextGUI youDied; // Says "You Died!"
	
	/**
	 * Displays a black screen w/ score and "You Died!"
	 * @param score the score to display
	 */
	public DeathScreenGUI(int score)
	{
		super(0, 0);
		
		// Displays the score
		scoreGUI = new TextGUI(Reference.WINDOW_WIDTH / 2, Reference.WINDOW_HEIGHT / 2 - 50, "Score: " + score, Color.green);
		scoreGUI.setCentered(true);
		scoreGUI.setFont(new TrueTypeFont(new Font("Verdana", Font.BOLD, 45), true));
		
		// Displays "You Died!"
		youDied = new TextGUI(Reference.WINDOW_WIDTH / 2, Reference.WINDOW_HEIGHT / 2 - 120, "You Died!", Color.green);
		youDied.setCentered(true);
		youDied.setFont(new TrueTypeFont(new Font("Verdana", Font.BOLD, 45), true));
	}

	@Override
	public void render(GameContainer gc, Graphics gfx, float offsetX, float offsetY) throws SlickException
	{
		gfx.setColor(Color.black);
		gfx.fillRect(0, 0, Reference.WINDOW_WIDTH, Reference.WINDOW_HEIGHT);
		scoreGUI.render(gc, gfx, offsetX, offsetY);
		youDied.render(gc, gfx, offsetX, offsetY);
	}
}
