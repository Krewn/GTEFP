package gtefpMain;

public class JavaFile {
	public Socket _imports;
	private Socket _Class;
	private WorkspacePanel _wp;
	private int _scale;
	public JavaFile(WorkspacePanel wp){
		_wp = wp;
		_scale=wp.getScale();
		_imports = new Socket(wp);
		_imports.Place(wp._trayWidth, 0);
		_imports.scale(_wp.getScale());
		_imports.Draw_p();
		_Class = new Socket(wp);
		_Class.Place(wp._trayWidth,_imports.ysize()+35);
		_Class.scale(_wp.getScale());
		_Class.Draw_p();
	}
	public void paint(java.awt.Graphics aBrush){
		_Class.Place(_wp._trayWidth,_imports.ysize()+35);
		java.awt.Color oldColor = aBrush.getColor();
		java.awt.Graphics2D betterBrush = (java.awt.Graphics2D) aBrush;
		_imports.paint(aBrush);
		_Class.paint(aBrush);
	}
}
