package gtefpMain;

public class WorkspacePanel extends javax.swing.JPanel{
	private java.awt.Polygon _templateTray;
	private App _app;
	public int _trayWidth;
	public void init(){
		this.setBackground(new java.awt.Color(200,200,201));
		int [] xs ={0};
		int [] ys ={0};
		_trayWidth = 250;
		_templateTray = new java.awt.Polygon(xs ,ys, xs.length);
		this.repaint();
	}
	public WorkspacePanel(){
		super();
		this.init();
		_app = new App(this);
		//this.addMouseListener(_trailer);
		
	}
	public WorkspacePanel(App app){
		super();
		_app = app;
		this.init();
		}
	public void paintComponent (java.awt.Graphics aBrush){
		super.paintComponent(aBrush);
		java.awt.Graphics2D betterBrush = (java.awt.Graphics2D) aBrush;
		betterBrush.setColor(new java.awt.Color(233,233,240));
		betterBrush.fillPolygon(_templateTray);
		_app.paint(aBrush);
	}
	public void setup (){
		int [] xs ={0,_trayWidth,_trayWidth,0};
		int [] ys ={0,0,3000,3000};
		_templateTray = new java.awt.Polygon(xs ,ys, xs.length);
	}
	public int nClasses(){
		int n = _app.getClasses().size();
		return(n);
	}
}
