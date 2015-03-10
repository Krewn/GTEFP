package gtefpBlocks;

import gtefpMain.WorkspacePanel;

public class kPackage extends kImport
{
	public kPackage(WorkspacePanel wp)
	{
		super(wp);
		_import.setText("package");
	}
	
	@Override
	public void mousePressed(java.awt.event.MouseEvent e){
		_lastMouseLoc = e.getPoint();
		if(_p.contains(_lastMouseLoc)){
			_selected=true;
			if(_isButton){
				kPackage _temp = new kPackage(_wp);
				_temp.setCp(_temp);
				_temp.place(_xPos,_yPos);
				_temp.setRel(0,0);
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
