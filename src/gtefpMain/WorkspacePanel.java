/**************************************************************************************************************
 * WorkspacePanel represents the space in which the user works on his or her code. Code blocks may be dragged *
 * to and from locations in the WorkspacePanel.                                                               *
 **************************************************************************************************************/

package gtefpMain;

import gpBase.kVec;
import gtefpBlocks.*;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Polygon;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.Timer;

import util.*;

public class WorkspacePanel extends javax.swing.JPanel implements java.awt.event.ActionListener, Serializable
{
	private static final long serialVersionUID = 1L;
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
	private boolean            _OkToPaint;
	
	public WorkspacePanel(GpFrame frame)
	{
		super();
		this.init();
		_cp = new ClassesPanel(this, frame);
		_app = new App(this);
		_frame = frame;
	}
	
	public WorkspacePanel(App app, GpFrame frame)
	{
		super();
		_cp = new ClassesPanel(this, frame);
		_app = app;
		this.init();
		_frame = frame;
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
		if(arg0.getSource()==_timer && _OkToPaint)
			repaint();
	}
	
	public void setOkToPaint(boolean b){
		_OkToPaint = b;
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
	
	// Initializes WorkspacePanel when a new GpFrame is loaded.
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
		kElseIf  _ELSEIF  = new kElseIf(this);
		kElse    _ELSE    = new kElse(this);
		kVar     _VAR     = new kVar(this);
		kPackage _PACKAGE = new kPackage(this);
		kWhile   _WHILE   = new kWhile(this);
		kFor     _FOR     = new kFor(this);
		kSwitch  _SWITCH  = new kSwitch(this);
		kCase    _CASE    = new kCase(this);

		_buttonSocket.insert(_PACKAGE);
		_PACKAGE.getAfter().insert(_IMPORT);
		_IMPORT.getAfter().insert(_IF);
		_IF.getAfter().insert(_ELSEIF);
		_ELSEIF.getAfter().insert(_ELSE);
		_ELSE.getAfter().insert(_VAR);
		_VAR.getAfter().insert(_WHILE);
		_WHILE.getAfter().insert(_FOR);
		_FOR.getAfter().insert(_SWITCH);
		_SWITCH.getAfter().insert(_CASE);
		_CASE.getAfter().insert(_CLASS);
		
		_PACKAGE.makeButton();
		_IMPORT.makeButton();
		_IF.makeButton();
		_ELSEIF.makeButton();
		_ELSE.makeButton();
		_VAR.makeButton();
		_WHILE.makeButton();
		_FOR.makeButton();
		_SWITCH.makeButton();
		_CASE.makeButton();
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
		System.out.println("This is not a break.");
		this.setBackground(new java.awt.Color(200,200,201));
		java.awt.Graphics2D betterBrush = (java.awt.Graphics2D) aBrush;
		betterBrush.setColor(new java.awt.Color(233,233,240));
		betterBrush.fillPolygon(_templateTray);
		_buttonSocket.paint(aBrush);
		_app.paint(aBrush);
		if(_temp!=null){_temp.paint(aBrush);}
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
	
	private void readObject(java.io.ObjectInputStream in) throws ClassNotFoundException, IOException
	{
		_app=(App)in.readObject();
		_bc=(int)in.readObject();
		_blink=(Color)in.readObject();
		_bUp=(boolean)in.readObject();
		_buttonSocket=(Socket)in.readObject();
		_codeYpos=(int)in.readObject();
		_importsYPos=(int)in.readObject();
		_scale=(int)in.readObject();
		_trayWidth=(int)in.readObject();
		_cp=(ClassesPanel)in.readObject();
		_frame=(GpFrame)in.readObject();
		_ogT=(long)in.readObject();
		//_sockets=(kVec<kVec<Socket>>)in.readObject();
		java.util.ArrayList<java.util.ArrayList<Socket>> _temp = (java.util.ArrayList<java.util.ArrayList<Socket>>)in.readObject();
		_sockets = new kVec<kVec<Socket>>();
		for(Object k:_temp){
			kVec<Socket> foo = new kVec<Socket>(); 
			for(Socket k2 : (java.util.ArrayList<Socket>) k){
				foo.que(k2);
			}
			_sockets.que(foo);
		}
		_templateTray=(Polygon)in.readObject();
		_timer=(Timer)in.readObject();
	}
	
	private void writeObject(java.io.ObjectOutputStream out) throws IOException
	{
		out.writeObject(_app);
		out.writeObject(_bc);
		out.writeObject(_blink);
		out.writeObject(_bUp);
		out.writeObject(_buttonSocket);
		out.writeObject(_codeYpos);
		out.writeObject(_importsYPos);
		out.writeObject(_scale);
		out.writeObject(_trayWidth);
		out.writeObject(_cp);
		out.writeObject(_frame);
		out.writeObject(_ogT);
		//out.writeObject(_sockets);
		java.util.ArrayList<java.util.ArrayList<Socket>> Temp= new java.util.ArrayList<java.util.ArrayList<Socket>>();
		for(kVec<Socket> k : _sockets){
			java.util.ArrayList<Socket> temp= new java.util.ArrayList<Socket>();
			for(Socket k2 : k){
				temp.add(k2);
			}
			Temp.add(temp);
		}
		out.writeObject(Temp);
		out.writeObject(_templateTray);
		out.writeObject(_timer);
	}

	public kVec<ReadableSRC> GetReadables() {
		return(_app.writeCode());
	}
}
