package gtefpBlocks;

import gpBase.trailer;

public abstract class CodePiece extends javax.swing.event.MouseInputAdapter{
	private java.awt.Point _lastMouseLoc;
	private int _xpos;
	private int _ypos;
	private int [] _x;
	private int [] _y;
	private java.awt.Polygon _Piece;
	private java.awt.Color _C;
	private javax.swing.JPanel _panel;
	public CodePiece(){
			super();
			_C = new java.awt.Color(255,255,255);
			_xpos = 0;
			_ypos = 0;
	}
	public void setPanel(javax.swing.JPanel pan){
		_panel=pan;
	}
	public void move(int dx, int dy){
		for(int k=0;k<_x.length;k++){
			_x[k]+=dx;
			_y[k]+=dy;
		}
		_Piece=new java.awt.Polygon(_x,_y,6);
	}
}
