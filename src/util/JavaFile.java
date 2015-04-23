/********************************************************************
 * JavaFile represents a Java file in a form compatible with GTEFP. *
 ********************************************************************/

package util;

import java.io.IOException;
import java.io.Serializable;

import gtefpBlocks.Socket;
import gtefpBlocks.kClass;
import gtefpMain.ClassesPanel;
import gtefpMain.WorkspacePanel;

public class JavaFile implements Serializable
{
	private static final long serialVersionUID = 1L;
	public  Socket         _imports;
	private Socket         _class;
	private kClass         _classBlock;
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
	}
	
	public void setup(){
		_class = new Socket(_wp);
		_class.setCp(_imports);
		_class.setRel(0,_imports.ySize()+10);
		_class.scale(_wp.getScale());
		_class.draw_p();
		_classBlock = new kClass(_wp);
		_classBlock.setJavaFile(this);
		_class.insert(_classBlock);
		
		_wp.getClassesPanel().newTab(this);
	}
	
	public void paint(java.awt.Graphics aBrush)
	{
		_class.setRel(0,_imports.ySize()+10);
		java.awt.Color oldColor = aBrush.getColor();
		java.awt.Graphics2D betterBrush = (java.awt.Graphics2D) aBrush;
		_imports.paint(aBrush);
		_class.paint(aBrush);
	}
	
	public String getClassName()
	{
		return _classBlock.getName();
	}
	
	public String writeCode()
	{
		return _imports.writeCode() + _class.writeCode();
	}
	
	private void readObject(java.io.ObjectInputStream in) throws ClassNotFoundException, IOException
	{
		_imports=(Socket)in.readObject();
		_class=(Socket)in.readObject();
		_classBlock=(kClass)in.readObject();
		_scale=(int)in.readObject();
		_wp=(WorkspacePanel)in.readObject();
	}
	private void writeObject(java.io.ObjectOutputStream out) throws IOException
	{
		out.writeObject(_imports);
		out.writeObject(_class);
		out.writeObject(_classBlock);
		out.writeObject(_scale);
		out.writeObject(_wp);

	}
}
