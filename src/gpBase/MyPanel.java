package gpBase;
//import javax.swing.*; 
//import javax.swing.event.*; 
//import java.awt.Color;
//import java.awt.event.*;
public class MyPanel extends javax.swing.JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
}