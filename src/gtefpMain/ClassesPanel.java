package gtefpMain;
import java.awt.Dimension;

import util.App;
import util.JavaFile;
import util.NewClassButton;
import gpBase.kVec;

public class ClassesPanel extends javax.swing.JPanel{
	private int _nrows;
	private App _app;
	private NewClassButton _newClassButton;
	private kVec<Tab> _tabs;
	private WorkspacePanel _wp;
	private int _rowHeight;
	private int _Q;
	private GpFrame _frame;
	public ClassesPanel(WorkspacePanel wp, GpFrame frame){
		super();
		_nrows = 1;
		this.setBackground(new java.awt.Color(90,90,90));
		_newClassButton = new NewClassButton(this);
		_tabs = new kVec<Tab>();
		this.repaint();
		_wp = wp;
		_rowHeight = 30;
		_Q = 10;
		_frame = frame;
	}
	public ClassesPanel(App app, WorkspacePanel wp, GpFrame frame){
		super();
		_nrows = 1;
		_app = app;
		this.setBackground(new java.awt.Color(90,90,90));
		_newClassButton = new NewClassButton(this);
		_tabs = new kVec<Tab>();
		this.repaint();
		_wp = wp;
		_rowHeight = 30;
		_Q = 10;
		_frame = frame;
	}
	public void paintComponent (java.awt.Graphics aBrush){
		System.out.println("\n");
		this.setPreferredSize(new Dimension(780, 30 * ((_tabs.size() / _Q) + 1)));
		//this.setSize(new Dimension(this.getWidth(), 30 * ((_tabs.size() / _Q) + 1)));
		//this.revalidate();
		//_frame.pack();
		_frame.getContentPane().revalidate();
		super.paintComponent(aBrush);
		_newClassButton.paint(aBrush);
		
		for (Tab t : _tabs)
			t.paintComponent(aBrush);
	}
	public void newClass() {
		_app.newJavaFile();
	}
	public void setApp(App a)
	{
		_app = a;
	}
	
	public WorkspacePanel getWorkspacePanel()
	{
		return _wp;
	}
	
	public void newTab(JavaFile jf)
	{
		_tabs.que(new Tab(this, jf));
	}
	
	public int getRowHeight()
	{
		return _rowHeight;
	}
	
	/*@Override
	public int getWidth()
	{
		return _width;
	}*/
	
	public void drawTabs()
	{
		for (Tab t : _tabs)
			t.draw_p();
	}
}
