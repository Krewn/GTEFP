package gtefpMain;

import java.awt.FontMetrics;
import java.awt.event.MouseEvent;

import util.App;
import util.JavaFile;

public class Tab extends javax.swing.event.MouseInputAdapter
{
	private JavaFile _jf;
	private WorkspacePanel _wp;
	private ClassesPanel _cp;
	private App _app;
	private String _style, _text;
	private java.awt.Font _font;
	private java.awt.Polygon _poly;
	private java.awt.Point _point;
	private java.awt.Color _c, _fontColor;
	private int _x, _y, _w, _h, _Q;
	private boolean _sized;
	
	public Tab(ClassesPanel cp)
	{
		_cp = cp;
		_wp = _cp.getWorkspacePanel();
		_app = _wp.getApp();
		_Q = 10;
		_c = new java.awt.Color(255, 255, 255);
		_fontColor = new java.awt.Color(0, 0, 0);
		draw_p();
		_sized = false;
		_style = "Arial";
	}
	
	public void draw_p()
	{
		int i = _app.indexOfJavaFile(_jf);
		
		_x = _cp.getWidth()/(i % _Q) + 5;
		_y = _h * (i % _Q) + 5;
		_w = (_cp.getWidth() - 40) / _Q;
		_h = _cp.getRowHeight() - 10;
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		_point = e.getPoint();
		if (_poly.contains(_point))
			_wp.setCurrentWsClass(_app.indexOfJavaFile(_jf));
	}
	
	public void paintComponent(java.awt.Graphics aBrush)
	{
		java.awt.Color oldColor = aBrush.getColor();
		java.awt.Graphics2D betterBrush = (java.awt.Graphics2D) aBrush;
		betterBrush.setColor(_c);
		betterBrush.setColor(_c);
		betterBrush.fillPolygon(_poly);
		betterBrush.setColor(oldColor);
		
		if(_sized==false){
			prefHeight(aBrush,_h);
		}
		
		aBrush.drawString(_text,  _x , _y + _h);
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
		}
		metrics = g.getFontMetrics(_font);
		_sized = true;
	}
	
	public void resize()
	{
		
	}
}