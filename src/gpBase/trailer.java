package gpBase;
public class trailer extends javax.swing.event.MouseInputAdapter{
	private java.awt.Point _lastMouseLoc;
	private int[] _x;
	private int[] _y;
	private java.awt.Polygon _trailer;
	private java.awt.Color _C;
	private javax.swing.JPanel _panel;
	private SmartEllipse _wheel;
	private colorPicker _cp;
	
	trailer(colorPicker cp,javax.swing.JPanel pan){
		super();
		_cp = cp;
		_panel=pan;
		_x=new int[] {0,1,1,4,4,0};//base scaling for vector
		_y=new int[] {4,3,2,2,4,4};
		_wheel = new SmartEllipse(new java.awt.Color(0,0,0));
		_wheel.setFrame(80,35,20,20);
		_wheel.setFillColor(new java.awt.Color(0,0,0));
		move(7,0);
		for(int k=0;k<_x.length;k++){_x[k]=_x[k]*10;_y[k]=_y[k]*10;}
		_trailer = new java.awt.Polygon(_x,_y,6);
		_C = new java.awt.Color(100,100,100);
		_panel.addMouseListener(this);
		_panel.addMouseMotionListener(this);
		_lastMouseLoc = new java.awt.Point();
		_panel.repaint();
	}
	trailer(colorPicker cp,javax.swing.JPanel pan,int x, int y){
		super();
		_cp = cp;
		_panel=pan;
		_x=new int[] {0,1,1,4,4,0};//base scaling for vector
		_y=new int[] {4,3,2,2,4,4};
		_wheel = new SmartEllipse(new java.awt.Color(0,0,0));
		_wheel.setFrame(30,40,20,20);
		_wheel.setFillColor(new java.awt.Color(0,0,0));
		move(7,0);
		for(int k=0;k<_x.length;k++){_x[k]=_x[k]*10;_y[k]=_y[k]*10;}
		move(x,y);
		_trailer = new java.awt.Polygon(_x,_y,6);
		_C = new java.awt.Color(100,100,100);
		_panel.addMouseListener(this);
		_panel.addMouseMotionListener(this);
		_lastMouseLoc = new java.awt.Point();
		_panel.repaint();
	}
	public void move(int dx, int dy){
		for(int k=0;k<_x.length;k++){
			_x[k]+=dx;
			_y[k]+=dy;
		}
		_wheel.setLocation(_wheel.getX()+dx, _wheel.getY()+dy);
		_trailer=new java.awt.Polygon(_x,_y,6);
	}

	public void paint(java.awt.Graphics aBrush){
		java.awt.Color oldColor = aBrush.getColor();
		java.awt.Graphics2D betterBrush = (java.awt.Graphics2D) aBrush;
		betterBrush.setColor(_C);
		betterBrush.fillPolygon(_trailer);
		_wheel.fill((java.awt.Graphics2D)betterBrush);
		aBrush.setColor(oldColor);
	}
	public void mousePressed(java.awt.event.MouseEvent e){
		_lastMouseLoc = e.getPoint();
	}
	public void mouseDragged(java.awt.event.MouseEvent e){
		if(_trailer.contains(_lastMouseLoc)){
			int dx = e.getX()-_lastMouseLoc.x;
			int dy = e.getY()-_lastMouseLoc.y;
			move(dx,dy);
			_panel.repaint();
		}
		_lastMouseLoc = e.getPoint();
	}
	public void mouseClicked(java.awt.event.MouseEvent e){
		if(_trailer.contains(_lastMouseLoc)){
			_C = _cp.fetchColor();
		}
		_panel.repaint();
	}
	public java.awt.Color getColor(){
		java.awt.Color C = _C;
		return(C);
	}
}
