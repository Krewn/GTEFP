package util;
import gpBase.kVec;
import gtefpBlocks.Socket;
import gtefpMain.WorkspacePanel;

public class App {
	public kVec<JavaFile> _classes;
	private int _currentWsClass;
	private WorkspacePanel _wp;
	public App(WorkspacePanel wp){
		_wp = wp;
		_classes=new kVec<JavaFile>();
		// new JavaFile(wp);
		//_classes.que(new JavaFile(wp)); # JavaFile Constructor ques the file into _classes.
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
	public kVec<String> writeCode()
	{
		kVec<String> r = new kVec<String>();
		for (JavaFile k2 : _classes)
			r.que(k2.writeCode());
		return r;
	}
	public void NewJavaFile(){
		 new JavaFile(_wp);
	}
	public void AddFile(JavaFile j) {
		_classes.que(j);
		_currentWsClass = _classes.indexOf(j);
		_wp.repaint();
	}
}
/*
public void saveToFile() {
	fc=new javax.swing.JFileChooser();
	int returnVal = fc.showSaveDialog(this);
	if(returnVal== javax.swing.JFileChooser.APPROVE_OPTION){
		file = fc.getSelectedFile();
	}else return;
	try{java.io.ObjectOutputStream os = new java.io.ObjectOutputStream(new java.io.FileOutputStream(file));
		os.writeObject(_circles);
		os.close();
	}
	catch(java.io.IOException e){
		System.out.println("IO Exception reading file");
	}
}
public void loadFromFile(){
	fc=new javax.swing.JFileChooser();
	int returnVal = fc.showOpenDialog(this);
	if(returnVal== javax.swing.JFileChooser.APPROVE_OPTION){
		file = fc.getSelectedFile();
	}else return;
	try{java.io.ObjectInputStream is = new java.io.ObjectInputStream(new java.io.FileInputStream(file));
		_circles = (java.util.ArrayList<SmartEllipse>)is.readObject();
		is.close();
	}
	catch(java.io.IOException e){
		System.out.println("IO Exception reading file");
	}
	catch(ClassNotFoundException e){
		System.out.println("Class Not Found Exception");
	}
	repaint();
	}
*/ // useful for saving and loading classes from a file.