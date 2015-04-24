/*************************************************************************
 * The SaveButton opens a dialog prompting the user for a file location. *
 * The current GpFrame is then saved to that location.                   *
 *************************************************************************/

package util;

import java.awt.Color;
import java.awt.Font;
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
	protected java.awt.Color _c, _fontColor;
	protected java.awt.Polygon _plusButton;
	protected SaveLoadFrame _slf;
	protected Font _font;
	
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
		_c = new java.awt.Color(0,0,255);
		_cp.addMouseListener(this);
		_cp.repaint();
		_font = new java.awt.Font("Arial", java.awt.Font.BOLD, 12);
		_fontColor = new Color(255, 0, 0);
	}
	
	public void draw_p(){
		int [] xs = new int[_xs.length];
		int [] ys = new int[_ys.length];
		_xPos = _cp.getWidth() - 40;
		_yPos = 5;
		
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
		aBrush.setFont(_font);
		aBrush.setColor(_fontColor);
		aBrush.drawString("S", _xPos + 3, _yPos + 12);
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