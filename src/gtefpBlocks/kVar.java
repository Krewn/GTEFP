package gtefpBlocks;

import gtefpMain.WorkspacePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import util.JavaFile;
import util.kVec;

public class kVar extends CodePiece implements Relative, Buttonable, java.awt.event.KeyListener, Serializable{
	private static final long serialVersionUID = 1L;
	private String _text;
	private String _style;
	private java.awt.Font _font;
	private int _height;
	private int _width;
	private boolean _inUse;
	private int [] _OGxs;
	private int [] _OGys;
	private boolean _isEditable;
	private java.awt.Color _tColor;
	private Socket _after;
	public boolean _focused;
	private java.awt.Polygon _cursor;
	private int _cursorPos;
	private int _cursorPlace;
	private JavaFile _jf;
	
	public kVar() // necessary for deserialization // really tho?
	{
		super();
	}
	
	public kVar(WorkspacePanel wp){
		super(wp);
		_jf = _wp.curJF();
		_wp.addKeyListener(this);
		_text = "";
		_isButton=false;
		_isEditable = true;
		_inUse=false;
		_OGxs = new int[]{0,4,4,0};
		_OGys = new int[]{0,0,4,4};
		_style="Arial";
		_font = new java.awt.Font(_style,java.awt.Font.PLAIN,20);
		_tColor= new java.awt.Color(0,0,0);
		_after = new Socket(wp);
		_after.setCp(this);
		_after.setRel(0,this.ySize()-_after.ySize());
		//_after._dontDraw = true;
		_focused = false;
		_after.setDD(true);
	}
	@Override
	public String writeCode()
	{
		return _text;
	}
	public void setDDA(boolean b){ // don't draw after (won't/will see socket)
		_after.setDD(b);
	}
	public kVar(WorkspacePanel wp,String s){
		this(wp);
		_text = s;
		_inUse=true;
	}
	public void setTextColor(java.awt.Color tc){
		_tColor = tc;
	}
	public void setCp(CodePiece cp){
		_cp = cp;
		setRel(0,0);
		_after.setRel();
	}
	public String getText(){
		//String r = _text;
		//return(r);
		
		return _text;
	}
	public void setText(String text){
		_text=text;
	}
	public void setEditable(boolean b){
		_isEditable = b ;
	}
	@Override
	public void move(int dx,int dy){
		super.move(dx, dy);
		draw_p();
	}
	@Override
	public void paint(Graphics aBrush){
		if(_sized==false){
			prefHeight(aBrush,_scale*4);
		}
		FontMetrics metrics = aBrush.getFontMetrics(_font);
		_width = metrics.stringWidth(_text)/_scale;
		Color oldColor = aBrush.getColor();
		//java.awt.Graphics2D betterBrush = (java.awt.Graphics2D) aBrush;
		//betterBrush.setFont(_font);
		_cursorPos = metrics.stringWidth(_text.substring(0,_cursorPlace));
		if(!_inUse){
			java.awt.Polygon _blank = new java.awt.Polygon(new int []{_xPos+_cursorPos,_xPos+_cursorPos+1,_xPos+_cursorPos+1,_xPos+_cursorPos},new int []{_yPos,_yPos,_yPos+ height(),_yPos+ height()},4);
			java.awt.Graphics2D someBrush = (java.awt.Graphics2D) aBrush;
			aBrush.setColor(new java.awt.Color(70,0,10));
			someBrush.fillPolygon(_blank);
		}
		//Draw_p();
		super.paint(aBrush);
		_after.paint(aBrush);
		if(_focused){
			java.awt.Graphics2D betterBrush = (java.awt.Graphics2D) aBrush;
			betterBrush.setColor(_wp.blink());
			_cursor = new java.awt.Polygon(new int []{_xPos+_cursorPos,_xPos+_cursorPos+1,_xPos+_cursorPos+1,_xPos+_cursorPos},new int []{_yPos,_yPos,_yPos+ height(),_yPos+ height()},4);
			betterBrush.fillPolygon(_cursor);
		}
		//draw a polygon underneth?
		//if(_inUse){
			//System.out.println("paintString");
			//System.out.println(_text);
		aBrush.setColor(_tColor);
		aBrush.setFont(_font);
		setRel();
		aBrush.drawString(_text,  _xPos , _yPos+_scale*3);
		aBrush.setColor(oldColor);
		_width = metrics.stringWidth(_text)/_scale;
		//}
	}
	public void paint(Graphics aBrush, Color c){
		if(_sized==false){
			prefHeight(aBrush,_scale*4);
		}
		Color oldColor = aBrush.getColor();
		//java.awt.Graphics2D betterBrush = (java.awt.Graphics2D) aBrush;
		//betterBrush.setFont(_font);
		draw_p();
		super.paint(aBrush);
		_after.paint(aBrush);
		//draw a polygon underneth?
		if(_inUse){
			//System.out.println("paintString");
			//System.out.println(_text);
			aBrush.setColor(c);
			aBrush.setFont(_font);
			setRel();
			aBrush.drawString(_text,  _xPos , _yPos+_scale*3);
			aBrush.setColor(oldColor);
		}
	}
	public void prefHeight(java.awt.Graphics g,int h){
		int s= 1;
		_font = new java.awt.Font(_style,java.awt.Font.PLAIN,s);
		FontMetrics metrics = g.getFontMetrics(_font);
		g.setFont(_font);
		while((g.getFontMetrics().getHeight())<h){
			s++;
			_font = new java.awt.Font(_style,java.awt.Font.PLAIN,s);
			g.setFont(_font);
		}
		metrics = g.getFontMetrics(_font);
		_width = metrics.stringWidth(_text)/_scale;
		_height= metrics.getHeight();
		_sized = true;
		//System.out.println("sized");
	}
	public int width(){
		int r = _width;
		return(r);
	}
	public int height(){
		int r = _height;
		return(r);
	}
	@Override
	public int ySize(){
		return(super.ySize()+_after.ySize());
	}
	public void draw_p(){
		_after.setRel();
		if(_inUse==false){
			_xs=_OGxs;
			_ys=_OGys;
			super.draw_p();
			_width = 4;
		}else{
			if(_isEditable){
				int w = width();
				int h = height()/_scale;
				_xs=new int [] {0,w,w,0};
				_ys=new int [] {0,0,h,h};
			}else{
				_xs=new int [] {0};
				_ys=new int [] {0};
			}
			super.draw_p();
			//_p = new java.awt.Polygon({0},{0},1);
		}
	}
	@Override
	public void clicked(){
		if(_isButton){}else{
			
		}
	}
	@Override
	public void makeButton() {
		// TODO Auto-generated method stub
		_isButton=true;
	}
	public void unplug(){
		super.unplug();
		if(_after.inUse()){
			_cp.insert(_after.getPlug());}
		_after.unsert();
		setCp(this);
	}
	@Override
	public void mouseReleased(java.awt.event.MouseEvent e){
		if(_p.contains(e.getPoint()) && _wp.curJF() == _jf){
			if(_isEditable){
				_focused = true;
				_cursorPlace = 0; 
				//System.out.println("focused");
			}
		}else{
			_focused = false;
			//System.out.println("UN - focused");
		}
		if(_selected){
			java.awt.geom.Area testa = new java.awt.geom.Area(_p);
			Socket q = null;
			for(Socket s: _wp.sockets()){
				if(s!=this._after){
					java.awt.geom.Area a =new java.awt.geom.Area(s._p);
					a.intersect(testa);
					if (!a.isEmpty()){
						q=s;
					}
				}
			}//Had to do this q nonsense to avoid current modification error in the prior for loop.
			if(q!=null){
				q.insert(this);
			}
		}
		_selected=false;
		_wp.repaint();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(_focused){
			int KeyCode = e.getKeyCode();
			//System.out.println(KeyCode);
			switch(KeyCode){
				case 37://left arrow
					if(_cursorPlace > 0){
						_cursorPlace--;
					}
					break;
				case 39://right arrow
					if(_cursorPlace < _text.length()){
						_cursorPlace++;
					}
					break;
				case 8:
					if(_cursorPlace > 0){
						if(_text.length()>0){
							if(_cursorPlace<_text.length()){
								_text = _text.substring(0,_cursorPlace-1)+_text.substring(_cursorPlace,_text.length());
							}else{
								_text = _text.substring(0,_cursorPlace-1);
							}
							_cursorPlace-=1;
						}
					}
					break;
				case 127:
					if(_cursorPlace < _text.length()){
						if(_text.length()>0){
							if(_cursorPlace>0){
								_text = _text.substring(0,_cursorPlace)+_text.substring(_cursorPlace+1,_text.length());
							}else{
								_text = _text.substring(1,_text.length());
							}
						}
					}
					break;
				case KeyEvent.VK_ENTER:
					//setDDA(false);
					setDDA(!getDDA());
					System.out.print(_after.inUse());
					break;
			}
			if(_text.length()>0){
				_inUse=true;
			}else{
				_inUse=false;
			}
			_wp.repaint();
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("released");
	}
	@Override
	public void keyTyped(KeyEvent e) {
		//System.out.println("hehe");
		//System.out.println(e.getKeyChar());
		if(_focused){
			//System.out.println("keyTyped");
			//System.out.println(e.getKeyChar());
			//System.out.println(e.getKeyChar());
			if((int)e.getKeyChar()<127 && (int)e.getKeyChar() > 31 ){
				_text=_text.substring(0,_cursorPlace)+e.getKeyChar()+_text.substring(_cursorPlace,_text.length());
				_cursorPlace+=1;
				_wp.repaint();
			}
		}
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		_lastMouseLoc = e.getPoint();
		if(_p.contains(_lastMouseLoc)){
			_selected=true;
			if(_isButton){
				kVar _temp = new kVar(_wp);
				_temp.setCp(_temp);
				_temp.place(_xPos,_yPos);
				_temp.setRel(0,0);
				_temp.select();
				_temp.mousePressed(e);
				_wp.setTemp(_temp);
				_wp.repaint();
				_temp.draw_p();
				_wp.repaint();
			}
		}
		
	}
	
	public boolean getDDA()
	{
		return _after.getDD();
	}
	
	public Socket getAfter()
	{
		return _after;
	}
	
	private void readObject(java.io.ObjectInputStream in) throws ClassNotFoundException, IOException
	{
		_c=(Color)in.readObject();
		java.util.ArrayList<Object> _temp = new ArrayList<Object>();
		_code = new kVec<Maluable>();
		for(Object k:_temp){
			_code.que((Maluable) k);
		}
		_code=(kVec<Maluable>)in.readObject();
		_cp=(CodePiece)in.readObject();
		_isButton=(boolean)in.readObject();
		_lastMouseLoc=(java.awt.Point)in.readObject();
		_p=(CompPoly)in.readObject();
		_scale=(int)in.readObject();
		_selected=(boolean)in.readObject();
		_sized=(boolean)in.readObject();
		_wp=(WorkspacePanel)in.readObject();
		_xPos=(int)in.readObject();
		_xRel=(int)in.readObject();
		_xs=(int[])in.readObject();
		_yPos=(int)in.readObject();
		_yRel=(int)in.readObject();
		_ys=(int[])in.readObject();
		_yWidth=(int)in.readObject();
		_wp.addMouseListener(this);
		_wp.addMouseMotionListener(this);
		_text=(String)in.readObject();
		_style=(String)in.readObject();
		_font=(java.awt.Font)in.readObject();
		_height=(int)in.readObject();
		_width=(int)in.readObject();
		_inUse=(boolean)in.readObject();
		_OGxs=(int[])in.readObject();
		_OGys=(int[])in.readObject();
		_isEditable=(boolean)in.readObject();
		_tColor=(java.awt.Color)in.readObject();
		_after=(Socket)in.readObject();
		_focused=(boolean)in.readObject();
		_cursor=(java.awt.Polygon)in.readObject();
		_cursorPos=(int)in.readObject();
		_cursorPlace=(int)in.readObject();
		_jf=(JavaFile)in.readObject();
	}
	
	private void writeObject(java.io.ObjectOutputStream out) throws IOException
	{
		out.writeObject(_c);
		java.util.ArrayList<Object> _temp = new java.util.ArrayList<Object>();
		for(Maluable k : _code){_temp.add(k);}
		out.writeObject(_code);
		out.writeObject(_cp);
		out.writeObject(_isButton);
		out.writeObject(_lastMouseLoc);
		out.writeObject(_p);
		out.writeObject(_scale);
		out.writeObject(_selected);
		out.writeObject(_sized);
		out.writeObject(_wp);
		out.writeObject(_xPos);
		out.writeObject(_xRel);
		out.writeObject(_xs);
		out.writeObject(_yPos);
		out.writeObject(_yRel);
		out.writeObject(_ys);
		out.writeObject(_yWidth);
		out.writeObject(_text);
		out.writeObject(_style);
		out.writeObject(_font);
		out.writeObject(_height);
		out.writeObject(_width);
		out.writeObject(_inUse);
		out.writeObject(_OGxs);
		out.writeObject(_OGys);
		out.writeObject(_isEditable);
		out.writeObject(_tColor);
		out.writeObject(_after);
		out.writeObject(_focused);
		out.writeObject(_cursor);
		out.writeObject(_cursorPos);
		out.writeObject(_cursorPlace);
		out.writeObject(_jf);
	}
}
