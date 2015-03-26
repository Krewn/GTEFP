package gtefpMain;

import java.io.File;

public class SaveLoadFrame
{
	private GpFrame _current;
	private javax.swing.JFileChooser fc;
	private File _file;
	
	public SaveLoadFrame()
	{
		_current = new GpFrame("GTEFP");
		_current.makeSaveAndLoadButton(this);
	}
	
	public static void main(String[] args)
	{
		SaveLoadFrame slf = new SaveLoadFrame();
	}
	
	public void saveToFile() {
    	fc=new javax.swing.JFileChooser();
    	int returnVal = fc.showSaveDialog(null);
		if(returnVal== javax.swing.JFileChooser.APPROVE_OPTION){
    		_file = fc.getSelectedFile();
    	}else return;
    	try{
    		java.io.ObjectOutputStream os = new java.io.ObjectOutputStream(new java.io.FileOutputStream(_file));
    		os.writeObject(_current);
    		os.close();
    	}
    	catch(java.io.IOException e){
    		System.out.println("IO Exception reading file");
    	}
	}
	public void loadFromFile(){
    	fc=new javax.swing.JFileChooser();
    	int returnVal = fc.showOpenDialog(null);
    	if(returnVal== javax.swing.JFileChooser.APPROVE_OPTION){
    		_file = fc.getSelectedFile();
    	}else return;
    	try{
    		java.io.ObjectInputStream is = new java.io.ObjectInputStream(new java.io.FileInputStream(_file));
    		_current = (GpFrame)is.readObject();
    		is.close();
    		System.out.println("File Loaded");
    	}
    	catch(java.io.IOException e){
    		System.out.println(e.getStackTrace());
    		e.printStackTrace();
    	}
    	catch(ClassNotFoundException e){
    		System.out.println("Class Not Found Exception");
    	}
    	_current.repaint();
    	}
	 // useful for saving and loading classes from a file.
}