/***************************************************************************************
 * ClassesPanel maintains Tabs for each class that the user is currently working with. *
 * ClassesPanel allows the user to switch between classes while coding.                *
 ***************************************************************************************/

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
		 _nrows = (int) in.readObject();
		 _app = (App) in.readObject();
		 _newClassButton = (NewClassButton) in.readObject();
		 java.util.ArrayList<Tab> temp = (java.util.ArrayList<Tab>) in.readObject();
		 _tabs = new kVec<Tab>();
		 for( Object k : temp ){
			 _tabs.que((Tab)k);
		 } 
		 _wp = (WorkspacePanel) in.readObject();
		 _rowHeight = (int) in.readObject();
		 _Q = (int) in.readObject();
		 _frame = (GpFrame) in.readObject();
		 _saveButton = (SaveButton) in.readObject();
		 _loadButton = (LoadButton) in.readObject();
	}
	
	private void writeObject(java.io.ObjectOutputStream out) throws IOException
	{
		out.writeObject(_nrows);         //private int ;
		out.writeObject(_app);           //private App ;
		out.writeObject(_newClassButton);//private NewClassButton ;
		java.util.ArrayList<Tab> temp = new java.util.ArrayList<Tab>();
		for(Tab k : _tabs){
			temp.add(k);
		}
		out.writeObject(temp);
		out.writeObject(_wp);            //private WorkspacePanel ;
		out.writeObject(_rowHeight);     //private int ;
		out.writeObject(_Q);             //private int ;
		out.writeObject(_frame);         //private GpFrame ;
		out.writeObject(_saveButton);    //private SaveButton ;
		out.writeObject(_loadButton);    //private LoadButton ;
		
	}
}
