/*******************************************************************************
 * Socket represents a hole in the code in which a CodePiece can be "plugged". *
 *******************************************************************************/

package gtefpBlocks;

import java.awt.Color;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import util.kVec;
import gtefpMain.WorkspacePanel;

public class Socket extends CodePiece implements Serializable
{
	private static final long serialVersionUID = 1L;
	private CodePiece _plug;
	private boolean _inUse;
	private int[] _OGxs;
	private int[] _OGys;
	private WorkspacePanel _wp;
	private boolean _dontDraw;
	
	public Socket() // necessary for deserialization
	{
		super();
	}
	
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
	
	public boolean getDD()
	{
		return _dontDraw;
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
	
	private void readObject(java.io.ObjectInputStream in) throws ClassNotFoundException, IOException
	{
		_c=(Color)in.readObject();
		java.util.ArrayList<Object> _temp = new ArrayList<Object>();
		_code = new kVec<Maluable>();
		for(Object k:_temp){
			_code.que((Maluable) k);
		}
		_code=(kVec<Maluable>)in.readObject();
		_cp=(CodePiece)in.readObject();
		_isButton=(boolean)in.readObject();
		_lastMouseLoc=(java.awt.Point)in.readObject();
		_p=(CompPoly)in.readObject();
		_scale=(int)in.readObject();
		_selected=(boolean)in.readObject();
		_sized=(boolean)in.readObject();
		_wp=(WorkspacePanel)in.readObject();
		_xPos=(int)in.readObject();
		_xRel=(int)in.readObject();
		_xs=(int[])in.readObject();
		_yPos=(int)in.readObject();
		_yRel=(int)in.readObject();
		_ys=(int[])in.readObject();
		_yWidth=(int)in.readObject();
		_wp.addMouseListener(this);
		_wp.addMouseMotionListener(this);
		_plug=(CodePiece)in.readObject();
		_inUse=(boolean)in.readObject();
		_OGxs=(int[])in.readObject();
		_OGys=(int[])in.readObject();
		_wp=(WorkspacePanel)in.readObject();
		_dontDraw=(boolean)in.readObject();
	}

	private void writeObject(java.io.ObjectOutputStream out) throws IOException
	{
		out.writeObject(_c);
		java.util.ArrayList<Object> _temp = new java.util.ArrayList<Object>();
		for(Maluable k : _code){_temp.add(k);}
		//out.writeObject(_temp);
		out.writeObject(_code);
		out.writeObject(_cp);
		out.writeObject(_isButton);
		out.writeObject(_lastMouseLoc);
		out.writeObject(_p);
		out.writeObject(_scale);
		out.writeObject(_selected);
		out.writeObject(_sized);
		out.writeObject(_wp);
		out.writeObject(_xPos);
		out.writeObject(_xRel);
		out.writeObject(_xs);
		out.writeObject(_yPos);
		out.writeObject(_yRel);
		out.writeObject(_ys);
		out.writeObject(_yWidth);
		out.writeObject(_plug);
		out.writeObject(_inUse);
		out.writeObject(_OGxs);
		out.writeObject(_OGys);
		out.writeObject(_wp);
		out.writeObject(_dontDraw);
	}
}