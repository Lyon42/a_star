package main.java.lyon.a_star.world;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import main.java.lyon.a_star.events.RenderWorldEvent;
import main.java.lyon.a_star.utils.Vec2i;

public class PathFinder 
{
	protected static final Vec2i[] sides = new Vec2i[]{new Vec2i(0, -1), new Vec2i(1, -1), new Vec2i(1, 0), new Vec2i(1, 1), new Vec2i(0, 1), new Vec2i(-1, 1), new Vec2i(-1, 0), new Vec2i(-1, -1)};
	
	protected boolean isSearchingForPath;
	protected final World world;
	
	protected PathNode startNode, endNode;
	protected List<PathNode> openNodes = new ArrayList<PathNode>();
	protected List<PathNode> closedNodes = new ArrayList<PathNode>();
	
	public PathFinder(World world) 
	{
		this.world = world;
	}
	
	public void tick()
	{
		if(this.isSearchingForPath)
		{
			if(!this.openNodes.isEmpty())
			{
				PathNode current = getNodeWithLowestFCostAndHCost();
				this.openNodes.remove(current);
				this.closedNodes.add(current);
				
				if(current.getPosition().equals(endNode.getPosition()))
				{
					this.stopSearching();
				}
				
				this.updateNeighbours(current);
			}
		}
	}
	
	public PathNode getNodeWithLowestFCostAndHCost()
	{
		PathNode lowest = null;
		
		for(PathNode node : this.openNodes)
		{
			if(node != null)
			{
				if(lowest == null || node.getFCost() < lowest.getFCost() || (node.getFCost() == lowest.getFCost() && node.getHCost() < lowest.getHCost()))
				{
					lowest = node;
				}
			}
			
			/*if(lowest == null || node.getHCost() < lowest.getHCost())
			{
				lowest = node;
			}*/
		}
		
		return lowest;
	}
	
	public void updateNeighbours(PathNode node)
	{
		for(Vec2i side : sides)
		{
			Vec2i vec = node.getPosition().add(side);
			
			if(this.world.isInWorld(vec))
			{
				if((this.world.getGameObjectAt(vec) != null && this.world.getGameObjectAt(vec).isObstacle()) || this.getNodeInList(this.closedNodes, vec) != null)
				{
					continue;
				}
				
				PathNode open = this.getNodeInList(this.openNodes, vec); 
				
				if(open == null)
				{
					open = new PathNode(vec.getX(), vec.getY());
					openNodes.add(open);
				}
				
				int gCost = node.getGCost() + node.getPosition().calcIntDist(open.getPosition());
				int hCost = open.getPosition().calcIntDist(this.endNode.getPosition());
				
				if(gCost + hCost < open.getFCost() || open.getFCost() == 0)
				{
					open.setGCost(gCost);
					open.setHCost(hCost);
				}
				
				open.setParentNode(node);
			}
		}
	}
	
	public PathNode getNodeInList(List<PathNode> list, Vec2i vec)
	{
		if(list != null && !list.isEmpty())
		{
			for(PathNode node : list)
			{
				if(node.getPosition().equals(vec))
				{
					return node;
				}
			}
		}
		
		return null;
	}
	
	public void setStartNode(PathNode startNode)
	{
		this.startNode = startNode;
		this.stopSearching();
	}
	
	public void setEndNode(PathNode endNode)
	{
		this.endNode = endNode;
		this.stopSearching();
	}
	
	public void startSearching()
	{
		if(this.startNode != null && this.endNode != null)
		{
			this.isSearchingForPath = true;
			
			this.clear();
			
			this.openNodes.add(this.startNode);
			this.startNode.setHCost(this.startNode.getPosition().calcIntDist(this.endNode.getPosition()));
		}
	}
	
	public void stopSearching()
	{
		this.isSearchingForPath = false;
	}
	
	public void drawPath(RenderWorldEvent event)
	{	
		if(!this.openNodes.isEmpty())
		{
			for(int i = 0; i < openNodes.size(); i++)
			{
				PathNode node = this.openNodes.get(i);
				
				if(node != null)
				{
					if(node != this.startNode && node != this.endNode)
					{
						node.setColor(Color.YELLOW);
					}
					
					node.drawGameObject(event);
				}
			}
		}
		
		if(!this.closedNodes.isEmpty())
		{
			for(int i = 0; i < closedNodes.size(); i++)
			{
				PathNode node = this.closedNodes.get(i);
				
				if(node != null)
				{
					if(node != this.startNode && node != this.endNode)
					{
						node.setColor(Color.RED);
					}
					
					node.drawGameObject(event);
				}
			}
		}
		
		if(this.startNode != null)
		{
			startNode.drawGameObject(event);
		}
		
		if(this.endNode != null)
		{
			endNode.drawGameObject(event);
		}
	}
	
	public void clear()
	{
		this.openNodes.clear();
		this.closedNodes.clear();
	}
	
	public boolean isSearching()
	{
		return this.isSearchingForPath;
	}
}
