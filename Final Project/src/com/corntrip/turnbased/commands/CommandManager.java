package com.corntrip.turnbased.commands;

import com.corntrip.turnbased.gameobject.living.Enemy;
import com.corntrip.turnbased.gameobject.living.Player;
import com.corntrip.turnbased.world.World;

public class CommandManager
{
	public static void sendCommand(String cmd, String[] args, Player sender)
	{
		if(cmd == null || cmd.isEmpty())
			return;
		
		switch(cmd.toLowerCase())
		{
		case "hello":
			sender.sendMessage("Hello World!");
			break;
		case "ping":
			sender.sendMessage("pong");
			break;
		case "die":
			sender.die();
			break;
		case "heal":
			sender.setHealth(sender.getMaxHealth());
			break;
		case "butcher":
			World w = sender.getWorld();
			
			int butcheredAmt = 0;
			
			for(int i = 0; i < w.getGameObjects().size(); i++)
			{
				if(w.getGameObjects().get(i) instanceof Enemy)
				{
					w.removeObject(i); // Doesn't remove it but it will be removed once the update is done being called, which means I should not decrement i
					butcheredAmt++;
				}
			}
			
			sender.sendMessage(butcheredAmt + " enemies butchered.");
			break;
		case "xp":
			if(checkArgs(args, 1, sender))
			{
				try
				{
					int xp = Integer.parseInt(args[0]);
					sender.setXp(xp);
				}
				catch(NumberFormatException ex)
				{
					sender.sendMessage("You must enter a valid integer!");
				}
			}
			break;
		case "score":
			if(checkArgs(args, 1, sender))
			{
				try
				{
					int score = Integer.parseInt(args[0]);
					sender.setScore(score);
				}
				catch(NumberFormatException ex)
				{
					sender.sendMessage("You must enter a valid integer!");
				}
			}
			break;
		case "wave":
			if(args.length == 0)
			{
				sender.getWorld().initateNextWave();
				sender.sendMessage("Next wave initiated.");
			}
			else
			{
				try
				{
					int waveNumber = Integer.parseInt(args[0]);
					
					while(sender.getWorld().getWave() < waveNumber)
					{
						sender.getWorld().initateNextWave();
						sender.sendMessage("Wave " + sender.getWorld().getWave() + " spawned!");
					}
					sender.sendMessage("All waves spawned.");
				}
				catch(NumberFormatException ex)
				{
					sender.sendMessage("You must enter a valid integer!");
				}
			}
			break;
		case "op":
			if(sender.isInvulnerable())
			{
				sender.setInvulnerable(false);
				sender.sendMessage("OP mode deactivated.");
			}
			else
			{
				sender.setInvulnerable(true);
				sender.sendMessage("OP mode activated.");
			}
			break;
		default:
			sender.sendMessage("Invalid Command!");
			break;
		}
	}
	
	private static boolean checkArgs(String[] args, int len, Player sender)
	{
		if(args.length >= len)
		{
			return true;
		}
		else
		{
			sender.sendMessage("There should be " + len + " argument" + (len != 0 ? "s" : ""));
			return false;
		}
	}
}
