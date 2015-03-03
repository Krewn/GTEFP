package gtefpBlocks;

import gtefpMain.WorkspacePanel;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class kIf extends Closure
{
	private kVar _pIf;
	private Socket _eval; // soemthing has to be evaluated in the iff ... if(_name){_inside}
	public Socket _after;// add socket after because abstract class closure does not have one.
	public kIf(WorkspacePanel wp)
	{
		super(wp);
		_pIf = new kVar(wp, "if(");
		_pIf.setEditable(false);
		_pIf.setCp(this);
		_curly.setText("){");
		_eval = new Socket(wp);
		_eval.setCp(this);
		_eval.setRel(_pIf.width(),0);
		//_code.cut(_pIf);
		_after = new Socket(wp);
		_after.setCp(this);
		_after.setRel(0,_pIf.ySize()+_inside.ySize());
		_curly.setRel(_pIf.width() + _eval.width() , 0);
		_c= new java.awt.Color(210,210,230);
		_code.que(_pIf); _code.que(_eval); _code.que(_curly); _code.que(_ylruc); _code.que(_after);
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
		_curly.setRel(_pIf.width() + _eval.width(), 0);
		_pIf.setRel(0, 0);
		_eval.setRel(0, _pIf.width());
	}
	
	public void move(int dx, int dy)
	{
		super.move(dx, dy);
		_eval.setRel();
		_pIf.setRel();
		_curly.setRel();
		_inside.setRel();
		_after.setRel();
	}
	
	@Override
	public void paint(Graphics aBrush)
	{
		super.paint(aBrush);
		_pIf.paint(aBrush);
		_eval.paint(aBrush);
		_after.paint(aBrush);
		_curly.setRel(_pIf.width() + _eval.width(), 0); // No need to divide by scale 
	}
	
	@Override
	public void draw_p()
	{
		int[] w = new int[]{_curly.width() + _pIf.width() + _eval.width()+1, 8, _ylruc.width()};
		int[] h = new int[]{_curly.ySize(), _curly.ySize() + _inside.ySize(), _curly.ySize() + _inside.ySize() + _ylruc.ySize()};
		super.draw_p(w, h);
		_eval.setRel(_pIf.width(),0);
		_curly.setRel(_pIf.width()+_eval.width(),0);
		_pIf.setRel();
		_after.setRel(0,_pIf.ySize()+_inside.ySize()+_ylruc.ySize());
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
				kIf _temp = new kIf(_wp);
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
		// TODO Auto-generated method stub
		return 0;
	}
}