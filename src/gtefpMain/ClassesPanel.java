package gtefpMain;
import gpBase.kVec;

public class ClassesPanel extends javax.swing.JPanel{
	private int _nrows;
	private App _app;
	public ClassesPanel(){
		super();
		_nrows = 1;
		//this.addMouseListener(_trailer);
		this.setBackground(new java.awt.Color(90,90,90));
		this.repaint();
	}
	public ClassesPanel(App app){
		super();
		_nrows = 1;
		//this.addMouseListener(_trailer);
		_app = app;
		this.setBackground(new java.awt.Color(90,90,90));
		this.repaint();	
	}
	public void paintComponent (java.awt.Graphics aBrush){
		super.paintComponent(aBrush);
		java.awt.Graphics2D betterBrush = (java.awt.Graphics2D) aBrush;
		betterBrush.setColor(new java.awt.Color(0,0,0));
		
	}
}
