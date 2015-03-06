package gtefpMain;
import util.App;
import util.NewClassButton;
import gpBase.kVec;

public class ClassesPanel extends javax.swing.JPanel{
	private int _nrows;
	private App _app;
	private NewClassButton _newClassButton;
	private kVec<Tab> _tabs;
	public ClassesPanel(){
		super();
		_nrows = 1;
		//this.addMouseListener(_trailer);
		this.setBackground(new java.awt.Color(90,90,90));
		_newClassButton = new NewClassButton(this);
		_tabs = new kVec<Tab>();
		this.repaint();
	}
	public ClassesPanel(App app){
		super();
		_nrows = 1;
		//this.addMouseListener(_trailer);
		_app = app;
		this.setBackground(new java.awt.Color(90,90,90));
		_newClassButton = new NewClassButton(this);
		_tabs = new kVec<Tab>();
		this.repaint();	
	}
	public void paintComponent (java.awt.Graphics aBrush){
		super.paintComponent(aBrush);
		_newClassButton.paint(aBrush);
		if()		
	}
	public void newClass() {
		_app.NewJavaFile();
	}
	public void setApp(App a)
	{
		_app = a;
	}
}
