package gtefpMain;
import gpBase.kVec;

public class App {
	public kVec<JavaFile> _classes;
	private int _currentWsClass;
	private WorkspacePanel _wp;
	public App(WorkspacePanel wp){
		_wp = wp;
		_classes=new kVec<JavaFile>();
		_classes.que(new JavaFile(wp));
		_currentWsClass=0;
	}
	public void paint(java.awt.Graphics aBrush){
		
		java.awt.Color oldColor = aBrush.getColor();
		java.awt.Graphics2D betterBrush = (java.awt.Graphics2D) aBrush;
		_classes.elementAt(_currentWsClass).paint(aBrush);
	}
	public int classIndex(){
		int r = _currentWsClass;
		return(r);
	}
	public kVec<JavaFile> getClasses(){
		kVec<JavaFile>c = _classes;
		return(c);
	}
	public boolean saveToFile(){
		boolean b =false;
		return(b);
	}
}
