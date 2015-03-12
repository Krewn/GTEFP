package util;

import gtefpBlocks.Socket;
import gtefpMain.ClassesPanel;
import gtefpMain.WorkspacePanel;

public class JavaFile
{
	public  Socket         _imports;
	private Socket         _class;
	private int            _scale;
	private WorkspacePanel _wp;
	
	public JavaFile(WorkspacePanel wp)
	{
		_wp    = wp;
		_scale = wp.getScale();
		_wp.newJFile(this);
		
		_imports = new Socket(wp);
		_imports.place(wp._trayWidth, 0);
		_imports.scale(_wp.getScale());
		_imports.draw_p();
		
		_class = new Socket(wp);
		_class.setCp(_imports);
		_class.setRel(0,_imports.ySize()+10);
		_class.scale(_wp.getScale());
		_class.draw_p();
		
		_wp.getClassesPanel().newTab();
	}
	
	public void paint(java.awt.Graphics aBrush)
	{
		_class.setRel(0,_imports.ySize()+10);
		java.awt.Color oldColor = aBrush.getColor();
		java.awt.Graphics2D betterBrush = (java.awt.Graphics2D) aBrush;
		_imports.paint(aBrush);
		_class.paint(aBrush);
	}
	
	public String writeCode()
	{
		return _imports.writeCode() + _class.writeCode();
	}
}
