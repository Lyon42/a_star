package main.java.lyon.a_star.world;

import java.awt.Color;
import main.java.lyon.a_star.events.RenderWorldEvent;

public class PathNode extends GameObjectRectangle
{
	private int hCost, gCost;
	private PathNode parentNode;
	
	public PathNode(int x, int y) 
	{
		this(x, y, Color.WHITE);
	}
	
	public PathNode(int x, int y, Color color) 
	{
		super(x, y, color);
	}
	
	public void setGCost(int gCost)
	{
		this.gCost = gCost;
	}
	
	public void setHCost(int hCost)
	{
		this.hCost = hCost;
	}
	
	public int getFCost()
	{
		return this.gCost + this.hCost;
	}
	
	public int getGCost()
	{
		return this.gCost;
	}
	
	public int getHCost()
	{
		 return this.hCost;
	}
	
	public void setParentNode(PathNode parentNode) 
	{
		this.parentNode = parentNode;
	}
	
	public PathNode getParentNode() 
	{
		return this.parentNode;
	}
	
	@Override
	public void drawGameObject(RenderWorldEvent event) 
	{
		super.drawGameObject(event);
		
		/*int nodeWidth = event.getWidth() / event.getWorld().columns;
		int nodeHeight = event.getHeight() / event.getWorld().rows;
		
		int x = pos.getX() * nodeWidth + 1;
		int y = pos.getY() * nodeHeight + 1;
		
		byte[] text1 = ("" + this.getGCost()).getBytes();
		byte[] text2 = (""+ this.getHCost()).getBytes();
		byte[] text3 = (""+ this.getFCost()).getBytes();
		
		event.getGraphics().setColor(Color.BLACK);
		event.getGraphics().drawBytes(text1, 0, text1.length, x, y + 10);
		event.getGraphics().drawBytes(text2, 0, text2.length, x, y + 20);
		event.getGraphics().drawBytes(text3, 0, text3.length, x, y + 30);
		event.getGraphics().setColor(this.getColor());*/
	}
}
