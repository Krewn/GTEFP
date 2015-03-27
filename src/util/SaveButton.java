package util;

import java.io.IOException;
import java.io.Serializable;

import gtefpMain.ClassesPanel;
import gtefpMain.GpFrame;
import gtefpMain.SaveLoadFrame;

public class SaveButton extends javax.swing.event.MouseInputAdapter implements Serializable
{
	private static final long serialVersionUID = 1L;
	public int _xPos;
	public int _yPos;
	public int _scale;
	protected int[] _xs;
	protected int[] _ys;
	protected ClassesPanel _cp;
	private java.awt.Color _c;
	protected java.awt.Polygon _plusButton;
	protected SaveLoadFrame _slf;
	
	public SaveButton(ClassesPanel cp, SaveLoadFrame slf)
	{
		_slf = slf;
		_xs = new int[]{0, 1, 1, 0};
		_ys = new int[]{0, 0, 1, 1};
		_scale = 15;
		_cp = cp;
		_yPos = 0;
		_xPos = _cp.getWidth() - (_scale * 8);
		draw_p();
		_c = new java.awt.Color(0,210,35);
		_cp.addMouseListener(this);
		_cp.repaint();
	}
	
	public void draw_p(){
		int [] xs = new int[_xs.length];
		int [] ys = new int[_ys.length];
		_xPos = _cp.getWidth() - (_scale * 4);
		for(int k = 0 ; k < xs.length ; k++){
			xs[k]=_xPos+_xs[k]*_scale;
			ys[k]=_yPos+_ys[k]*_scale;
		}
		_plusButton = new java.awt.Polygon(xs,ys,xs.length);
	}
	
	public void paint(java.awt.Graphics aBrush){
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
		if(_plusButton.contains(p))
			_slf.saveToFile();
	}
	
	private void readObject(java.io.ObjectInputStream in) throws ClassNotFoundException, IOException
	{
		in.defaultReadObject();
	}
	
	private void writeObject(java.io.ObjectOutputStream out) throws IOException
	{
		out.defaultWriteObject();
	}
}