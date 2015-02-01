package gtefpMain;
import java.awt.Polygon;
import java.awt.Color;
public abstract class CodePiece extends javax.swing.event.MouseInputAdapter implements Maluable{
	protected int _xpos;
	protected int _ypos;
	protected Polygon _p;
	protected Color _c;
	protected int[] _xs;
	protected int[] _ys;
	protected int _scale;
	private String [] _Code;
	private int[] _LineHeights;
	private App _App;
	private WorkspacePanel _wp;
	public CodePiece(WorkspacePanel wp){
		_wp=wp;
		_c = new Color(255,255,255);
		_scale = 5;
	}
	public void paint(java.awt.Graphics aBrush){
		java.awt.Color oldColor = aBrush.getColor();
		java.awt.Graphics2D betterBrush = (java.awt.Graphics2D) aBrush;
		System.out.println("get here");
		betterBrush.setColor(_c);
		this.Draw_p();
		System.out.println("get 2");
		betterBrush.fillPolygon(_p);
		betterBrush.setColor(oldColor);
	}
	public void setposition(int x,int y){
		_xpos = x; _ypos=y;
	}
	public void Draw_p(){
		System.out.println("!! The here tho");
		int [] xs = new int[_xs.length];
		int [] ys = new int[_ys.length];
		for(int k = 0 ; k < xs.length ; k++){
			xs[k]=_xpos+_xs[k]*_scale;
			ys[k]=_ypos+_ys[k]*_scale;
		}
		_p = new Polygon(xs,ys,xs.length);
	}
}
