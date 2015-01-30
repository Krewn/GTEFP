//import javax.swing.*; 
//import javax.swing.event.*; 
//import java.awt.Color;
//import java.awt.event.*;
public class MyPanel extends javax.swing.JPanel{
	private truck _truck;
	private trailer _trailer;
	private truck _truck2;
	private trailer _trailer2;
	private colorPicker _cp;
	private ColorButton _cb;
	private javax.swing.ButtonGroup _Group;
	public MyPanel(){
		super();
		_cp = new colorPicker(this);
		_trailer = new trailer(_cp,this);
		_truck = new truck(_cp,_trailer,this,100,100);
		this.addMouseListener(_trailer);
		this.addMouseListener(_truck);
		_trailer2 = new trailer(_cp,this);
		_truck2 = new truck(_cp,_trailer2,this,300,100);
		this.addMouseListener(_trailer2);
		this.addMouseListener(_truck2);
		
		this.setBackground(new java.awt.Color(42,42,42));
		
		 javax.swing.JPanel buttonPanel;
		 buttonPanel = new javax.swing.JPanel();
		 _cb = new ColorButton(new java.awt.Color(0,0,0),_cp,_Group,true);
		 buttonPanel.add(_cb);
		 this.add(buttonPanel);
		//MyMouseListener myMouseListener = new MyMouseListener(this);
		this.repaint();
	}
	public void paintComponent (java.awt.Graphics aBrush){
		super.paintComponent(aBrush);
		java.awt.Graphics2D betterBrush = (java.awt.Graphics2D) aBrush;
		betterBrush.setColor(new java.awt.Color(0,0,0));
		_truck.paint(betterBrush);
		_trailer.paint(betterBrush);
		_truck2.paint(betterBrush);
		_trailer2.paint(betterBrush);
		_cp.paint(aBrush);
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
