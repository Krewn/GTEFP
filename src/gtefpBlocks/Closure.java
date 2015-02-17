package gtefpBlocks;

import gtefpMain.WorkspacePanel;

public abstract class Closure extends CodePiece{
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
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void clicked(){
		
	}
	
	@Override
	public void draw_p(){
		int[] w = new int[]{_curly.width(),16,_ylruc.width()};
		int[] h = new int[]{_curly.ySize(),(_curly.ySize()+_inside.ySize()),(_curly.ySize()+_inside.ySize()+_ylruc.ySize())};
		//System.out.println(_import.width());
		_curly.setRel();
		_ylruc.setRel(0,ySize());
		_inside.place(_xPos+30,_yPos+_curly.ySize());
		_xs = new int []{0,w[0],w[0],w[1],w[1],w[2],w[2],0};
		_ys = new int []{0,0,h[0],h[0],h[1],h[1],h[2],h[2]};
		super.draw_p();
		_inside.draw_p();
	}
	
	public void draw_p(int[] w, int[] h){
		//int[] w = new int[]{_curly.width(),16,_ylruc.width()};
		//int[] h = new int[]{_curly.ysize(),_curly.ysize()+_inside.ysize(),_curly.ysize()+_inside.ysize()+_ylruc.ysize()};
		//System.out.println(_import.width());
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
			for(Socket s: _wp._sockets){
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
		//betterBrush.fillPolygon(_p);
		//betterBrush.setColor(oldColor);
		//super.paint(aBrush);
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
}
