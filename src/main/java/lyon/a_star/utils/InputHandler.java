package main.java.lyon.a_star.utils;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.java.lyon.a_star.Main;
import main.java.lyon.a_star.world.PathNode;

public class InputHandler implements MouseListener, MouseMotionListener, KeyListener
{
	private InputMode mode = InputMode.NONE;
	private Vec2i prevMousePos;
	private boolean isMouseDown;
	
	@Override
	public void keyTyped(KeyEvent e) 
	{
		if(e.getKeyChar() == '1')
		{
			mode = InputMode.START_NODE;
			return;
		}
		else if(e.getKeyChar() == '2')
		{
			mode = InputMode.END_NODE;
			return;
		}
		else if(e.getKeyChar() == '3')
		{
			mode = InputMode.OBSTACLE;
			return;
		}
		else if(e.getKeyChar() == '4')
		{
			mode = InputMode.ERASE;
			return;
		}
		else if(e.getKeyChar() == '0')
		{
			Main.world.gameObjects.clear();
			Main.world.pathFinder.clear();
			return;
		}
		else if(e.getKeyChar() == '\n')
		{
			if(!Main.world.pathFinder.isSearching())
			{
				Main.world.pathFinder.startSearching();
			}
			else
			{
				Main.world.pathFinder.stopSearching();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		Vec2i vec;
		
		switch(this.mode)
		{
		case START_NODE:vec = Main.world.getTileClicked(e.getX(), e.getY());
						Main.world.pathFinder.setStartNode(new PathNode(vec.getX(), vec.getY(), Color.GREEN));
						Main.world.pathFinder.clear();
						break;
		case END_NODE:vec = Main.world.getTileClicked(e.getX(), e.getY());
					  Main.world.pathFinder.setEndNode(new PathNode(vec.getX(), vec.getY(), Color.BLUE));
					  Main.world.pathFinder.clear();
					  break;
		case OBSTACLE:vec = Main.world.getTileClicked(e.getX(), e.getY());
		  			  Main.world.addObstacle(vec);
		  			  Main.world.pathFinder.clear();
		  			  break;
		case ERASE:vec = Main.world.getTileClicked(e.getX(), e.getY());
		  		   Main.world.removeGameObjectAt(vec);
		  		   Main.world.pathFinder.clear();
		  		   break;
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) 
	{
		this.prevMousePos = null;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) 
	{
		Vec2i tileOver = Main.world.getTileClicked(e.getX(), e.getY());
		
		if(tileOver != null && (!tileOver.equals(this.prevMousePos) || this.prevMousePos == null))
		{
			if(this.mode == InputMode.OBSTACLE)
			{	
				Main.world.addObstacle(tileOver);
				this.prevMousePos = tileOver;
				Main.world.pathFinder.clear();
			}
			else if(this.mode == InputMode.ERASE)
			{	
				Main.world.removeGameObjectAt(tileOver);
				this.prevMousePos = tileOver;
				Main.world.pathFinder.clear();
			}
		}
	}
	
	
	@Override
	public void mousePressed(MouseEvent e) {}
	
	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	@Override
	public void mouseMoved(MouseEvent e) {}
	
	private enum InputMode
	{
		NONE,
		START_NODE,
		END_NODE,
		OBSTACLE,
		ERASE;
	}
}
