package gtefpMain;

public class Socket extends CodePiece{
	private CodePiece _plug;
	private boolean _inUse;
	public Socket(WorkspacePanel wp){
		super(wp);
		wp.addMouseListener(this);
		_xpos = wp._trayWidth;
		_ypos = 0;
		_xs = new int[]{2,4,2,0,2,2,1,2,3,2};
		_ys = new int[]{0,2,4,2,0,1,2,3,2,1};
		_scale = 20;
		_inUse = false;
	}
	public void insert(CodePiece plug){
		_plug = plug;
		_inUse = true;
	}
	public CodePiece unsert(CodePiece plug){
		_inUse = false;
		return(_plug);
	}
	@Override
	public void Draw_p(){
		if(_inUse==false){
			super.Draw_p();
		}else{
			_plug.Draw_p();
			//_p = new java.awt.Polygon({0},{0},1);
		}
	}
	@Override
	public void Move(int dx, int dy) {
		_xpos+=dx;
		_ypos+=dy;		
	}
	@Override
	public void Place(int x, int y) {
		_xpos=x;
		_ypos=y;		
	}
	@Override
	public void scale(int s) {
		_scale=s;
		this.Draw_p();
		// TODO Auto-generated method stub
	}
}
