package gtefpMain;
import java.awt.Polygon;
import java.awt.Color;
public abstract class CodePiece extends javax.swing.event.MouseInputAdapter implements Maluable, Buttonable{
	protected int _xpos;
	protected int _ypos;
	protected int _ywidth;
	protected int _xrel;
	protected int _yrel; // for cases of pieces with parent pieces, relative positioning...
	protected CompPoly _p;
	protected Color _c;
	protected int[] _xs;
	protected int[] _ys;
	public int _scale;
	protected boolean _sized;
	protected boolean _isButton;
	protected kVec<kVar> _code;
	protected java.awt.Point _lastMouseLoc;
	private App _App;
	public abstract void setCp(CodePiece cp);
	public abstract int width();
	public WorkspacePanel _wp;
	public boolean _selected;
	protected CodePiece _cp;
	public CodePiece(WorkspacePanel wp){
		wp.addMouseListener(this);
		wp.addMouseMotionListener(this);
		_code=new kVec<kVar>();
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
		_xpos=0;
		_ypos=0;
	}
	public void paint(java.awt.Graphics aBrush){
		//System.out.println("CodePiecePrint");
		java.awt.Color oldColor = aBrush.getColor();
		java.awt.Graphics2D betterBrush = (java.awt.Graphics2D) aBrush;
		betterBrush.setColor(_c);
		this.Draw_p();
		betterBrush.setColor(_c);
		betterBrush.fillPolygon(_p);
		betterBrush.setColor(oldColor);
	}
	public void select(){
		System.out.println("truth");
		_selected = true;
	}
	public void makeButton(){
		_isButton=true;
	}
	public boolean isButton(){
		boolean r = _isButton;
		return(r);
	}
	public int getScale(){
		int r = _scale;
		return(r);
	}
	public void setposition(int x,int y){
		_xpos = x; _ypos=y;
	}
	public void Draw_p(){
		//System.out.println("!! The here tho");
		int [] xs = new int[_xs.length];
		int [] ys = new int[_ys.length];
		for(int k = 0 ; k < xs.length ; k++){
			xs[k]=_xpos+_xs[k]*_scale;
			ys[k]=_ypos+_ys[k]*_scale;
		}
		_p = new CompPoly(xs,ys,xs.length,this);
	}
	public void setRel(int x, int y){
		_xrel = x;_yrel = y;
		_xpos = _cp._xpos+_xrel*_scale;
		_ypos = _cp._ypos+_yrel*_scale;
	}public void setRel(){
		_xpos = _cp._xpos+_xrel*_scale;
		_ypos = _cp._ypos+_yrel*_scale;
	}
	public WorkspacePanel getWp(){
		WorkspacePanel r = _wp;
		return(r);
	}
	public int ysize(){
		return(4);
	}
	@Override
	public void Move(int dx, int dy) {
		_xpos+=dx;
		_ypos+=dy;
		Draw_p();
	}
	@Override
	public void Place(int x, int y) {
		_xpos=x;
		_ypos=y;
		Draw_p();
	}
	@Override
	public void scale(int s) {
		_scale=s;
		//scale comppolys
	}
	//public void mousePressed(java.awt.event.MouseEvent e){
	//	_lastMouseLoc = e.getPoint();
	//	if(_p.contains(_lastMouseLoc )){_selected=true;}
	//}
	public abstract void mousePressed(java.awt.event.MouseEvent e);
	public void mouseReleased(java.awt.event.MouseEvent e){
		_selected=false;
	}
	public void mouseDragged(java.awt.event.MouseEvent e){
		if(_selected){
			if(_p.contains(_lastMouseLoc)){
				int dx = e.getX()-_lastMouseLoc.x;
				int dy = e.getY()-_lastMouseLoc.y;
				Move(dx,dy);
				_wp.repaint();
			}
		}
		_lastMouseLoc = e.getPoint();
	}
	public abstract void clicked();
	public void mouseClicked(java.awt.event.MouseEvent e){
		if(_p.contains(_lastMouseLoc)){
			clicked();
		}
		_wp.repaint();
	}
}
