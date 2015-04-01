package gtefpBlocks;

import gtefpMain.WorkspacePanel;
import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import util.JavaFile;
import util.kVec;

public abstract class CodePiece extends javax.swing.event.MouseInputAdapter implements Maluable, Buttonable
{
	protected Color          _c;
	protected kVec<Maluable> _code;
	protected CodePiece      _cp;
	protected boolean        _isButton;
	protected java.awt.Point _lastMouseLoc;
	protected CompPoly       _p;
	public    int            _scale;
	public    boolean        _selected;
	protected boolean        _sized;
	public    WorkspacePanel _wp;
	protected int            _xPos;
	protected int            _xRel;
	protected int[]          _xs;
	protected int            _yPos;
	protected int            _yRel;
	protected int[]          _ys;
	protected int            _yWidth;

		
	public CodePiece() {} // necessary for deserialization
	
	public CodePiece(WorkspacePanel wp){
		wp.addMouseListener(this);
		wp.addMouseMotionListener(this);
		_code=new kVec<Maluable>();
		_cp = this;
		_lastMouseLoc = new java.awt.Point();
		_xs=new int []{0};
		_ys=new int []{0};
		_p=new CompPoly(_xs,_ys,_xs.length,this); 
		_isButton=false;
		_wp=wp;
		_c = new Color(255,255,255);
		_scale = _wp.getScale();
		_sized=false;
		_selected=false;
		_xPos=0;
		_yPos=0;
	}
	
	public boolean isCodeCompleted()
	{
		int size = _code.size();
		
		for (int i = 0; i < size; i++)
		{
			CodePiece cp = (CodePiece)_code.elementAt(i);
			
			if ((cp == null) || !cp.isCodeCompleted())
				return false;
		}
		
		return true;
	}
	
	public abstract void clicked();
	
	public void draw_p(){
		//System.out.println("!! The here tho");
		int [] xs = new int[_xs.length];
		int [] ys = new int[_ys.length];
		for(int k = 0 ; k < xs.length ; k++){
			xs[k]=_xPos+_xs[k]*_scale;
			ys[k]=_yPos+_ys[k]*_scale;
		}
		_p = new CompPoly(xs,ys,xs.length,this);
	}
	
	public int getScale(){
		int r = _scale;
		return(r);
	}
	
	public WorkspacePanel getWp(){
		WorkspacePanel r = _wp;
		return(r);
	}
	
	public int getXPos()
	{
		int r = _xPos;
		return r;
	}
	
	public int getYPos()
	{
		int r = _yPos;
		return r;
	}
	
	public void insert(CodePiece plug){	
	}
	
	public boolean isButton(){
		boolean r = _isButton;
		return(r);
	}
	
	public void makeButton(){
		_isButton=true;
	}
	
	public void mouseClicked(java.awt.event.MouseEvent e){
		if(_p.contains(_lastMouseLoc)){
			clicked();
		}
		_wp.repaint();
	}
	
	public void mouseDragged(java.awt.event.MouseEvent e){
		if(_selected){
			if(_p.contains(_lastMouseLoc)){
				if(_cp!=this){
					if(_isButton == false){
						unplug();
						}
				}
				int dx = e.getX()-_lastMouseLoc.x;
				int dy = e.getY()-_lastMouseLoc.y;
				move(dx,dy);
				_wp.repaint();
			}
		}
		_lastMouseLoc = e.getPoint();
	}
	
	public abstract void mousePressed(java.awt.event.MouseEvent e);
	
	public void mouseReleased(java.awt.event.MouseEvent e){
		_selected=false;
	}
	
	@Override
	public void move(int dx, int dy) {
		_xPos+=dx;
		_yPos+=dy;
		draw_p();
	}
	
	public void paint(java.awt.Graphics aBrush){
		//System.out.println("CodePiecePrint");
		java.awt.Color oldColor = aBrush.getColor();
		java.awt.Graphics2D betterBrush = (java.awt.Graphics2D) aBrush;
		betterBrush.setColor(_c);
		this.draw_p();
		betterBrush.setColor(_c);
		betterBrush.fillPolygon(_p);
		betterBrush.setColor(oldColor);
	}
	
	@Override
	public void place(int x, int y) {
		_xPos=x;
		_yPos=y;
		draw_p();
	}
	
	@Override
	public void scale(int s) {
		_scale=s;
		//scale comppolys
	}
	
	public void select(){
		System.out.println("truth");
		_selected = true;
	}
	
	public abstract void setCp(CodePiece cp);
	
	public void setPosition(int x,int y){
		_xPos = x; _yPos=y;
	}
	
	public void setRel(){
		_xPos = _cp.getXPos()+_xRel*_scale;
		_yPos = _cp.getYPos()+_yRel*_scale;
	}
	
	public void setRel(int x, int y){
		_xRel = x;_yRel = y;
		_xPos = _cp.getXPos()+_xRel*_scale;
		_yPos = _cp.getYPos()+_yRel*_scale;
	}
	
	public void unplug(){
		//_cp = (Socket) _cp;
		_cp.unsert();
		_wp.setTemp(this);
	}
	
	public void unsert(){//special method only for the socket.
	}
	
	public abstract int width();
	
	public String writeCode()
	{
		String s = "";
		for (Maluable k : _code)
			s += k.writeCode();
		
		return s;
	}
	
	public int ySize(){
		return(4);
	}

	public void writeObject(ObjectOutputStream out)throws IOException {
		out.writeObject(_c);
		java.util.ArrayList<Object> _temp = new java.util.ArrayList<Object>();
		for(Maluable k : _code){_temp.add(k);}
		out.writeObject(_temp);
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
	}

	public void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException{
		
		_c=(Color)in.readObject();
		//_code=(kVec<Maluable>)in.readObject();
		java.util.ArrayList<Object> _temp = (java.util.ArrayList<Object>)in.readObject();
		_code = new kVec<Maluable>();
		for(Object k:_temp){
			_code.que((Maluable) k);
		}
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
		
	}
	
	//public void mousePressed(java.awt.event.MouseEvent e){
	//	_lastMouseLoc = e.getPoint();
	//	if(_p.contains(_lastMouseLoc )){_selected=true;}
	//}
}
