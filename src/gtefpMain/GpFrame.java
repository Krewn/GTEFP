package gtefpMain;
import java.awt.*;
import javax.swing.*;
public class GpFrame extends JFrame {
	private ClassesPanel _Cpanel;
	private WorkspacePanel _Wpanel;
	public GpFrame(String Title){
		super(Title);
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
		this.setSize(900,750);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE); 
		_Cpanel = new ClassesPanel();
		_Cpanel.setPreferredSize(new Dimension(780,30));// hardCoded sizing
		_Cpanel.setMaximumSize(new Dimension(3000, 25));  // hardCoded sizing
		_Cpanel.setMinimumSize(new Dimension(100, 20));  // hardCoded sizing
		_Wpanel = new WorkspacePanel();
		_Wpanel.setPreferredSize(new Dimension(780, 800));// hardCoded sizing
		_Wpanel.setMaximumSize(new Dimension(3000, 3000));  // hardCoded sizing
		_Wpanel.setMinimumSize(new Dimension(100, 42));  // hardCoded sizing
		this.setPreferredSize(new Dimension(950, 800));
		this.add(_Cpanel);
		this.add(_Wpanel);
		_Wpanel.setup();
		this.repaint();
		this.setVisible(true);
	}
	public WorkspacePanel getWp(){
		return(_Wpanel);
	}
	public static void main (String [ ] args) {
		GpFrame test = new GpFrame ("Gtefp");
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
}