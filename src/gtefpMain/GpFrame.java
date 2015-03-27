package gtefpMain;

import gtefpBlocks.*;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.*;

public class GpFrame extends JFrame implements Serializable
{
	private static final long serialVersionUID = 1L;
	private ClassesPanel   _Cpanel;
	private WorkspacePanel _Wpanel;
	
	public GpFrame(String title)
	{
		super(title);
		
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
		this.setSize(900,750);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE); 
		
		_Wpanel = new WorkspacePanel(this);
		_Wpanel.setPreferredSize(new Dimension(780, 800));  // hardCoded sizing
		_Wpanel.setMaximumSize(new Dimension(3000, 3000));  // hardCoded sizing
		_Wpanel.setMinimumSize(new Dimension(100, 42));     // hardCoded sizing
		
		_Cpanel = _Wpanel.getClassesPanel();
		_Cpanel.setPreferredSize(new Dimension(780,30));    // hardCoded sizing
		_Cpanel.setMaximumSize(new Dimension(3000, 300));   // hardCoded sizing
		_Cpanel.setMinimumSize(new Dimension(100, 20));     // hardCoded sizing
		_Cpanel.setApp(_Wpanel.getApp());
		
		_Wpanel.setCp(_Cpanel);
		
		this.setPreferredSize(new Dimension(950, 800));
		this.add(_Cpanel);
		this.add(_Wpanel);
		
		_Wpanel.setup();
		
		this.repaint();
		this.setVisible(true);
	}
	
	public void makeSaveAndLoadButton(SaveLoadFrame slf)
	{
		_Cpanel.makeSaveAndLoadButton(slf);
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