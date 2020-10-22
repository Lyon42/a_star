package main.java.lyon.a_star.events;

import java.awt.Graphics;

import main.java.lyon.a_star.world.World;

public class RenderWorldEvent extends RenderTickEvent
{
	private World world;
	
	public RenderWorldEvent(Graphics g, int width, int height, World world) 
	{
		super(g, width, height);
		this.world = world;
	}
	
	public World getWorld()
	{
		return this.world;
	}
}
