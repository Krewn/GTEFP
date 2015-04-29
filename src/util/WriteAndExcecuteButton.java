package util;

import java.io.IOException;

import gtefpMain.ClassesPanel;
import gtefpMain.GpFrame;

public class WriteAndExcecuteButton extends NewClassButton {

	/**
	 * This button runs the program created in GTEFP  
	 */
	private static final long serialVersionUID = 1L;
	
	public WriteAndExcecuteButton(ClassesPanel cp) {
		super(cp);
		_xs = new int[]{0,2,0};
		_ys = new int[]{0,1,2,};
		draw_p();
		// TODO Auto-generated constructor stub
	}
	
	public void draw_p(){
		int [] xs = new int[_xs.length];
		int [] ys = new int[_ys.length];
		_xPos = _cp.getWidth() - (_scale * 16);
		for(int k = 0 ; k < xs.length ; k++){
			xs[k]=_xPos+_xs[k]*_scale;
			ys[k]=_yPos+_ys[k]*_scale;
		}
		_plusButton = new java.awt.Polygon(xs,ys,xs.length);
	}
	
	public void mouseClicked(java.awt.event.MouseEvent e){
		java.awt.Point p = e.getPoint();
		if(_plusButton.contains(p)){
			_cp.WriteAndRun();
		}
	}
	
	private void readObject(java.io.ObjectInputStream in) throws ClassNotFoundException, IOException
	{
		in.defaultReadObject();
	}
	
	private void writeObject(java.io.ObjectOutputStream out) throws IOException
	{
		out.defaultWriteObject();
	}

}
