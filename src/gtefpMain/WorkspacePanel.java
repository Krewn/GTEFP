package gtefpMain;

import gtefpBlocks.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Polygon;
import javax.swing.Timer;
import util.*;

public class WorkspacePanel extends javax.swing.JPanel implements java.awt.event.ActionListener
{
	public  App                _app;
	private int                _bc;
	public  Color              _blink;
	private boolean            _bUp; // instructs whether the blinking is to go up or down
	public  Socket             _buttonSocket;
	public  int                _codeYpos, _importsYPos, _scale, _trayWidth;
	public  ClassesPanel       _cp;
	private GpFrame            _frame;
	private long               _ogT;
	public  kVec<kVec<Socket>> _sockets;
	public  CodePiece          _temp;
	private Polygon            _templateTray;
	private Timer              _timer;
	
	public WorkspacePanel(GpFrame frame)
	{
		super();
		this.init();
		_cp = new ClassesPanel(this, frame);
		_app = new App(this);
		_frame = frame;
		//_app.newJavaFile();
		//_cp.newTab();
	}
	
	public WorkspacePanel(App app, GpFrame frame)
	{
		super();
		_cp = new ClassesPanel(this, frame);
		_app = app;
		this.init();
		_frame = frame;
		//_cp.newTab();
	}
	public JavaFile curJF(){
		try{ 
			return(_app.curJF());
		}catch(java.lang.NullPointerException e){
			return(null);
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(arg0.getSource()==_timer)
			repaint();
	}
	
	
	
	public void addSocket(Socket s)
	{
		try
		{
			_sockets.elementAt(_app.classIndex()).add(s);
		}
		catch(java.lang.NullPointerException e)
		{
			_sockets.elementAt(0).add(s);
		}
	}
	
	public Color blink()
	{
		long t   = System.currentTimeMillis();
		     _bc = (int)((t - _ogT) % 510) / 2;
		     _bc = (((t - _ogT) % 510) > 255) ? 255 - _bc:_bc;	
		
		return(new Color(_bc, _bc, _bc));
	}
	
	public void clearTemp()
	{
		_temp=null;
	}
	
	public App getApp()
	{
		return _app;
	}
	
	public ClassesPanel getClassesPanel()
	{
		return _cp;
	}
	
	public int getScale()
	{
		int r = _scale;
		return(r);
	}
	
	public void init()
	{
		this.setFocusable(true);
		
		_bUp = true;
		_bc  = 5;
		_ogT = System.currentTimeMillis();
		
		this.setBackground(new java.awt.Color(200,200,201));
		
		int [] xs = {0};
		int [] ys = {0};
		
		_sockets = new kVec<kVec<Socket>>();
		_sockets.que(new kVec<Socket>());
		
		_scale        = 5;
		_trayWidth    = 250;
		_templateTray = new java.awt.Polygon(xs, ys, xs.length);
		_buttonSocket = new Socket(this);
		_buttonSocket.scale(this.getScale());
		_buttonSocket.place(0, 0);
		_temp = null;
		
		kImport  _IMPORT  = new kImport(this); // Prepare template tray
		kClass   _CLASS   = new kClass(this);
		kIf      _IF      = new kIf(this);
		kVar     _VAR     = new kVar(this);
		kPackage _PACKAGE = new kPackage(this);

		_buttonSocket.insert(_PACKAGE);
		//_PACKAGE._after.insert(_IMPORT);
		_PACKAGE.getAfter().insert(_IMPORT);
		//_IMPORT._after.insert(_IF);
		_IMPORT.getAfter().insert(_IF);
		//_IF._after.insert(_VAR);
		_IF.getAfter().insert(_VAR);
		//_VAR._after.insert(_CLASS);
		_VAR.getAfter().insert(_CLASS);
		
		_PACKAGE.makeButton();
		_IMPORT.makeButton();
		_IF.makeButton();
		_VAR.makeButton();
		_CLASS.makeButton();
		
		_timer = new javax.swing.Timer(100, this);
		_timer.start();
		_sockets = new kVec<kVec<Socket>>();
		
		this.repaint();
	}
	
	public int nClasses()
	{
		int n = _app.getClasses().size();
		return(n);
	}
	
	public void newJFile(JavaFile j)
	{
		_temp = null;
		_app.addFile(j);
		_sockets.que(new kVec<Socket>());
		this.repaint();
	}
	
	public void paintComponent (java.awt.Graphics aBrush)
	{
		super.paintComponent(aBrush);
		this.setBackground(new java.awt.Color(200,200,201));
		java.awt.Graphics2D betterBrush = (java.awt.Graphics2D) aBrush;
		betterBrush.setColor(new java.awt.Color(233,233,240));
		betterBrush.fillPolygon(_templateTray);
		_buttonSocket.paint(aBrush);
		_app.paint(aBrush);
		if(_temp!=null){_temp.paint(aBrush);}
		//_cp.paintComponent(aBrush);
	}
	
	public void removeSocket(Socket s)
	{
		sockets().remove(s);
	}
	
	public void setApp(App a)
	{
		_app = a;
	}
	
	public void setCp(ClassesPanel cp)
	{
		_cp = cp;
	}
	
	public void setCurrentWsClass(int i)
	{
		_app.setCurrentWsClass(i);
		setTemp(null);
	}
	
	public void setTemp(CodePiece cp)
	{
		_temp=cp;
	}
	
	public void setup()
	{
		int[] xs = {0, _trayWidth, _trayWidth, 0};
		int[] ys = {0, 0, 3000, 3000};
		
		_templateTray = new java.awt.Polygon(xs ,ys, xs.length);
	}
	
	public kVec<Socket> sockets()
	{
		try
		{
			return(_sockets.elementAt(_app.classIndex()));
		}
		catch(java.lang.NullPointerException e)
		{
			return(_sockets.elementAt(0));
		}
	}
	
	public int trayWidth()
	{
		int r = _trayWidth;
		return(r);
	}
}
