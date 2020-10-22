package main.java.lyon.a_star.utils;

public class Vec2i 
{
	private int x, y;
	
	public Vec2i(int x, int y) 
	{
		this.x = x;
		this.y = y;
	}
	
	public Vec2i add(Vec2i vec)
	{
		return new Vec2i(this.x + vec.getX(), this.y + vec.getY());
	}
	
	public Vec2i subtract(Vec2i vec)
	{
		return new Vec2i(this.x - vec.getX(), this.y - vec.getY());
	}
	
	public int calcIntDist(Vec2i vec)
	{
		Vec2i diff = this.subtract(vec);
		
		/*int a = Math.abs(diff.getX() - diff.getY());
		int b = (Math.abs(diff.getX()) + Math.abs(diff.getY()) - a) / 2;
		int dist = a * 10 + b * 14;*/
		
		int dist = diff.getX() * diff.getX() + diff.getY() * diff.getY();
		
		return dist;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if(obj instanceof Vec2i)
		{
			Vec2i vec = (Vec2i)obj;
			
			if(vec.getX() == this.x && vec.getY() == this.y)
			{
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public String toString() 
	{
		return this.x + " / " + this.y;
	}
}
