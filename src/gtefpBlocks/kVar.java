package gtefpBlocks;

import gtefpMain.WorkspacePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class kVar extends CodePiece implements Relative, Buttonable, java.awt.event.KeyListener{
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
	public Socket _after;
	public boolean _focused;
	private java.awt.Polygon _cursor;
	private int _cursorPos;
	private int _cursorPlace;
	public kVar(WorkspacePanel wp){
		super(wp);
		_wp.addKeyListener(this);
		_text = "";
		//_c = new java.awt.Color(150,100,100);
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
		_after.setRel(0,this.ysize()-_after.ysize());
		//_after._dontDraw = true;
		_focused = false;
		_after.setDD(true);
	}
	public void setDDA(boolean b){
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
		String r = _text;
		return(r);
	}
	public void setText(String text){
		_text=text;
	}
	public void setEditable(boolean b){
		_isEditable = b ;
	}
	@Override
	public void Move(int dx,int dy){
		super.Move(dx, dy);
		Draw_p();
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
		//Draw_p();
		super.paint(aBrush);
		_after.paint(aBrush);
		if(_focused){
			java.awt.Graphics2D betterBrush = (java.awt.Graphics2D) aBrush;
			betterBrush.setColor(_wp.blink());
			_cursor = new java.awt.Polygon(new int []{_xpos+_cursorPos,_xpos+_cursorPos+1,_xpos+_cursorPos+1,_xpos+_cursorPos},new int []{_ypos,_ypos,_ypos+ height(),_ypos+ height()},4);
			betterBrush.fillPolygon(_cursor);
		}
		//draw a polygon underneth?
		//if(_inUse){
			//System.out.println("paintString");
			//System.out.println(_text);
		aBrush.setColor(_tColor);
		aBrush.setFont(_font);
		setRel();
		aBrush.drawString(_text,  _xpos , _ypos+_scale*3);
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
		Draw_p();
		super.paint(aBrush);
		_after.paint(aBrush);
		//draw a polygon underneth?
		if(_inUse){
			//System.out.println("paintString");
			//System.out.println(_text);
			aBrush.setColor(c);
			aBrush.setFont(_font);
			setRel();
			aBrush.drawString(_text,  _xpos , _ypos+_scale*3);
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
	public int ysize(){
		return(super.ysize()+_after.ysize());
	}
	public void Draw_p(){
		if(_inUse==false){
			_xs=_OGxs;
			_ys=_OGys;
			super.Draw_p();
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
			super.Draw_p();
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
			_cp.insert(_after.getPulg());}
		_after.unsert();
		setCp(this);
	}
	@Override
	public void mouseReleased(java.awt.event.MouseEvent e){
		if(_p.contains(e.getPoint())){
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
			for(Socket s: _wp._sockets){
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
		}
		if(_text.length()>0){
			_inUse=true;
		}else{
			_inUse=false;
		}
		}
		// TODO Auto-generated method stub
		
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
				_temp.Place(_xpos,_ypos);
				_temp.setRel(0,0);
				_temp.select();
				_temp.mousePressed(e);
				_wp.setTemp(_temp);
				_wp.repaint();
				_temp.Draw_p();
				_wp.repaint();
			}
		}
		
	}
}
