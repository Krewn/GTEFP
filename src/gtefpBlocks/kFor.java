package gtefpBlocks;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.Serializable;

import gtefpMain.WorkspacePanel;

public class kFor extends kWhile implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public kFor() // necessary for deserialization
	{
		super();
	}
	
	public kFor(WorkspacePanel wp)
	{
		super(wp);
		_pWhile.setText("for(");
		_c = new java.awt.Color(40, 170, 31);
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		_lastMouseLoc = e.getPoint();
		
		if (_p.contains(_lastMouseLoc))
		{
			_selected = true;
			
			if (_isButton)
			{
				kFor _temp = new kFor(_wp);
				_temp.setCp(_temp);
				_temp.place(_xPos, _yPos);
				_temp.setRel(0, 0);
				_temp.select();
				_temp.mousePressed(e);
				_wp.setTemp(_temp);
				_wp.repaint();
				_temp.draw_p();
				_wp.repaint();
			}
		}
	}
	
	private void readObject(java.io.ObjectInputStream in) throws ClassNotFoundException, IOException
	{
		in.defaultReadObject();
	}
	
	private void writeObject(java.io.ObjectOutputStream out) throws IOException
	{
		out.defaultWriteObject();
	}
}