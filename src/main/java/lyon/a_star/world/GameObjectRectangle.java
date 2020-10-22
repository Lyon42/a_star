package main.java.lyon.a_star.world;

import java.awt.Color;

import main.java.lyon.a_star.events.RenderWorldEvent;

public class GameObjectRectangle extends GameObject
{
	private Color color;
	
	public GameObjectRectangle(int x, int y, Color color) 
	{
		super(x, y);
		this.color = color;
	}

	public Color getColor()
	{
		return this.color;
	}
	
	public void setColor(Color color)
	{
		this.color = color;
	}
	
	@Override
	public void drawGameObject(RenderWorldEvent event) 
	{
		int x = pos.getX() * event.getWorld().tileWidth + 1;
		int y = pos.getY() * event.getWorld().tileHeight + 1;
		
		event.getGraphics().setColor(this.color);
		event.getGraphics().fillRect(x, y, event.getWorld().tileWidth - 1, event.getWorld().tileHeight - 1);
	}
}
