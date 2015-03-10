package gtefpMain;
import util.App;
import util.NewClassButton;
import gpBase.kVec;

public class ClassesPanel extends javax.swing.JPanel{
	private int _nrows;
	private App _app;
	private NewClassButton _newClassButton;
	private kVec<Tab> _tabs;
	private WorkspacePanel _wp;
	private int _rowHeight;
	public ClassesPanel(WorkspacePanel wp){
		super();
		_nrows = 1;
		//this.addMouseListener(_trailer);
		this.setBackground(new java.awt.Color(90,90,90));
		_newClassButton = new NewClassButton(this);
		_tabs = new kVec<Tab>();
		this.repaint();
		_wp = wp;
		_rowHeight = 30;
	}
	public ClassesPanel(App app, WorkspacePanel wp){
		super();
		_nrows = 1;
		//this.addMouseListener(_trailer);
		_app = app;
		this.setBackground(new java.awt.Color(90,90,90));
		_newClassButton = new NewClassButton(this);
		_tabs = new kVec<Tab>();
		this.repaint();
		_wp = wp;
		_rowHeight = 30;
	}
	public void paintComponent (java.awt.Graphics aBrush){
		super.paintComponent(aBrush);
		_newClassButton.paint(aBrush);
		
		for (Tab t : _tabs)
			t.paintComponent(aBrush);
	}
	public void newClass() {
		_app.NewJavaFile();
	}
	public void setApp(App a)
	{
		_app = a;
	}
	
	public WorkspacePanel getWorkspacePanel()
	{
		return _wp;
	}
	
	public void newTab()
	{
		_tabs.que(new Tab(this));
	}
	
	public int getRowHeight()
	{
		return _rowHeight;
	}
	
	public void drawTabs()
	{
		for (Tab t : _tabs)
			t.draw_p();
	}
}
