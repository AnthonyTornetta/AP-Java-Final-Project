package com.corntrip.turnbased.world;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.corntrip.turnbased.gameobject.nonliving.Wall;
import com.corntrip.turnbased.gameobject.nonliving.resources.GoldResource;
import com.corntrip.turnbased.gameobject.nonliving.resources.ResourceGenerator;
import com.corntrip.turnbased.util.Reference;

public class WorldLoader
{
	public static World generateWorldFromImage(BufferedImage img)
	{
		World world = new World(img.getWidth() * Reference.TILE_DIMENSIONS, img.getHeight() * Reference.TILE_DIMENSIONS);
		
		for(int y = 0; y < img.getHeight(); y++)
		{
			for(int x = 0; x < img.getWidth(); x++)
			{
				Color c = new Color(img.getRGB(x, y));
				
				if(c.equals(Reference.RESOURCE_SPAWN_POINT_KEY))
				{
					world.addObject(new ResourceGenerator(x * Reference.TILE_DIMENSIONS, y * Reference.TILE_DIMENSIONS, 
							Reference.TILE_DIMENSIONS, Reference.TILE_DIMENSIONS, world, 10000, 
							new GoldResource(Reference.TILE_DIMENSIONS * x + Reference.TILE_DIMENSIONS / 2, 
									Reference.TILE_DIMENSIONS * y + Reference.TILE_DIMENSIONS / 2, 
									Reference.TILE_DIMENSIONS / 2, Reference.TILE_DIMENSIONS / 2)));
				}
				
				if(c.equals(Reference.TREE_SPAWN_KEY) || c.equals(Reference.WALL_SPAWN_KEY))
				{
					world.addObject(new Wall(x * Reference.TILE_DIMENSIONS, y * Reference.TILE_DIMENSIONS, Reference.TILE_DIMENSIONS, Reference.TILE_DIMENSIONS));
				}
			}
		}
		
		return world;
	}
}
