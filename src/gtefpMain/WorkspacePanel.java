package gtefpMain;

public class WorkspacePanel extends javax.swing.JPanel{
	private java.awt.Polygon _templateTray;
	public App _app;
	public Socket _buttonSocket;
	public CodePiece _temp;
	public int _trayWidth;
	public int _importsYPos;//imports and code are default x positioned at tray width.
	public int _codeYpos;
	public int _scale;
	public kVec<Socket> _sockets;
	public int getScale(){
		int r = _scale;
		return(r);
	}
	public void addSocket(Socket s){
		_sockets.add(s);
	}
	public void removeSocket(Socket s){
		_sockets.remove(s);
	}
	public void init(){
		this.setBackground(new java.awt.Color(200,200,201));
		int [] xs ={0};
		int [] ys ={0};
		_sockets=new kVec<Socket>();
		_scale = 5 ;
		_trayWidth = 250;
		_templateTray = new java.awt.Polygon(xs ,ys, xs.length);
		_buttonSocket = new Socket(this);
		_buttonSocket.scale(this.getScale());
		_buttonSocket.Place(0, 0);
		_temp = null;
		kImport _IMPORT = new kImport(this);
		_IMPORT.makeButton();
		_buttonSocket.insert(_IMPORT);
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
	public int trayWidth(){
		int r = _trayWidth;
		return(r);
	}
	public void paintComponent (java.awt.Graphics aBrush){
		super.paintComponent(aBrush);
		this.setBackground(new java.awt.Color(200,200,201));
		java.awt.Graphics2D betterBrush = (java.awt.Graphics2D) aBrush;
		betterBrush.setColor(new java.awt.Color(233,233,240));
		betterBrush.fillPolygon(_templateTray);
		_buttonSocket.paint(aBrush);
		if(_temp!=null){_temp.paint(aBrush);}
		_app.paint(aBrush);
	}
	public void setTemp(CodePiece cp){
		_temp=cp;
	}
	public void clearTemp(){
		_temp=null;
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
