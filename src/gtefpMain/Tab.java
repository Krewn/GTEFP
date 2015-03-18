package gtefpMain;

import java.awt.FontMetrics;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Polygon;
import javax.swing.event.MouseInputAdapter;
import util.App;
import util.JavaFile;

public class Tab extends MouseInputAdapter
{
	private App            _app;
	private Color          _c, _fontColor;
	private ClassesPanel   _cp;
	private JavaFile       _file;
	private Font           _font;
	private Point          _point;
	private Polygon        _poly;
	private boolean        _sized;
	private String         _style, _text;
	private WorkspacePanel _wp;
	private int            _x, _y, _w, _h, _Q;
	
	public Tab(ClassesPanel cp, JavaFile jf)
	{
		_cp = cp;
		_wp = _cp.getWorkspacePanel();
		_app = _wp.getApp();
		_Q = 10;
		_c = new java.awt.Color(255, 0, 0);
		_fontColor = new java.awt.Color(1, 1, 1);
		_file = jf;
		draw_p();
		_sized = false;
		_style = "Arial";
		_text = "tab";
		_cp.addMouseListener(this);
	}
	
	public void draw_p()
	{
		int cpw = _cp.getWidth() - 20;
		int i = _app.getIndexOfJavaFile(_file);
		_x = (cpw/_Q)*(i % _Q) + 5;
		_y = _h * (i / _Q) + 5;
		_w = (cpw - 40) / _Q;
		//System.out.println(_cp.getWidth());
		_h = _cp.getRowHeight() - 10;
		//System.out.println("x:"+_x+"\t y:"+_y+"\t h:"+_h+"\t w:"+_w );
		int[] xs = new int[]{_x, _x+_w, _x+_w, _x};
		int[] ys = new int[]{_y + 5, _y + 5, _y+_h, _y+_h};
		_poly = new Polygon(xs,ys,xs.length);
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		_point = e.getPoint();
		if (_poly.contains(_point))
			_wp.setCurrentWsClass(_app.getIndexOfJavaFile(_file));
	}
	
	public void paintComponent(java.awt.Graphics aBrush)
	{
		_text = _file.getClassName();
		
		if (_text.length() == 0)
			_text = "tab";
		
		this.draw_p();
		java.awt.Color oldColor = aBrush.getColor();
		java.awt.Graphics2D betterBrush = (java.awt.Graphics2D) aBrush;
		betterBrush.setColor(_c);
		betterBrush.setColor(_c);
		betterBrush.fillPolygon(_poly);
		betterBrush.setColor(oldColor);
		
		if(_sized==false)
			prefHeight(aBrush,_h);
		
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
}