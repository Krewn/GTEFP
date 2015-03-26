package util;

import gtefpMain.ClassesPanel;
import gtefpMain.SaveLoadFrame;

public class LoadButton extends SaveButton
{
	
	public LoadButton(ClassesPanel cp, SaveLoadFrame slf)
	{
		super(cp, slf);
		_xs = new int[]{0, 1, 1, 0};
		_ys = new int[]{0, 0, 1, 1};
		_scale = 25;
		_xPos = _cp.getWidth() - (_scale * 12);
		draw_p();
		_cp.repaint();
	}
	
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e){
		java.awt.Point p = e.getPoint();
		if(_plusButton.contains(p))
			_slf.loadFromFile();
	}
}
