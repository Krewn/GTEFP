package gtefpBlocks;

import gtefpMain.WorkspacePanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import util.kVec;

public class kSwitch extends Closure implements Serializable
{
	private static final long serialVersionUID = 1L;
	private kVar _pSwitch;
	private Socket _eval; // soemthing has to be evaluated in the iff ... if(_name){_inside}
	private Socket _after;
	
	public kSwitch(WorkspacePanel wp)
	{
		super(wp);
		_pSwitch = new kVar(wp, "switch(");
		_pSwitch.setEditable(false);
		_pSwitch.setCp(this);
		_curly.setText("){");
		_eval = new Socket(wp);
		_eval.setCp(this);
		_eval.setRel(_pSwitch.width(),0);
		_after = new Socket(wp);
		_after.setCp(this);
		_after.setRel(0,_pSwitch.ySize()+_inside.ySize());
		_curly.setRel(_pSwitch.width() + _eval.width() , 0);
		_c= new java.awt.Color(210,110,230);
		_code.que(_pSwitch); _code.que(_eval); _code.que(_curly); _code.que(_ylruc); _code.que(_after);
	}
	@Override
	public void unplug(){
		super.unplug();
		if(_after.inUse()){
			_cp.insert(_after.getPlug());}
		_after.unsert();
		setCp(this);
	}
	@Override
	public void setCp(CodePiece cp)
	{
		super.setCp(cp);
		_curly.setRel(_pSwitch.width() + _eval.width(), 0);
		_pSwitch.setRel(0, 0);
		_eval.setRel(0, _pSwitch.width());
	}
	
	public void move(int dx, int dy)
	{
		super.move(dx, dy);
		_eval.setRel();
		_pSwitch.setRel();
		_curly.setRel();
		_inside.setRel();
		_after.setRel();
	}
	
	@Override
	public void paint(Graphics aBrush)
	{
		super.paint(aBrush);
		_pSwitch.paint(aBrush);
		_eval.paint(aBrush);
		_after.paint(aBrush);
		_curly.setRel(_pSwitch.width() + _eval.width(), 0); // No need to divide by scale 
	}
	
	@Override
	public void draw_p()
	{
		int[] w = new int[]{_curly.width() + _pSwitch.width() + _eval.width()+1, 8, _ylruc.width()};
		int[] h = new int[]{_curly.ySize(), _curly.ySize() + _inside.ySize(), _curly.ySize() + _inside.ySize() + _ylruc.ySize()};
		super.draw_p(w, h);
		_eval.setRel(_pSwitch.width(),0);
		_curly.setRel(_pSwitch.width()+_eval.width(),0);
		_pSwitch.setRel();
		_after.setRel(0,_pSwitch.ySize()+_inside.ySize()+_ylruc.ySize());
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		_lastMouseLoc = e.getPoint();
		
		if (_p.contains(_lastMouseLoc))
		{
			_selected = true;
			
			if (_isButton)
			{
				kSwitch _temp = new kSwitch(_wp);
				_temp.setCp(_temp);
				_temp.place(_xPos, _yPos);
				_temp.setRel(0, 0);
				_temp.select();
				_temp.mousePressed(e);
				_wp.setTemp(_temp);
				_wp.repaint();
				_temp.draw_p();
				_wp.repaint();
			}
		}
	}
	@Override
	public void mouseReleased(java.awt.event.MouseEvent e){
		if(_selected){
			java.awt.geom.Area testa = new java.awt.geom.Area(_p);
			Socket q = null;
			for(Socket s: _wp.sockets()){
				if(s!=this._after && s!= this._eval){
					java.awt.geom.Area a =new java.awt.geom.Area(s._p);
					a.intersect(testa);
					if (!a.isEmpty()){
						q=s;
					}
				}
			}//Had to do this q nonsense to avoid current modification error in the prior for loop.
			if(q!=null){
				q.insert(this);
			}
		}
		_selected=false;
		_wp.repaint();
	}
	@Override
	public int ySize(){
		return(super.ySize()+_after.ySize());
	}
	@Override
	public int width() {
		return 0;
	}
	
	public Socket getAfter()
	{
		return _after;
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
		_pSwitch=(kVar)in.readObject();
		_eval=(Socket)in.readObject();
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
		out.writeObject(_pSwitch);
		out.writeObject(_eval);
		out.writeObject(_after);

	}
}