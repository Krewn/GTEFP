package gtefpMain;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import gtefpBlocks.*;
import util.*;

public class WorkspacePanel extends javax.swing.JPanel implements java.awt.event.ActionListener{
	private java.awt.Polygon _templateTray;
	public App _app;
	public Socket _buttonSocket;
	public CodePiece _temp;
	public int _trayWidth;
	public int _importsYPos;//imports and code are default x positioned at tray width.
	public int _codeYpos;
	public int _scale;
	public kVec<kVec<Socket>> _sockets;
	public java.awt.Color _blink;
	private int _bc;
	private long _ogT;
	private boolean _bUp; // instructs weather the blinking is to go up or down;
	private javax.swing.Timer timer;
	
	public int getScale(){
		int r = _scale;
		return(r);
	}
	public kVec<Socket> sockets(){
		try{
			return(_sockets.elementAt(_app.classIndex()));
		}catch(java.lang.NullPointerException e){
			return(_sockets.elementAt(0));
		}
	}
	public java.awt.Color blink(){//1000 miliseconds / second.
		long t = System.currentTimeMillis();
		_bc = (int) ((t - _ogT)%510)/2;
		_bc = ( ((t - _ogT)%510) > 255) ? 255-_bc : _bc;	
		return(new java.awt.Color(_bc,_bc,_bc));
	}
	public void addSocket(Socket s){
		try{
			_sockets.elementAt(_app.classIndex()).add(s);
		}catch(java.lang.NullPointerException e){
			_sockets.elementAt(0).add(s);
		}
	}
	public void newJFile(JavaFile j){
		_app.AddFile(j);
		_sockets.que(new kVec<Socket> ());
	}
	public void removeSocket(Socket s){
		sockets().remove(s);
	}
	public void init(){
		this.setFocusable(true);
		//this.setFocusTraversalKeysEnabled(false);
		_bUp = true;
		_bc = 5;
		_ogT = System.currentTimeMillis();
		this.setBackground(new java.awt.Color(200,200,201));
		int [] xs ={0};
		int [] ys ={0};
		_sockets=new kVec<kVec<Socket>>();
		_sockets.que(new kVec<Socket>());
		_scale = 5 ;
		_trayWidth = 250;
		_templateTray = new java.awt.Polygon(xs ,ys, xs.length);
		_buttonSocket = new Socket(this);
		_buttonSocket.scale(this.getScale());
		_buttonSocket.place(0, 0);
		_temp = null;
		kImport _IMPORT = new kImport(this);
		kClass _CLASS = new kClass(this);
		kIf _IF = new kIf(this);
		kVar _VAR = new kVar(this);
		_buttonSocket.insert(_IMPORT);
		_IMPORT._after.insert(_IF);
		_IF._after.insert(_VAR);
		_VAR._after.insert(_CLASS);
		_IMPORT.makeButton();
		_IF.makeButton();
		_VAR.makeButton();
		_CLASS.makeButton();
		timer= new javax.swing.Timer(100, this);
		timer.start();
		_sockets = new kVec<kVec<Socket>>();
		this.repaint();
	}
	public WorkspacePanel(){
		super();
		this.init();
		_app = new App(this);
		_app.NewJavaFile();
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
		_app.paint(aBrush);
		if(_temp!=null){_temp.paint(aBrush);}
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
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==timer){
			repaint();
		}
	}
}
