/************************************************************************************************************************
 * SaveLoadFrame maintains a reference to the current GpFrame (i.e. the current Java project that the user is editing). *
 * SaveLoadFrame also handles the saving and loading of Java projects.                                                  *
 ************************************************************************************************************************/

package gtefpMain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SaveLoadFrame implements Serializable
{
	private static final long serialVersionUID = 1L;
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
	
	// Saves current project to file specified by the user.
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
    		e.printStackTrace();
    	}
	}
	
	// Loads file specified by user into memory.
	public void loadFromFile(){
		_current.setOkToPaint(false);
    	fc=new javax.swing.JFileChooser();
    	int returnVal = fc.showOpenDialog(null);
    	if(returnVal== javax.swing.JFileChooser.APPROVE_OPTION){
    		_file = fc.getSelectedFile();
    	}else return;
    	try{
    		java.io.ObjectInputStream is = new java.io.ObjectInputStream(new java.io.FileInputStream(_file));
    		_current.setVisible(false);
    		GpFrame temp = (GpFrame)is.readObject();
    		temp.setVisible(true);
    		_current = temp;
    		is.close();
    	}
    	catch(java.io.IOException e){
    		System.out.println(e.getStackTrace());
    		e.printStackTrace();
    	}
    	catch(ClassNotFoundException e){
    		System.out.println("Class Not Found Exception");
    	}
    	_current.revalidate();
    	_current.repaint();
    	}
	
	private void readObject(java.io.ObjectInputStream in) throws ClassNotFoundException, IOException
	{
		_current = (GpFrame)in.readObject();
	}
	
	private void writeObject(java.io.ObjectOutputStream out) throws IOException
	{
		out.writeObject(_current);
	}
}