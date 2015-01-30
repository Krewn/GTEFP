import java.awt.Color;

public class colorPicker implements java.awt.event.ActionListener{
	private java.awt.Color _c;
	private slider255 _R;
	private slider255 _G;
	private slider255 _B;
	private Swatch _swatch;
	private javax.swing.JPanel _panel;
	colorPicker(javax.swing.JPanel pan){
		_panel = pan;
		_R = new slider255(10,10,this,_panel);
		_G = new slider255(10,25,this,_panel);
		_B = new slider255(10,40,this,_panel);
		_panel.addMouseListener(_R);
		_panel.addMouseListener(_G);
		_panel.addMouseListener(_B);
		_swatch = new Swatch(265,10);
		_swatch.setColor(new java.awt.Color(_R.getVal(),_G.getVal(),_B.getVal()));
	}
	public void refreshColor(){
		_swatch.setColor(new java.awt.Color(_R.getVal(),_G.getVal(),_B.getVal()));
		_panel.repaint();
	}
	public void refreshColor(java.awt.Color C){
		_swatch.setColor(C);
		_R.setVal(C.getRed());
		_G.setVal(C.getGreen());
		_B.setVal(C.getBlue());
		_panel.repaint();
	}
	public java.awt.Color fetchColor(){
		return(_swatch.getColor());
	}
	public void paint(java.awt.Graphics aBrush){
		java.awt.Color oldColor = aBrush.getColor();
		java.awt.Color C = _swatch.getColor();
		java.awt.Graphics2D betterBrush = (java.awt.Graphics2D) aBrush;
		_swatch.paint(aBrush);
		_R.paint(aBrush,new java.awt.Color(C.getRed(),0,0));
		_G.paint(aBrush,new java.awt.Color(0,C.getGreen(),0));
		_B.paint(aBrush, new java.awt.Color(0,0,C.getBlue()));
		aBrush.setColor(oldColor);
	}
	public void actionPerformed (java.awt.event.ActionEvent e) {
		refreshColor(new java.awt.Color(0,0,0));
	}
}