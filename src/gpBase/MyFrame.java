package gpBase;
public class MyFrame extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyPanel _panel;
	public MyFrame(String Title){
		super(Title);
		this.setSize(700,350);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE); 
		_panel = new MyPanel();
		this.add(_panel);
		this.setVisible(true);
		this.repaint();
	}
	public static void main (String [ ] args) { 
		new MyFrame ("Trucks!");
	}
}