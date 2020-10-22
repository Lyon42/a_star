package main.java.lyon.a_star.render;

import javax.swing.JFrame;

public class Frame extends JFrame
{
	public static final int widthOffset = 16, heightOffset = 39;
	
	public Frame() 
	{
		this.setTitle("A*");
		this.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}
	
	@Override
	public void setSize(int width, int height) 
	{
		super.setSize(width + widthOffset, height + heightOffset);
		
		if(this.getContentPane() != null)
		{
			this.getContentPane().setSize(width, height);
		}
	}
}
