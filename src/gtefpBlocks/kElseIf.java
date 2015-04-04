package gtefpBlocks;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.Serializable;

import gtefpMain.WorkspacePanel;

public class kElseIf extends Closure implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Socket _after, _eval;
	private kVar   _pElseIf;
	
	public kElseIf(WorkspacePanel wp)
	{
		super(wp);
		_pElseIf = new kVar(wp, "else if(");
		_pElseIf.setEditable(false);
		_pElseIf.setCp(this);
		_eval = new Socket(wp);
		_eval.setCp(this);
		_eval.setRel(_pElseIf.width(),0);
		_after = new Socket(wp);
		_after.setCp(this);
		_after.setRel(0,_pElseIf.ySize()+_inside.ySize());
		_c = new java.awt.Color(200, 100, 40);
		_code.que(_pElseIf); _code.que(_eval); _code.que(_curly); _code.que(_ylruc); _code.que(_after);
	}
	
	@Override
	public void draw_p()
	{
		int[] w = new int[]{_curly.width() + _pElseIf.width() + _eval.width()+1, 8, _ylruc.width()};
		int[] h = new int[]{_curly.ySize(), _curly.ySize() + _inside.ySize(), _curly.ySize() + _inside.ySize() + _ylruc.ySize()};
		super.draw_p(w, h);
		_eval.setRel(_pElseIf.width(),0);
		_curly.setRel(_pElseIf.width()+_eval.width(),0);
		_pElseIf.setRel();
		_after.setRel(0,_pElseIf.ySize()+_inside.ySize()+_ylruc.ySize());
	}
	
	public Socket getAfter()
	{
		return _after;
	}
	
	@Override
	public void paint(Graphics aBrush)
	{
		super.paint(aBrush);
		_pElseIf.paint(aBrush);
		_eval.paint(aBrush);
		_after.paint(aBrush);
		_curly.setRel(_pElseIf.width() + _eval.width(), 0); // No need to divide by scale 
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
				kElseIf _temp = new kElseIf(_wp);
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
	public void mouseReleased(java.awt.event.MouseEvent e)
	{
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
	public void setCp(CodePiece cp)
	{
		super.setCp(cp);
		_curly.setRel(_pElseIf.width() + _eval.width(), 0);
		_pElseIf.setRel(0, 0);
		_eval.setRel(0, _pElseIf.width());
	}
	
	@Override
	public void unplug()
	{
		super.unplug();
		if(_after.inUse()){
			_cp.insert(_after.getPlug());}
		_after.unsert();
		setCp(this);
	}
	
	@Override
	public int width()
	{
		return 0;
	}
	
	@Override
	public int ySize(){
		return(super.ySize()+_after.ySize());
	}
	
	public void readObject(java.io.ObjectInputStream in) throws ClassNotFoundException, IOException
	{
		super.readObject(in);
		_after=(Socket)in.readObject();
		_eval=(Socket)in.readObject();
		_pElseIf=(kVar)in.readObject();
	}
	
	public void writeObject(java.io.ObjectOutputStream out) throws IOException
	{
		super.writeObject(out);
		out.writeObject(_after);
		out.writeObject(_eval);
		out.writeObject(_pElseIf);
	}
}