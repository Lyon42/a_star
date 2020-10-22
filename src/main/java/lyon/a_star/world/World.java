package main.java.lyon.a_star.world;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import main.java.lyon.a_star.Main;
import main.java.lyon.a_star.events.RenderTickEvent;
import main.java.lyon.a_star.events.RenderWorldEvent;
import main.java.lyon.a_star.events.TickEvent;
import main.java.lyon.a_star.render.Frame;
import main.java.lyon.a_star.utils.Vec2i;
import main.java.lyon.a_star.events.EventHandler.EventListener;

public class World 
{
	public int rows = 500, columns = 500;
	public int tileWidth, tileHeight;
	public List<GameObject> gameObjects = new ArrayList<GameObject>();
	public PathFinder pathFinder;
	
	public World() 
	{
		this.tileWidth = Math.round(Main.width / this.columns);
		this.tileHeight = Math.round(Main.height / this.rows);
		
		this.pathFinder = new PathFinder(this);
	}
	
	public void addObstacle(Vec2i vec)
	{
		if(this.isInWorld(vec))
		{
			GameObject obj = this.getGameObjectAt(vec);
			
			if(obj != null)
			{
				this.removeGameObjectFromWorld(obj);
			}
			
			this.addGameObjectToWorld(new GameObjectRectangle(vec.getX(), vec.getY(), Color.BLACK).setIsObstacle(true));
		}
	}
	
	@EventListener
	public void onRenderTick(RenderTickEvent event)
	{
		Main.eventHandler.onEvent(new RenderWorldEvent(event.getGraphics(), event.getWidth(), event.getHeight(), this));
	}
	
	@EventListener
	public void onTick(TickEvent event)
	{
		this.pathFinder.tick();
	}
	
	public GameObject addGameObjectToWorld(GameObject gameObject)
	{
		if(this.isInWorld(gameObject.getPosition()) && this.getGameObjectAt(gameObject.getPosition()) == null)
		{
			gameObjects.add(gameObject);
			return gameObject;
		}
		
		return null;
	}
	
	public void removeGameObjectAt(Vec2i vec)
	{
		GameObject obj = this.getGameObjectAt(vec);
		
		if(obj != null)
		{
			this.removeGameObjectFromWorld(obj);
		}
	}
	
	public void removeGameObjectFromWorld(GameObject gameObject)
	{
		this.gameObjects.remove(gameObject);
	}
	
	public GameObject getGameObjectAt(Vec2i pos)
	{
		for(GameObject obj : this.gameObjects)
		{
			if(obj.getPosition().equals(pos))
			{
				return obj;
			}
		}
		
		return null;
	}
	
	public boolean isInWorld(Vec2i pos)
	{
		if(pos.getX() >= 0 && pos.getY() >= 0 && pos.getX() < columns && pos.getY() < rows)
		{
			return true;
		}
		
		return false;
	}
	
	public Vec2i getTileClicked(int mouseX, int mouseY)
	{
		int xPos = (mouseX - Frame.widthOffset / 2) / this.tileWidth;
		int yPos = (mouseY - Frame.heightOffset + Frame.widthOffset / 2) / this.tileHeight;
		
		return new Vec2i(xPos, yPos);
	}
}
