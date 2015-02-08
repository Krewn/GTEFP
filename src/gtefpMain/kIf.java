package gtefpMain;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class kIf extends Closure
{
	private kVar _pIf;
	private kVar _name;
	
	public kIf(WorkspacePanel wp)
	{
		super(wp);
		_pIf = new kVar(wp, "if");
		_pIf.setEditable(false);
		_pIf.setCp(this);
		_name = new kVar(wp, "");
		_name.setCp(this);
		_code.cut(_pIf);
		_code.cut(_name);
		_curly.setRel(_pIf.width() + _name.width(), 0);
	}
	
	@Override
	public void setCp(CodePiece cp)
	{
		super.setCp(cp);
		_curly.setRel(_pIf.width() + _name.width(), 0);
		_pIf.setRel(0, 0);
		_name.setRel(0, _pIf.width());
	}
	
	public void Move(int dx, int dy)
	{
		super.Move(dx, dy);
		_name.setRel();
		_pIf.setRel();
	}
	
	@Override
	public void paint(Graphics aBrush)
	{
		super.paint(aBrush);
		_pIf.paint(aBrush);
		_name.paint(aBrush);
		_curly.setRel(_pIf.width() + _name.width(), 0);
	}
	
	@Override
	public void Draw_p()
	{
		int[] w = new int[]{(_curly.width() + _pIf.width() + _name.width()) / _scale, 16, _ylruc.width()};
		int[] h = new int[]{_curly.ysize() / _scale, (_curly.ysize() + _inside.ysize()) / _scale, (_curly.ysize() + _inside.ysize() + _ylruc.ysize()) / _scale};
		super.Draw_p(w, h);
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		_lastMouseLoc = e.getPoint();
		
		if (_p.contains(_lastMouseLoc))
		{
			_selected = true;
			
			if (_isButton)
			{
				kIf _temp = new kIf(_wp);
				_temp.setCp(_temp);
				_temp.Place(_xpos, _ypos);
				_temp.setRel(0, 0);
				_temp.select();
				_temp.mousePressed(e);
				_wp.setTemp(_temp);
				_wp.repaint();
				_temp.Draw_p();
				_wp.repaint();
			}
		}
	}
}