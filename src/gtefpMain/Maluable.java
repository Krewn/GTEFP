package gtefpMain;

import java.awt.Color;
import java.awt.Polygon;

public interface Maluable {
	
	public void Draw_p();
	public void Move(int dx,int dy);
	public void Place(int x, int y);
	public void scale(int s);
}
