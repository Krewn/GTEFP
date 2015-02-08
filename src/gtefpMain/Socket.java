package gtefpMain;

import java.awt.event.MouseEvent;

public class Socket extends CodePiece{
	private CodePiece _plug;
	private boolean _inUse;
	private int[] _OGxs;
	private int[] _OGys;
	private WorkspacePanel _wp;
	public Socket(WorkspacePanel wp){
		super(wp);
		wp.addSocket(this);
		kVec<Integer> I =  new kVec<Integer>();
		wp.addMouseListener(this);
		_wp = wp;
		_xpos = 0;
		_ypos = 0;
		_OGxs = new int[]{2,4,2,0,2,2,1,2,3,2};
		_OGys = new int[]{0,2,4,2,0,1,2,3,2,1};
		_scale = wp.getScale();
		_inUse = false;
	}
	public void insert(CodePiece plug){
		if(!_cp.isButton()){
			_plug = plug;
			_plug.setCp(this);
			_inUse = true;
			_wp.removeSocket(this);
			Draw_p();
		}
	}
	public CodePiece unsert(){
		_inUse = false;
		Draw_p();
		_wp.addSocket(this);
		return(_plug);
	}
	@Override
	public void paint(java.awt.Graphics aBrush){
		java.awt.Color oldColor = aBrush.getColor();
		java.awt.Graphics2D betterBrush = (java.awt.Graphics2D) aBrush;
		super.paint(aBrush);
		//System.out.print("paint Socket");
		if(_inUse){
			//System.out.print("paint Plug");
			_plug.paint(aBrush);
		}
	}
	@Override
	public void Draw_p(){
		if(_inUse==false){
			_xs=_OGxs;
			_ys=_OGys;
			super.Draw_p();
		}else{
			_xs=new int [] {0};
			_ys=new int [] {0};
			super.Draw_p();
			_plug.Place(_xpos, _ypos);
			_plug.Draw_p();
			//_p = new java.awt.Polygon({0},{0},1);
		}
	}
	@Override
	public int ysize() {
		if (_inUse){return (_plug.ysize());}else{return 4;}
	}
	@Override
	public void clicked() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setCp(CodePiece cp) {
		_cp=cp;
	}
	@Override
	public void mousePressed(java.awt.event.MouseEvent e){
		_lastMouseLoc = e.getPoint();
		if(_p.contains(_lastMouseLoc)){
			_selected=true;
			if(_isButton){
			}
		}
	}
}
