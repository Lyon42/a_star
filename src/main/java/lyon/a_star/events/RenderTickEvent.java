package main.java.lyon.a_star.events;

import java.awt.Graphics;

public class RenderTickEvent extends Event
{
	private Graphics g;
	private int width, height;
	
	public RenderTickEvent(Graphics g, int width, int height) 
	{
		this.g = g;
		this.width = width;
		this.height = height;
	}
	
	public Graphics getGraphics()
	{
		return this.g;
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	public int getHeight()
	{
		return this.height;
	}
}
