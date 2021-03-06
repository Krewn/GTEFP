package Testing;

import junit.framework.TestCase;
import gtefpBlocks.kIf;
import gtefpMain.WorkspacePanel;

public class IfTestCase extends TestCase
{
	private kIf            _if;
	private WorkspacePanel _wp;
	
	public void testIfCreation() throws Exception
	{
		_if = new kIf(_wp); // _if is created -- test will pass.
		assertNotNull(_if);
	}
	
	public void testIfLocation() throws Exception
	{
		_if = new kIf(_wp);
		assertEquals(_if.getXPos(), 0);
		assertEquals(_if.getXPos(), 0); // _if initiates at position 0, 0 -- test will pass.
	}
	
	public void testIfMovement() throws Exception
	{
		_if = new kIf(_wp);
		_if.move(25, 125);
		assertEquals(_if.getXPos(), 25); // _if is moved to position 25, 125 -- test will pass.
		assertEquals(_if.getYPos(), 125);
	}
}