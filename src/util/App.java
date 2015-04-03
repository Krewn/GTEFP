package util;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;

import gpBase.kVec;
import gtefpMain.WorkspacePanel;

public class App implements Serializable
{
	private static final long serialVersionUID = 1L;
	public  kVec<JavaFile> _classes;
	private int            _currentWsClass;
	private WorkspacePanel _wp;
	
	public App(WorkspacePanel wp)
	{
		_wp             = wp;
		_classes        = new kVec<JavaFile>();
		_currentWsClass = 0;
	}
	
	public void addFile(JavaFile j)
	{
		_classes.que(j);
		_currentWsClass = _classes.indexOf(j);
		_wp.repaint();
	}
	
	public int classIndex()
	{
		int r = _currentWsClass;
		return(r);
	}
	
	public kVec<JavaFile> getClasses()
	{
		kVec<JavaFile> c = _classes;
		return (c);
	}
	
	public int getIndexOfJavaFile(JavaFile jf)
	{
		return _classes.indexOf(jf);
	}
	
	public JavaFile curJF(){
		return( _classes.get(_currentWsClass) );
	}
	
	public void newJavaFile()
	{
		JavaFile temp = new JavaFile(_wp);
		temp.setup();
	}
	
	public void paint(java.awt.Graphics aBrush)
	{
		try
		{
			_classes.elementAt(_currentWsClass).paint(aBrush);
		}
		catch(java.lang.ArrayIndexOutOfBoundsException e)
		{
			this.newJavaFile();
		}
	}
	
	public boolean saveToFile()
	{
		boolean b = false;
		return(b);
	}
	
	public void setCurrentWsClass(int i)
	{
		_currentWsClass = i;
	}
	

	//public  kVec<JavaFile> _classes;
	public void writeToFile(String dir){
		dir+="_";
		File f = new File(dir);
		if (f.isDirectory()) {
			FileUtils.deleteDirectory(dir);
			// Apache Commons Io
		}
		String Content = "";
		String Name ="";
		for (JavaFile k2 : _classes){
			Content = k2.writeCode();
			Name = k2.getClassName();
			
			//r.que(k2.writeCode());
		}
	}
	
	public kVec<String> writeCode()
	{
		kVec<String> r = new kVec<String>();
		
		for (JavaFile k2 : _classes)
			r.que(k2.writeCode());
		
		return r;
	}
	
	private void readObject(java.io.ObjectInputStream in) throws ClassNotFoundException, IOException
	{
		java.util.ArrayList<Object> _temp = (java.util.ArrayList<Object>)in.readObject();
		_classes = new kVec<JavaFile>();
		for(Object k:_temp){
			_classes.que((JavaFile) k);
		}
		_currentWsClass=(int)in.readObject();
		_wp=(WorkspacePanel)in.readObject();
	}
	
	private void writeObject(java.io.ObjectOutputStream out) throws IOException
	{
		java.util.ArrayList<Object> temp = new java.util.ArrayList<Object>();
		for(JavaFile k : _classes){
			temp.add((Object)k);
		}
		out.writeObject(temp);
		out.writeObject(_currentWsClass);
		out.writeObject(_wp);
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