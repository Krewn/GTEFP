package gtefpMain;

public class kImport extends CodePiece{
	private kVar _package;
	private kVar _import;
	kVar _sc;
	private CodePiece _after;
	private CodePiece _cp;
	public kImport(WorkspacePanel wp) {
		super(wp);
		_c=new java.awt.Color(0,0,0);
		_import = new kVar(wp,"Import ");
		_import.setEditable(false);
		_import.setCp(this);
		_sc = new kVar(wp,";");
		_sc.setEditable(false);
		_sc.setCp(this);
		_import.setCp(this);
		_package = new kVar(wp);
		_package.setEditable(false);
		_package.setCp(this);
		_after = new Socket(wp);
		_after.Place(_xpos,ysize());
	}
	public void setCp(CodePiece cp){
		_cp = cp;
		setRel(0,0);
		_import.setRel(0,0);
		_package.setRel(_import.width(),0);
		_sc.setRel(_import.width()+_package.width(),0);
	}
	@Override
	public int ysize(){
		return(4*_scale+_after.ysize());
	}
	public void setRel(int x, int y){
		_xrel = x;_yrel = y;
		_xpos = _cp._xpos+_xrel;
		_ypos = _cp._ypos+_yrel;
	}public void setRel(){
		_xpos = _cp._xpos+_xrel;
		_ypos = _cp._ypos+_yrel;
	}
	@Override
	public void paint(java.awt.Graphics aBrush){
		Draw_p();
		super.paint(aBrush);
		//betterBrush.fillPolygon(_p);
		//betterBrush.setColor(oldColor);
		//super.paint(aBrush);
		_import.paint(aBrush);
		_package.paint(aBrush);
		_sc.paint(aBrush);
		_after.paint(aBrush);
	}
	@Override
	public void Draw_p(){
		int l = _import.width()+_package.width();
		//System.out.println(_import.width());
		_import.setRel();
		_package.setRel(_import.width(),0);
		_sc.setRel(_import.width()+_package.width(),0);
		_after.Place(_xpos,_ypos+ysize()-_after.ysize());
		_xs = new int []{0,l/_scale,l/_scale,0};
		_ys = new int []{0,0,4,4};
		super.Draw_p();
		_after.Draw_p();
	}
	@Override
	public void Move(int dx,int dy){
		super.Move(dx, dy);
		_import.setRel();
		_package.setRel();
		_sc.setRel(_import.width()+_package.width(),0);
		_after.Place(_xpos, _ypos+this.ysize());
		Draw_p();
	}
	@Override
	public void mousePressed(java.awt.event.MouseEvent e){
		_lastMouseLoc = e.getPoint();
		if(_p.contains(_lastMouseLoc)){
			_selected=true;
			if(_isButton){
				System.out.println("kimprt mousePressed");
				System.out.println("test");
				kImport _temp = new kImport(_wp);
				_temp.setCp(_temp);
				_temp.Place(_xpos,_ypos);
				_temp.setRel(0,0);
				_temp.select();
				_temp.mousePressed(e);
				_wp.setTemp(_temp);
				_wp.repaint();
				_temp.Draw_p();
				_wp.repaint();
			}
		}
	}
	@Override
	public void mouseDragged(java.awt.event.MouseEvent e){
		if(_selected){
			if(_p.contains(_lastMouseLoc)){
				int dx = e.getX()-_lastMouseLoc.x;
				int dy = e.getY()-_lastMouseLoc.y;
				Move(dx,dy);
				_wp.repaint();
			}
		}
		_lastMouseLoc = e.getPoint();
	}
	public void mouseReleased(java.awt.event.MouseEvent e){
		if(_selected){
			java.awt.geom.Area testa = new java.awt.geom.Area(_p);
			Socket q = null;
			for(Socket s: _wp._sockets){
				if(s!=this._after){
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
	public void clicked(){
		
	}
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e){
		if(_p.contains(_lastMouseLoc)){
			clicked();
		}
		_wp.repaint();
	}
	

}
