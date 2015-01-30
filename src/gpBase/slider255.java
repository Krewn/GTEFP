package gpBase;
public class slider255 extends javax.swing.event.MouseInputAdapter{
	private java.awt.Point _lastMouseLoc;
	private int[] _xh;
	private int[] _yh;
	private int[] _xi;
	private int[] _yi;
	private java.awt.Polygon _handle;
	private java.awt.Polygon _indicator;
	private javax.swing.JPanel _panel;
	private colorPicker _cp;
	private int ref,val;
	slider255(colorPicker cp,javax.swing.JPanel pan){
		super();
		_panel=pan;
		_xh = new int [] {0,10,10,0}; ref=_xh[0];
		_yh = new int [] {0,0,4,4};
		_xi = new int [] {0,0,0,0};
		_yi = new int [] {0,0,1,1};
		_panel.addMouseListener(this);
		_panel.addMouseMotionListener(this);
		_handle = new java.awt.Polygon(_xh,_yh,4);
		_indicator = new java.awt.Polygon(_xi,_yi,4);
		_lastMouseLoc = new java.awt.Point();
		_cp=cp;
		_panel.repaint();
	}
	slider255(int x,int y,colorPicker cp,javax.swing.JPanel pan){
		super();
		_panel=pan;
		_xh = new int [] {0+x,10+x,10+x,0+x}; ref=_xh[0];
		_yh = new int [] {0+y,0+y,10+y,10+y};
		_xi = new int [] {0+x,0+x,0+x,0+x};
		_yi = new int [] {0+y,0+y,10+y,10+y};
		
		_panel.addMouseListener(this);
		_panel.addMouseMotionListener(this);
		_handle = new java.awt.Polygon(_xh,_yh,4);
		_indicator = new java.awt.Polygon(_xi,_yi,4);
		_lastMouseLoc = new java.awt.Point();
		_cp=cp;
		_panel.repaint();
	}
	public void mouseDragged(java.awt.event.MouseEvent m){ 
		if(_handle.contains(_lastMouseLoc)){
			int dx = m.getX()-_lastMouseLoc.x;
			int dy = m.getY()-_lastMouseLoc.y;
			if (dx+_xh[0]>=ref && dx+_xh[0]<ref+255){
				this.moveX(dx);
			}
			_lastMouseLoc = m.getPoint();
		}
		_panel.repaint();
	}
	public void mousePressed(java.awt.event.MouseEvent m){
		_lastMouseLoc = m.getPoint();
		
	}
	public void moveX(int dx){
		for(int k=0;k<_xh.length;k++){
			_xh[k]+=dx;
			if(k == 1 || k == 2){
				_xi[k]+=dx;
			}
		}
		_handle = new java.awt.Polygon(_xh,_yh,4);
		_indicator = new java.awt.Polygon(_xi,_yi,4);
		val = (_xh[0]-ref);
		_cp.refreshColor();
	}
	public int getVal(){
		int ret = val;
		return(ret);
	}
	public void setVal(int V){
		this.moveX(V-val);
		val = V;
		_panel.repaint();
	}
	public void paint(java.awt.Graphics aBrush,java.awt.Color C){
		java.awt.Color oldColor = aBrush.getColor();
		java.awt.Graphics2D betterBrush = (java.awt.Graphics2D) aBrush;
		betterBrush.setColor(C);
		betterBrush.fillPolygon(_indicator);
		betterBrush.setColor(new java.awt.Color(0,0,0));
		betterBrush.fillPolygon(_handle);
		betterBrush.setColor(oldColor);
	}
}
