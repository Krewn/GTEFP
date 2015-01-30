package gpBase;
public class Swatch {
	private java.awt.Polygon _swatch;
	private int [] _x;
	private int [] _y;
	private java.awt.Color _c;
	Swatch(int x,int y){
		_x = new int [] {0+x,10+x,10+x,0+x};
		_y = new int [] {0+y,0+y,45+y,45+y};
		_swatch = new java.awt.Polygon(_x,_y,4);
	}
	public void setColor(java.awt.Color c){
		_c = c;
	}
	public java.awt.Color getColor(){
		java.awt.Color _C = _c;
		return(_C);
	}
	public void paint(java.awt.Graphics aBrush){
		java.awt.Color oldColor = aBrush.getColor();
		java.awt.Graphics2D betterBrush = (java.awt.Graphics2D) aBrush;
		betterBrush.setColor(_c);
		betterBrush.fillPolygon(_swatch);
		aBrush.setColor(oldColor);
	}
}
