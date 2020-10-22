package main.java.lyon.a_star.world;

import main.java.lyon.a_star.events.RenderWorldEvent;
import main.java.lyon.a_star.utils.Vec2i;

public class GameObject 
{
	protected Vec2i pos;
	protected boolean isObstacle;
	
	public GameObject(int x, int y) 
	{
		this.pos = new Vec2i(x, y);
	}
	
	public Vec2i getPosition()
	{
		return this.pos;
	}
	
	public boolean isObstacle()
	{
		return this.isObstacle;
	}
	
	public GameObject setIsObstacle(boolean b)
	{
		this.isObstacle = b;
		return this;
	}
	
	public void drawGameObject(RenderWorldEvent event) {}
}
