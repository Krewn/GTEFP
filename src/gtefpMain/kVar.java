package gtefpMain;

import java.awt.*;
import java.awt.event.MouseEvent;

public class kVar extends CodePiece implements Relative, Buttonable {
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
	public kVar(WorkspacePanel wp){
		super(wp);
		_isButton=false;
		_text = "";
		_isEditable = true;
		_inUse=false;
		_OGxs = new int[]{0,4,4,0};
		_OGys = new int[]{0,0,4,4};
		_style="Arial";
		_font = new java.awt.Font(_style,java.awt.Font.PLAIN,20);
		_tColor= new java.awt.Color(0,0,0);
	}
	public void setTextColor(java.awt.Color tc){
		_tColor = tc;
	}
	public kVar(WorkspacePanel wp,String s){
		this(wp);
		_text = s;
		_inUse=true;
	}
	public void setCp(CodePiece cp){
		_cp = cp;
		setRel(0,0);
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
	public void paint(Graphics aBrush){
		if(_sized==false){
			prefHeight(aBrush,_scale*4);
		}
		Color oldColor = aBrush.getColor();
		//java.awt.Graphics2D betterBrush = (java.awt.Graphics2D) aBrush;
		//betterBrush.setFont(_font);
		Draw_p();
		super.paint(aBrush);
		//draw a polygon underneth?
		if(_inUse){
			//System.out.println("paintString");
			//System.out.println(_text);
			aBrush.setColor(_tColor);
			aBrush.setFont(_font);
			setRel();
			aBrush.drawString(_text,  _xpos , _ypos+_scale*3);
			aBrush.setColor(oldColor);
		}
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
		}metrics = g.getFontMetrics(_font);
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
	public void Draw_p(){
		if(_inUse==false){
			_xs=_OGxs;
			_ys=_OGys;
			super.Draw_p();
			_width = 4;
		}else{
			_xs=new int [] {0};
			_ys=new int [] {0};
			super.Draw_p();
			//_p = new java.awt.Polygon({0},{0},1);
		}
	}
	@Override
	public void clicked(){
		// TODO Auto-generated method stub
		if(_isButton){}else{}
	}
	@Override
	public void makeButton() {
		// TODO Auto-generated method stub
		_isButton=true;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
