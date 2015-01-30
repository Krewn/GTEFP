import java.awt.Color;
public class truck extends javax.swing.event.MouseInputAdapter{
	private java.awt.Point _lastMouseLoc;
	private int [] _x;
	private int [] _y;
	private java.awt.Polygon _truck;
	private java.awt.Color _C;
	private trailer _trailer;
	private javax.swing.JPanel _panel;
	private SmartEllipse _wheel1;
	private SmartEllipse _wheel2;
	private colorPicker _cp;
	
	truck(colorPicker cp,trailer Tr,javax.swing.JPanel pan){
		super();
		_cp = cp;
		_panel=pan;
		_x=new int[]{0,1,2,4,4,7,7,6,5,3,2,1,0};//base scaling for vector
		_y=new int[]{3,3,2,2,3,3,5,4,5,5,4,5,5};
		for(int k=0;k<_x.length;k++){_x[k]=_x[k]*10;_y[k]=_y[k]*10;}
		_truck =new java.awt.Polygon(_x,_y,13);
		_wheel1 = new SmartEllipse(new java.awt.Color(0,0,0));
		_wheel2 = new SmartEllipse(new java.awt.Color(0,0,0));
		_wheel1.setFrame(10, 40, 20, 20);
		_wheel2.setFrame(50, 40, 20, 20);
		_wheel1.setFillColor(new java.awt.Color(0,0,0));
		_wheel2.setFillColor(new java.awt.Color(0,0,0));
		_C=new java.awt.Color(255,255,255);
		_trailer=Tr;
		_lastMouseLoc = new java.awt.Point();
		_panel.addMouseListener(this);
		_panel.addMouseMotionListener(this);
		_panel.repaint();
	}
	truck(colorPicker cp,trailer Tr,javax.swing.JPanel pan,int x,int y){
		super();
		_cp=cp;
		_panel=pan;
		_x=new int[]{0,1,2,4,4,7,7,6,5,3,2,1,0};//base scaling for vector
		_y=new int[]{3,3,2,2,3,3,5,4,5,5,4,5,5};
		for(int k=0;k<_x.length;k++){_x[k]=_x[k]*10;_y[k]=_y[k]*10;}
		_trailer=Tr;
		_wheel1 = new SmartEllipse(new java.awt.Color(0,0,0));
		_wheel2 = new SmartEllipse(new java.awt.Color(0,0,0));
		_wheel1.setFrame(10, 40, 20, 20);
		_wheel2.setFrame(50, 40, 20, 20);
		_wheel1.setFillColor(new java.awt.Color(0,0,0));
		_wheel2.setFillColor(new java.awt.Color(0,0,0));
		_C=new java.awt.Color(255,255,255);
		move(x,y);
		_truck =new java.awt.Polygon(_x,_y,13);
		_lastMouseLoc = new java.awt.Point();
		_panel.addMouseListener(this);
		_panel.addMouseMotionListener(this);
		_panel.repaint();
	}
	public void move(int dx,int dy){
		for(int k=0;k<_x.length;k++){
			_x[k]+=dx;
			_y[k]+=dy;
		}
		_wheel1.setLocation(_wheel1.getX()+dx,_wheel1.getY()+dy);
		_wheel2.setLocation(_wheel2.getX()+dx,_wheel2.getY()+dy);
		_trailer.move(dx,dy);
		_truck =new java.awt.Polygon(_x,_y,13);
		_panel.repaint();
	}
	public void paint(java.awt.Graphics betterBrush){
		java.awt.Color oldColor = betterBrush.getColor();
		betterBrush.setColor(_C);
		betterBrush.fillPolygon(_truck);
		_wheel1.fill((java.awt.Graphics2D)betterBrush);
		_wheel2.fill((java.awt.Graphics2D)betterBrush);
		betterBrush.setColor(oldColor);
	}
	public void mousePressed(java.awt.event.MouseEvent e){
		_lastMouseLoc = e.getPoint();
	}
	public void mouseDragged(java.awt.event.MouseEvent e){
		if(_truck.contains(_lastMouseLoc)){
			int dx = e.getX()-_lastMouseLoc.x;
			int dy = e.getY()-_lastMouseLoc.y;
			move(dx,dy);
			_panel.repaint();
		}
		_lastMouseLoc = e.getPoint();
	}
	public void mouseClicked(java.awt.event.MouseEvent e){
		if(_truck.contains(_lastMouseLoc)){
			_C = _cp.fetchColor();
		}
		_panel.repaint();
	}
	java.awt.Color getColor(){
		java.awt.Color C = _C;
		return(C);
	}

}
