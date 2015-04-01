package gtefpBlocks;

import java.io.IOException;
import java.io.Serializable;

import gtefpMain.WorkspacePanel;

public class kPackage extends kImport implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public kPackage() // necessary for deserialization
	{
		super();
	}
	
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
	
	public void readObject(java.io.ObjectInputStream in) throws ClassNotFoundException, IOException
	{
		super.readObject(in);
	}
	
	public void writeObject(java.io.ObjectOutputStream out) throws IOException
	{
		super.writeObject(out);
	}
}
