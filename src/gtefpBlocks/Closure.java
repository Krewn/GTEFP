/**************************************************************************
 * A Closure is a CodePiece that is necessarily enclosed by curly braces. *
 **************************************************************************/

package gtefpBlocks;

import java.awt.Color;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import util.kVec;
import gtefpMain.WorkspacePanel;

public abstract class Closure extends CodePiece implements Serializable{
	private static final long serialVersionUID = 1L;
	protected kVar _curly;// {
	protected Socket _inside; //    #
	protected kVar _ylruc;// }
	
	public Closure(WorkspacePanel wp) {
		super(wp);
		_curly = new kVar(wp,"{");
		_curly.setEditable(false);
		_curly.setCp(this);
		_inside=new Socket(wp);
		_inside.setCp(this);
		_ylruc = new kVar(wp,"}");
		_ylruc.setEditable(false);
		_ylruc.setCp(this);
	}
	
	@Override
	public void clicked(){
		
	}
	
	@Override
	public void draw_p(){
		int[] w = new int[]{_curly.width(),16,_ylruc.width()};
		int[] h = new int[]{_curly.ySize(),(_curly.ySize()+_inside.ySize()),(_curly.ySize()+_inside.ySize()+_ylruc.ySize())};
		_curly.setRel();
		_ylruc.setRel(0,ySize());
		_inside.place(_xPos+30,_yPos+_curly.ySize());
		_xs = new int []{0,w[0],w[0],w[1],w[1],w[2],w[2],0};
		_ys = new int []{0,0,h[0],h[0],h[1],h[1],h[2],h[2]};
		super.draw_p();
		_inside.draw_p();
	}
	
	public Socket getInside()
	{
		return _inside;
	}
	
	public void draw_p(int[] w, int[] h){
		_curly.setRel();
		_ylruc.setRel(0,_curly.ySize()+_inside.ySize());
		_inside.setRel(8,_curly.ySize());
		_xs = new int []{0,w[0],w[0],w[1],w[1],w[2],w[2],0};
		_ys = new int []{0,0,h[0],h[0],h[1],h[1],h[2],h[2]};
		super.draw_p();
		_inside.draw_p();
	}
	
	public void mouseReleased(java.awt.event.MouseEvent e){
		if(_selected){
			java.awt.geom.Area testa = new java.awt.geom.Area(_p);
			Socket q = null;
			for(Socket s: _wp.sockets()){
				if(s!=this._inside){
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
	
	public void move(int dx,int dy){
		super.move(dx, dy);
		_curly.setRel();
		_inside.setRel(8,_curly.ySize());
		_ylruc.setRel(0,_curly.ySize()+_inside.ySize());
		draw_p();
	}
	
	@Override
	public void paint(java.awt.Graphics aBrush){
		draw_p();
		super.paint(aBrush);
		draw_p();
		_curly.paint(aBrush);
		_ylruc.paint(aBrush);
		_inside.paint(aBrush);
	}
	
	@Override
	public void setCp(CodePiece cp){
		_cp = cp;
		setRel(0,0);
		draw_p();
	}
	
	@Override
	public int ySize(){
		return(_curly.ySize()+_inside.ySize()+_ylruc.ySize());
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
	}
	
}
