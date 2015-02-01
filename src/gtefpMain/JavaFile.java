package gtefpMain;

public class JavaFile {
	private Socket _imports;
	private Socket _Class;
	public JavaFile(WorkspacePanel wp){
		_imports = new Socket(wp);
		_imports.Place(wp._trayWidth, 0);
		_imports.scale(5);
		_imports.Draw_p();
		_Class = new Socket(wp);
		_Class.Place(wp._trayWidth, 40);
		_Class.scale(5);
		_Class.Draw_p();
	}
	public void paint(java.awt.Graphics aBrush){
		java.awt.Color oldColor = aBrush.getColor();
		java.awt.Graphics2D betterBrush = (java.awt.Graphics2D) aBrush;
		_imports.paint(betterBrush);
		_Class.paint(betterBrush);
	}
}
