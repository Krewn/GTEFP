package gtefpMain;
import java.awt.Dimension;
import java.io.IOException;
import java.io.Serializable;

import util.App;
import util.JavaFile;
import util.LoadButton;
import util.NewClassButton;
import util.SaveButton;
import gpBase.kVec;

public class ClassesPanel extends javax.swing.JPanel implements Serializable{
	private static final long serialVersionUID = 1L;
	private int _nrows;
	private App _app;
	private NewClassButton _newClassButton;
	private kVec<Tab> _tabs;
	private WorkspacePanel _wp;
	private int _rowHeight;
	private int _Q;
	private GpFrame _frame;
	private SaveButton _saveButton;
	private LoadButton _loadButton;
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
		this.setPreferredSize(new Dimension(780, 30 * (((_tabs.size() - 1)/ _Q) + 1)));
		_frame.getContentPane().revalidate();
		super.paintComponent(aBrush);
		_newClassButton.paint(aBrush);
		
		if (_saveButton != null)
			_saveButton.paint(aBrush);
		
		if (_loadButton != null)
			_loadButton.paint(aBrush);
		
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
	
	public void drawTabs()
	{
		for (Tab t : _tabs)
			t.draw_p();
	}
	
	public void makeSaveAndLoadButton(SaveLoadFrame slf)
	{
		_saveButton = new SaveButton(this, slf);
		_loadButton = new LoadButton(this, slf);
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
