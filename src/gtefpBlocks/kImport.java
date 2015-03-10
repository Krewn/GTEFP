package gtefpBlocks;

import gtefpMain.WorkspacePanel;

public class kImport extends CodePiece{
	private kVar _package;
	protected kVar _import;
	private kVar _sc;
	public Socket _after;
	public kImport(WorkspacePanel wp) {
		super(wp);
		_c=new java.awt.Color(70,70,70);
		_import = new kVar(wp,"Import ");
		_import.setEditable(false);
		_import.setCp(this);
		_sc = new kVar(wp,";");
		_sc.setEditable(false);
		_sc.setCp(this);
		_package = new kVar(wp);
		_package.setEditable(true);
		_package.setCp(this);
		//This right here
		_code.que(_import);_code.que(_package);_code.que(_sc);
		//should make sense as the line of code " import package; "
		//                                          # socket
		_after = new Socket(wp);
		_after.place(_xPos,ySize());
		_after.setCp(this);
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
	public void setCp(CodePiece cp){
		_cp = cp;
		setRel(0,0);
		_import.setRel(0,0);
		_package.setRel(_import.width(),0);
		_sc.setRel(_import.width()+_package.width(),0);
	}
	@Override
	public int ySize(){
		return(_import.ySize()+_after.ySize());
	}
	@Override
	public void paint(java.awt.Graphics aBrush){
		draw_p();
		super.paint(aBrush);
		draw_p();
		//betterBrush.fillPolygon(_p);
		//betterBrush.setColor(oldColor);
		//super.paint(aBrush);
		_import.paint(aBrush,new java.awt.Color(175,120,120));
		_package.paint(aBrush);
		_sc.paint(aBrush,new java.awt.Color(175,120,120));
		_after.paint(aBrush);
	}
	@Override
	public void draw_p(){
		int l = _import.width();
		int h = ySize()-_after.ySize();
		//System.out.println(_import.width());
		_import.setRel();
		_package.setRel(_import.width(),0);
		_sc.setRel(_import.width()+_package.width(),0);
		_after.setRel(0,h);
		_xs = new int []{0,l,l,0};
		_ys = new int []{0,0,h,h};
		super.draw_p();
		_after.draw_p();
	}
	@Override
	public void move(int dx,int dy){
		super.move(dx, dy);
		_import.setRel();
		_package.setRel();
		_sc.setRel(_import.width()+_package.width(),0);
		_after.place(_xPos, _yPos+this.ySize());
		draw_p();
	}
	@Override
	public void mousePressed(java.awt.event.MouseEvent e){
		_lastMouseLoc = e.getPoint();
		if(_p.contains(_lastMouseLoc)){
			_selected=true;
			if(_isButton){
				kImport _temp = new kImport(_wp);
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
	@Override
	public void mouseReleased(java.awt.event.MouseEvent e){
		if(_selected){
			java.awt.geom.Area testa = new java.awt.geom.Area(_p);
			Socket q = null;
			for(Socket s: _wp.sockets()){
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
	public void clicked(){}
	@Override
	public int width() {
		// TODO Auto-generated method stub
		return 0;
	}
}
