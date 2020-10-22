package main.java.lyon.a_star.render;

import java.awt.Color;
import main.java.lyon.a_star.events.EventHandler.EventListener;
import main.java.lyon.a_star.events.RenderWorldEvent;
import main.java.lyon.a_star.world.GameObject;

public class RenderWorld 
{
	@EventListener
	public void onRenderWorld(RenderWorldEvent event)
	{
		this.drawBackground(event);
		//this.drawGrid(event);
		this.drawGameObjects(event);
	}
	
	protected void drawBackground(RenderWorldEvent event)
	{
		event.getGraphics().setColor(Color.GRAY);
		event.getGraphics().fillRect(0, 0, event.getWidth(), event.getHeight());
	}
	
	protected void drawGrid(RenderWorldEvent event)
	{
		int rows = event.getWorld().rows, columns = event.getWorld().columns;
		
		event.getGraphics().setColor(Color.BLACK);
		
		for(int x = 0; x <= columns; x++)
		{
			int x0 = event.getWorld().tileWidth * x;
			
			event.getGraphics().drawLine(x0, 0, x0, event.getHeight());
		}
		
		for(int y = 0; y <= rows; y++)
		{
			int y0 = event.getWorld().tileHeight * y;
			
			event.getGraphics().drawLine(0, y0, event.getWidth(), y0);
		}
	}
	
	public void drawGameObjects(RenderWorldEvent event)
	{
		for(int i = 0; i < event.getWorld().gameObjects.size(); i++)
		{
			GameObject obj = event.getWorld().gameObjects.get(i);
			
			obj.drawGameObject(event);
		}
		
		event.getWorld().pathFinder.drawPath(event);
	}
}
