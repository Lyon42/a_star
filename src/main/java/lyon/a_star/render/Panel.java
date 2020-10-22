package main.java.lyon.a_star.render;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import main.java.lyon.a_star.Main;

public class Panel extends JPanel
{
	private BufferedImage image;
	private Graphics graphics;
	
	public Panel() 
	{
		this.image = new BufferedImage(Main.frame.WIDTH, Main.frame.HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.graphics = this.image.getGraphics();
	}
	
	@Override
	public void setSize(int width, int height)
	{
		super.setSize(width, height);
		this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		this.graphics = this.image.getGraphics();
	}
	
	public void resetImage()
	{
		this.image.flush();
	}
	
	public Graphics getGraphics()
	{
		return this.graphics;
	}
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		g.drawImage(this.image, 0, 0, null);
	}
}
