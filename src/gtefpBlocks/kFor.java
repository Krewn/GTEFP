package gtefpBlocks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import util.kVec;
import gtefpMain.WorkspacePanel;

public class kFor extends kWhile implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private kVar _pFor, _sc1, _sc2;
	private Socket _eval1, _eval2, _eval3, _after;
	
	public kFor(WorkspacePanel wp)
	{
		super(wp);
		_pWhile.setText("for(");
		_c = new java.awt.Color(40, 170, 31);
				
		/*_pFor = new kVar(wp, "for(");
		_pFor.setEditable(false);
		_pFor.setCp(this);
		_eval1 = new Socket(wp);
		_eval1.setCp(this);
		_eval1.setRel(_pFor.width(),0);
		_sc1 = new kVar(wp, "; ");
		_sc1.setEditable(false);
		_sc1.setCp(this);
		_eval2 = new Socket(wp);
		_eval2.setCp(this);
		_eval2.setRel(_pFor.width()+_eval1.width()+_sc1.width(),0);
		_sc2 = new kVar(wp, "; ");
		_sc2.setEditable(false);
		_sc2.setCp(this);
		_eval3 = new Socket(wp);
		_eval3.setCp(this);
		_eval3.setRel(_pFor.width()+_eval1.width()+_sc1.width()+_eval2.width()+_sc2.width(),0);
		_after = new Socket(wp);
		_after.setCp(this);
		_after.setRel(0,_pFor.ySize()+_inside.ySize());*/
		
		_code.que(_pFor); _code.que(_eval1); _code.que(_sc1); _code.que(_eval2); _code.que(_sc2); _code.que(_eval3); _code.que(_curly); _code.que(_ylruc); _code.que(_after);
		//_code.fifoPop(); _code.fifoPop(); _code.fifoPop(); _code.fifoPop(); _code.fifoPop(); // Removes elements of _code that are unnecessary for kFor
		
		System.out.println("debug");
	}
	
	/*@Override
	public void draw_p()
	{
		int[] w = new int[]{_curly.width() + _pWhile.width() + _eval.width()+1, 8, _ylruc.width()};
		int[] h = new int[]{_curly.ySize(), _curly.ySize() + _inside.ySize(), _curly.ySize() + _inside.ySize() + _ylruc.ySize()};
		super.draw_p(w, h);
		_eval.setRel(_pWhile.width(),0);
		_curly.setRel(_pWhile.width()+_eval.width(),0);
		_pWhile.setRel();
		_after.setRel(0,_pWhile.ySize()+_inside.ySize()+_ylruc.ySize());
	}*/
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		_lastMouseLoc = e.getPoint();
		
		if (_p.contains(_lastMouseLoc))
		{
			_selected = true;
			
			if (_isButton)
			{
				kFor _temp = new kFor(_wp);
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
		_after=(Socket)in.readObject();
		_eval=(Socket)in.readObject();
		_pWhile=(kVar)in.readObject();
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
		out.writeObject(_after);
		out.writeObject(_eval);
		out.writeObject(_pWhile);
	}
}