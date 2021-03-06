package gtefpBlocks;

public class CompPoly extends java.awt.Polygon implements Maluable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CodePiece _cp;
	private int _xpos; //positions relative to the parent CodePiece.
	private int _ypos;
	private int _scale;
	private java.awt.Color _C;
	private java.awt.Point _lastMouseLoc;
	public CompPoly(CodePiece cp){
		
	}
	public CompPoly(int[] xs, int[] ys, int l,CodePiece cp){
		super(xs,ys,l);
		_cp = cp;
	}	
	public void mousePressed(java.awt.event.MouseEvent e){
		System.out.println("compPolyMousePress");
		_lastMouseLoc = e.getPoint();
		_cp.mousePressed(e);
	}
	public void setColor(int r , int g , int b){
		_C=new java.awt.Color(r,g,b);
	}
	public void mouseDragged(java.awt.event.MouseEvent e){
		_cp.mouseDragged(e);
		_lastMouseLoc = e.getPoint();
	}
	public void mouseClicked(java.awt.event.MouseEvent e){
		if(this.contains(_lastMouseLoc)){
			_cp.mouseClicked(e);
		}
		_cp._wp.repaint();
	}
	public java.awt.Color getColor(){
		java.awt.Color C = _C;
		return(C);
	}
	@Override
	public void draw_p() {
		// TODO Auto-generated method stub
		
	}
	public void move(int dx, int dy) {
		_cp.move(dx, dy);
	}
	@Override
	public void place(int x, int y) {
		_xpos=x;
		_ypos=y;
		draw_p();
	}
	@Override
	public void scale(int s) {
		_scale=s;
		//scale comppolys
	}
	@Override
	public String writeCode() {
		// TODO Auto-generated method stub
		return null;
	}
}
