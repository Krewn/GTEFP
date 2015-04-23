/*****************************************************************************************
 * A Tab is a button representing a class that can be edited. Clicking on the Tab causes *
 * its class to be displayed to the user.                                                *
 *****************************************************************************************/

package gtefpMain;

import java.awt.FontMetrics;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Polygon;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.event.MouseInputAdapter;

import util.App;
import util.GlobalConsts;
import util.JavaFile;

import java.util.Random;

public class Tab extends MouseInputAdapter implements Serializable
{
	private static final long serialVersionUID = 1L;
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
	private int            _x, _y, _w, _h;
	private Random         _rand;
	
	public Tab(ClassesPanel cp, JavaFile jf)
	{
		_cp = cp;
		_wp = _cp.getWorkspacePanel();
		_app = _wp.getApp();
		_rand = new Random();
		int [] Col = new int[]{ran255(), ran255(), ran255()};
		_c = new java.awt.Color(Col[0], Col[1], Col[2]);
		_fontColor = new java.awt.Color(255-Col[0], 255-Col[1], 255-Col[2]);
		_file = jf;
		draw_p();
		_sized = false;
		_style = "Arial";
		_text = "tab";
		_cp.addMouseListener(this);
	}
	private int ran255(){
		return (int)(_rand.nextFloat()*255);
	}
	
	public void draw_p()
	{
		int cpw = _cp.getWidth() - 20;
		int i = _app.getIndexOfJavaFile(_file);
		_x = (cpw/GlobalConsts.TABS_PER_ROW)*(i % GlobalConsts.TABS_PER_ROW) + 5;
		_y = _h * (i / GlobalConsts.TABS_PER_ROW) + 5;
		_w = (cpw - 40) / GlobalConsts.TABS_PER_ROW;
		_h = _cp.getRowHeight() - 10;
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
		aBrush.setColor(_fontColor);
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
	
	private void readObject(java.io.ObjectInputStream in) throws ClassNotFoundException, IOException
	{
		_app=(App)in.readObject();
		_c=(Color)in.readObject();
		_fontColor=(Color)in.readObject();
		_cp=(ClassesPanel)in.readObject();
		_file=(JavaFile)in.readObject();
		_font=(Font)in.readObject();
		_point=(Point)in.readObject();
		_poly=(Polygon)in.readObject();
		_sized=(boolean)in.readObject();
		_style=(String)in.readObject();
		_text=(String)in.readObject();
		_wp=(WorkspacePanel)in.readObject();
		_x=(int)in.readObject();
		_y=(int)in.readObject();
		_w=(int)in.readObject();
		_h=(int)in.readObject();
		_rand=(Random)in.readObject();
	}
	
	private void writeObject(java.io.ObjectOutputStream out) throws IOException
	{
		out.writeObject(_app);
		out.writeObject(_c);
		out.writeObject(_fontColor);
		out.writeObject(_cp);
		out.writeObject(_file);
		out.writeObject(_font);
		out.writeObject(_point);
		out.writeObject(_poly);
		out.writeObject(_sized);
		out.writeObject(_style);
		out.writeObject(_text);
		out.writeObject(_wp);
		out.writeObject(_x);
		out.writeObject(_y);
		out.writeObject(_w);
		out.writeObject(_h);
		out.writeObject(_rand);
	}
}