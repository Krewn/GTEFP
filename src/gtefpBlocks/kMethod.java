
package gtefpBlocks;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import gtefpMain.WorkspacePanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import util.JavaFile;
import util.kVec;

public class kMethod extends Closure implements Serializable
{
	private static final long serialVersionUID = 1L;
	private kVar _pMethod;
	private kVar _name;
	private Socket _after, _parameter;
	//private JavaFile _jf;
	
	public kMethod(WorkspacePanel wp) 
	{
		super(wp);
		_pMethod = new kVar(wp,"protected ");
		_pMethod.setCp(this);
		_pMethod.setEditable(false);
		_name = new kVar(wp, "method(");
		_name.setCp(this);
		_name.setEditable(true);
		//_curly.setCp(this);
		_curly.setText("){");
		_parameter = new Socket(wp);
		_parameter.setCp(this);
		_parameter.setRel(_pMethod.width() + _name.width(),0);
		_after = new Socket(wp);
		_after.setCp(this);
		_after.setRel(0,_pMethod.ySize()+_inside.ySize());
		_curly.setRel(_pMethod.width()+_name.width(),0);
		_c = new java.awt.Color(245, 126, 8);
		
		_code.que(_pMethod); _code.que(_name); _code.que(_parameter); _code.que(_curly); _code.que(_inside); _code.que(_ylruc);
		
	
	}

	@Override
	public void unplug(){
		super.unplug();
		setCp(this);
	}
	
	public void setCp(CodePiece cp)
	{
		super.setCp(cp);
		_curly.setRel(_pMethod.width()+_name.width() + _parameter.width(),0);
		_pMethod.setRel(0,0);
		_name.setRel(0, _pMethod.width());
		_parameter.setRel(0, _pMethod.width() + _name.width());
	}
	
	//public void move(int dx,int dy){
		//super.move(dx, dy);
		//_name.setRel();
		//_pMethod.setRel();
		//_parameter.setRel();
	//}
	
	@Override
	public void paint(java.awt.Graphics aBrush)
	{
		super.paint(aBrush);
		_name.paint(aBrush);
		_pMethod.paint(aBrush);
		_after.paint(aBrush);
		_parameter.paint(aBrush);
		_curly.paint(aBrush);
		//_curly.setRel(_pMethod.width() + _name.width(), 0);
	}
	

	@Override
	public void draw_p()
	{
		int[] w = new int[]{(_curly.width() +_pMethod.width()+_name.width() + _parameter.width()), 8, _ylruc.width()};
		int[] h = new int[]{_curly.ySize(),(_curly.ySize()+_inside.ySize()),(_curly.ySize()+_inside.ySize()+_ylruc.ySize())};
		super.draw_p(w,h);
		_curly.setRel(_pMethod.width() + _name.width() + _parameter.width(),0);
		_name.setRel(_pMethod.width(), 0);
		_pMethod.setRel();
		_parameter.setRel(_pMethod.width() + _name.width(), 0);
		_after.setRel(0,_pMethod.ySize()+_inside.ySize()+_ylruc.ySize());
	}
	
	public String getName()
	{
		return _name.getText();
	}
	
	@Override
	public void mousePressed(MouseEvent e) 
	{
		_lastMouseLoc = e.getPoint();
		if(_p.contains(_lastMouseLoc)){
			_selected=true;
			if(_isButton)
			{
				kMethod _temp = new kMethod(_wp);
				_temp.setCp(_temp);
				_temp.place(_xPos,_yPos);
				_temp.setRel(0,0);
				_temp.select();
				_temp.mousePressed(e);
				_wp.setTemp(_temp);
				_wp.repaint();
				_temp.draw_p();
				_wp.repaint();
			}
		}
	}
	
	//public void setJavaFile(JavaFile jf)
	//{
		//_jf = jf;
	//}
	
	@Override
	public int width() 
	{
		return 0;
	}
	
	private void readObject(java.io.ObjectInputStream in) throws ClassNotFoundException, IOException
	{
		_c=(Color)in.readObject();
		java.util.ArrayList<Object> _temp = new ArrayList<Object>();
		_code = new kVec<Maluable>();
		for(Object k:_temp){
			_code.que((Maluable) k);
		}
		_code=(kVec<Maluable>)in.readObject();
		_cp=(CodePiece)in.readObject();
		_isButton=(boolean)in.readObject();
		_lastMouseLoc=(java.awt.Point)in.readObject();
		_p=(CompPoly)in.readObject();
		_scale=(int)in.readObject();
		_selected=(boolean)in.readObject();
		_sized=(boolean)in.readObject();
		_wp=(WorkspacePanel)in.readObject();
		_xPos=(int)in.readObject();
		_xRel=(int)in.readObject();
		_xs=(int[])in.readObject();
		_yPos=(int)in.readObject();
		_yRel=(int)in.readObject();
		_ys=(int[])in.readObject();
		_yWidth=(int)in.readObject();
		_wp.addMouseListener(this);
		_wp.addMouseMotionListener(this);
		_curly=(kVar)in.readObject();
		_inside=(Socket)in.readObject();
		_ylruc=(kVar)in.readObject();
		_pMethod = (kVar)in.readObject();
		_name = (kVar)in.readObject();
		//_jf = (JavaFile)in.readObject();
		_parameter=(Socket)in.readObject();
		_after=(Socket)in.readObject();
	}
	
	private void writeObject(java.io.ObjectOutputStream out) throws IOException
	{
		out.writeObject(_c);
		java.util.ArrayList<Object> _temp = new java.util.ArrayList<Object>();
		for(Maluable k : _code){_temp.add(k);}
		out.writeObject(_code);
		out.writeObject(_cp);
		out.writeObject(_isButton);
		out.writeObject(_lastMouseLoc);
		out.writeObject(_p);
		out.writeObject(_scale);
		out.writeObject(_selected);
		out.writeObject(_sized);
		out.writeObject(_wp);
		out.writeObject(_xPos);
		out.writeObject(_xRel);
		out.writeObject(_xs);
		out.writeObject(_yPos);
		out.writeObject(_yRel);
		out.writeObject(_ys);
		out.writeObject(_yWidth);
		out.writeObject(_curly);
		out.writeObject(_inside);
		out.writeObject(_ylruc);
		out.writeObject(_pMethod);
		out.writeObject(_name);
		//out.writeObject(_jf);
		out.writeObject(_parameter);
		out.writeObject(_after);
	}
	
	/*
	@Override
	public void mouseReleased(java.awt.event.MouseEvent e)
	{
		if(_selected){
			java.awt.geom.Area testa = new java.awt.geom.Area(_p);
			Socket q = null;
			for(Socket s: _wp.sockets()){
				if(s!=this._after && s!= this._parameter){
					java.awt.geom.Area a =new java.awt.geom.Area(s._p);
					a.intersect(testa);
					if (!a.isEmpty()){
						q=s;
					}
				}
			}
			if(q!=null){
				q.insert(this);
		}
		}
	}
	*/

	public Socket getAfter() {
		return _after;
	}	
}


