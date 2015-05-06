package Testing;

import static org.junit.Assert.*;
import gtefpMain.GpFrame;
import gtefpMain.WorkspacePanel;

import org.junit.Test;

import junit.framework.TestCase;
import util.App;

public class NewAppTest extends TestCase{
	
	private WorkspacePanel _wp;
	private GpFrame _gpf;
	private boolean _testSave;
	
	@Test
	
	public void testNewApp(){
		_wp = new WorkspacePanel(_gpf);
		App newApp = new App(_wp);
		assertNotNull(newApp);		//if new app is created, test will pass
	}
	
	// tests that an App is successfully saved to a file
	public void testSaveToFile(){
		_wp = new WorkspacePanel(_gpf);
		App newApp = new App(_wp);
		_testSave = newApp.saveToFile();
		assertEquals(_testSave, false);
	}
}