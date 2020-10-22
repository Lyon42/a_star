package main.java.lyon.a_star;

import java.util.concurrent.TimeUnit;
import main.java.lyon.a_star.events.EventHandler;
import main.java.lyon.a_star.events.RenderTickEvent;
import main.java.lyon.a_star.events.TickEvent;
import main.java.lyon.a_star.render.Frame;
import main.java.lyon.a_star.render.Panel;
import main.java.lyon.a_star.render.RenderWorld;
import main.java.lyon.a_star.utils.InputHandler;
import main.java.lyon.a_star.world.World;

public class Main 
{
	public static final int width = 1000, height = 1000;
	public static boolean tick = false, tick2 = false;
	public static Frame frame;
	public static Panel panel;
	public static World world;
	public static InputHandler inputHandler;
	public static final EventHandler eventHandler = new EventHandler();
	
	public static void main(String[] args) 
	{
		frame = new Frame();
		panel = new Panel();
		world = new World();
		inputHandler = new InputHandler();
		
		frame.setContentPane(panel);
		frame.setSize(world.columns * world.tileWidth + 1, world.rows * world.tileWidth + 1);
		frame.addMouseListener(inputHandler);
		frame.addKeyListener(inputHandler);
		frame.addMouseMotionListener(inputHandler);
		frame.setVisible(true);
		
		registerEventListeners();
		
		Thread renderThread = new Thread(new Runnable() 
		{
			@Override
			public void run() 
			{
				while(true)
				{
					try 
					{	
						panel.resetImage();
						eventHandler.onEvent(new RenderTickEvent(panel.getGraphics(), panel.getWidth(), panel.getHeight()));
						frame.repaint();
						
						TimeUnit.MILLISECONDS.sleep(16);
					} 
					catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
				}
			}
		});
		
		Thread tickThread = new Thread(new Runnable() 
		{
			@Override
			public void run() 
			{
				while(true)
				{
					eventHandler.onEvent(new TickEvent());
				}
			}
		});
		
		tickThread.start();
		renderThread.start();
	}
	
	private static void registerEventListeners()
	{
		eventHandler.registerEventListener(world);
		eventHandler.registerEventListener(new RenderWorld());
	}
}
