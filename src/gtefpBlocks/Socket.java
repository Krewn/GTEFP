package gtefpBlocks;

import gtefpMain.WorkspacePanel;

public class Socket extends CodePiece{
	private CodePiece _plug;
	private boolean _inUse;
	private int[] _OGxs;
	private int[] _OGys;
	private WorkspacePanel _wp;
	private boolean _dontDraw;
	public Socket(WorkspacePanel wp){
		super(wp);
		wp.addSocket(this);
		wp.addMouseListener(this);
		_wp = wp;
		_xPos = 0;
		_yPos = 0;
		_OGxs = new int[]{2,4,2,0,2,2,1,2,3,2};
		_OGys = new int[]{0,2,4,2,0,1,2,3,2,1};
		_scale = wp.getScale();
		_inUse = false;
		_dontDraw = false;
	}
	public void setDD(boolean b){
		_dontDraw = b;
	}
	public boolean inUse(){
		boolean b = _inUse;
		return(b);
	}
	public void setInUse(boolean b){
		_inUse=b;
	}
	@Override
	public void insert(CodePiece plug){
		if(!_cp.isButton()){
			_plug = plug;
			_plug.setCp(this);
			_inUse = true;
			_wp.removeSocket(this);
			draw_p();
			_code.que(_plug);
		}
	}
	@Override
	public void unsert(){
		_code.remove(_plug);
		_inUse = false;
		draw_p();
		_wp.addSocket(this);
	}
	public CodePiece getPlug(){
		CodePiece p=_plug;
		return (p);
	}
	@Override
	public void paint(java.awt.Graphics aBrush){
		draw_p();
		super.paint(aBrush);
		if(_inUse){
			_plug.paint(aBrush);
		}
	}
	@Override
	public void draw_p(){
		if(_inUse==false && _dontDraw == false ){
			_xs=_OGxs;
			_ys=_OGys;
			super.draw_p();
		}else{
			_xs=new int [] {0};
			_ys=new int [] {0};
			super.draw_p();
			if(_inUse==true){
				_plug.place(_xPos, _yPos);
				_plug.draw_p();
			}
			//_p = new java.awt.Polygon({0},{0},1);
		}
	}
	public int width(){
		if(_inUse){
			return(_plug.width());
		}
		return(4);
	}
	@Override
	public int ySize() {
		if (_inUse){
			return (_plug.ySize());
		}else{
			if(_dontDraw){
				return(0);
				}else{return 4;}
			}
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
	@Override
	public void unplug() {
		// Auto-generated method stub
	}
}
