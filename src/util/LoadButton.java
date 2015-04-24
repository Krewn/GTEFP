/*********************************************************************
 * LoadButton opens a window prompting the user for a file location. *
 * A GpFrame is then read and loaded from that location.             *
 *********************************************************************/

package util;

import java.awt.Color;
import java.io.IOException;
import java.io.Serializable;

import gtefpMain.ClassesPanel;
import gtefpMain.SaveLoadFrame;

public class LoadButton extends SaveButton implements Serializable
{
	private static final long serialVersionUID = 1L;

	public LoadButton(ClassesPanel cp, SaveLoadFrame slf)
	{
		super(cp, slf);
		_xs = new int[]{0, 1, 1, 0};
		_ys = new int[]{0, 0, 1, 1};
		_scale = 15;
		_xPos = _cp.getWidth() - (_scale * 12);
		_c = new java.awt.Color(255,0,0);
		draw_p();
		_cp.repaint();
		_font = new java.awt.Font("Arial", java.awt.Font.BOLD, 12);
		_fontColor = new Color(0, 255, 0);
	}
	
	@Override
	public void draw_p()
	{
		int [] xs = new int[_xs.length];
		int [] ys = new int[_ys.length];
		_xPos = _cp.getWidth() - 60;
		_yPos = 5;
		
		for(int k = 0 ; k < xs.length ; k++){
			xs[k]=_xPos+_xs[k]*_scale;
			ys[k]=_yPos+_ys[k]*_scale;
		}
		_plusButton = new java.awt.Polygon(xs,ys,xs.length);
	}
	
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e){
		java.awt.Point p = e.getPoint();
		if(_plusButton.contains(p))
			_slf.loadFromFile();
	}
	
	@Override
	public void paint(java.awt.Graphics aBrush){
		java.awt.Color oldColor = aBrush.getColor();
		java.awt.Graphics2D betterBrush = (java.awt.Graphics2D) aBrush;
		this.draw_p();
		betterBrush.setColor(_c);
		betterBrush.fillPolygon(_plusButton);
		betterBrush.setColor(oldColor);
		aBrush.setFont(_font);
		aBrush.setColor(_fontColor);
		aBrush.drawString("L", _xPos + 5, _yPos + 11);
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