package util;

import java.awt.Polygon;

import gtefpBlocks.CompPoly;
import gtefpMain.ClassesPanel;

public class NewClassButton extends javax.swing.event.MouseInputAdapter
{
	public int _xPos;
	public int _yPos;
	public int _scale;
	private int[] _xs;
	private int[] _ys;
	private ClassesPanel _cp;
	private java.awt.Color _c;
	
	private java.awt.Polygon _plusButton;
	public NewClassButton(ClassesPanel cp)
	{
		_xs = new int[]{0,1,1,2,2,3,3,2,2,1,1,0};
		_ys = new int[]{1,1,0,0,1,1,2,2,3,3,2,2};
		_scale = 5;
		_cp = cp;
		_yPos = 0;
		_xPos = _cp.getWidth() - (_scale * 4);
		draw_p();
		_c = new java.awt.Color(0,210,35);
		_cp.addMouseListener(this);
		_cp.repaint();
	}
	
	public void draw_p(){
		//System.out.println("!! The here tho");
		int [] xs = new int[_xs.length];
		int [] ys = new int[_ys.length];
		_xPos = _cp.getWidth() - (_scale * 4);
		for(int k = 0 ; k < xs.length ; k++){
			xs[k]=_xPos+_xs[k]*_scale;
			ys[k]=_yPos+_ys[k]*_scale;
		}
		_plusButton = new java.awt.Polygon(xs,ys,xs.length);
		/*String a = "";
		for (int k : xs){
			a += k +" ,";
		}
		System.out.println(a);
		a = "";
		for (int k : ys){
			a += k +" ,";
		}
		System.out.println(a);*/
	}
	
	public void paint(java.awt.Graphics aBrush){
		//System.out.println("CodePiecePrint");
		java.awt.Color oldColor = aBrush.getColor();
		java.awt.Graphics2D betterBrush = (java.awt.Graphics2D) aBrush;
		this.draw_p();
		betterBrush.setColor(_c);
		betterBrush.fillPolygon(_plusButton);
		betterBrush.setColor(oldColor);
	}
	
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e){
		java.awt.Point p = e.getPoint();
		if(_plusButton.contains(p)){
			_cp.newClass();
		}
		_cp.repaint();
	}

}
