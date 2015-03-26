package gtefpBlocks;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import gtefpMain.WorkspacePanel;

public class kFor extends kWhile
{
	public kFor(WorkspacePanel wp)
	{
		super(wp);
		_pWhile.setText( "for(");
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
}