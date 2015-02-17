package gtefpBlocks;

import gtefpMain.WorkspacePanel;

import java.awt.event.MouseEvent;

public class kclass extends Closure{
	private kVar _pClass;
	private kVar _name;
	public kclass(WorkspacePanel wp){
		super(wp);
		_pClass = new kVar(wp,"public class");
		_pClass.setEditable(false);
		_pClass.setCp(this);
		_name = new kVar(wp,"");
		_name.setCp(this);
		_code.cut(_pClass);
		_code.cut(_name);
		_curly.setRel(_pClass.width()+_name.width(),0);
	}
	@Override
	public void unplug(){
		super.unplug();
		setCp(this);
	}
	@Override
	public void setCp(CodePiece cp){
		super.setCp(cp);
		_curly.setRel(_pClass.width()+_name.width(),0);
		_pClass.setRel(0,0);
		_name.setRel(0,_pClass.width());
	}
	public void move(int dx,int dy){
		super.move(dx, dy);
		_name.setRel();
		_pClass.setRel();
	}
	@Override
	public void paint(java.awt.Graphics aBrush){
		super.paint(aBrush);
		_pClass.paint(aBrush);
		_name.paint(aBrush);
		_curly.setRel(_pClass.width()+_name.width(),0);
	}
	@Override
	public void draw_p(){
		int[] w = new int[]{(_curly.width()+_pClass.width()+_name.width()),8 , _ylruc.width()};
		int[] h = new int[]{_curly.ySize(),(_curly.ySize()+_inside.ySize()),(_curly.ySize()+_inside.ySize()+_ylruc.ySize())};
		super.draw_p(w,h);
	}
	@Override
	public void mousePressed(java.awt.event.MouseEvent e){
		_lastMouseLoc = e.getPoint();
		if(_p.contains(_lastMouseLoc)){
			_selected=true;
			if(_isButton){
				kclass _temp = new kclass(_wp);
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
	public int width() {
		// TODO Auto-generated method stub
		return 0;
	}
}