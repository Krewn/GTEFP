package gtefpBlocks;

import java.awt.Color;
import java.awt.Polygon;

public interface Maluable {
	
	public void draw_p();
	public void move(int dx,int dy);
	public void place(int x, int y);
	public void scale(int s);
}
