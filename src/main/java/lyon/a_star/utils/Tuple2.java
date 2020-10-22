package main.java.lyon.a_star.utils;

public class Tuple2<E1, E2>
{
	private E1 element1;
	private E2 element2;
	
	public Tuple2(E1 element1, E2 element2) 
	{
		this.element1 = element1;
		this.element2 = element2;
	}
	
	public E1 get1()
	{
		return this.element1;
	}
	
	public E2 get2()
	{
		return this.element2;
	}
}
