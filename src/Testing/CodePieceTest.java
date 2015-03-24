package Testing;

import gtefpBlocks.Socket;
import gtefpBlocks.kClass;
import gtefpBlocks.kIf;
import gtefpMain.GpFrame;
import gtefpMain.WorkspacePanel;

import org.junit.Test;

import junit.framework.TestCase;

public class CodePieceTest extends TestCase
{
	@Test
	public void testForBracketMatching() throws Exception
	{
		GpFrame        gf      = new GpFrame("CodePieceTest");
		WorkspacePanel wp      = new WorkspacePanel(gf);
		Socket         socket  = new Socket(wp);
		kClass         kclass  = new kClass(wp);
		kIf            kif     = new kIf(wp);
		socket.insert(kclass); kclass.getInside().insert(kif);
		
		String s = socket.writeCode();
		
		int[] bracketCount = new int[8];
		
		for (int i = 0; i < s.length(); i++)
		{
			char c = s.charAt(i);
			
			if (c == '(')
				bracketCount[0]++;
			if (c == ')')
				bracketCount[1]++;
			if (c == '[')
				bracketCount[2]++;
			if (c == ']')
				bracketCount[3]++;
			if (c == '{')
				bracketCount[4]++;
			if (c == '}')
				bracketCount[5]++;
			if (c == '<')
				bracketCount[6]++;
			if (c == '>')
				bracketCount[7]++;
		}
		
		assertEquals(bracketCount[0], bracketCount[1]);
		assertEquals(bracketCount[2], bracketCount[3]);
		assertEquals(bracketCount[4], bracketCount[5]);
		assertEquals(bracketCount[6], bracketCount[7]);
	}
	
	@Test
	public void testForReturnType() throws Exception
	{
		GpFrame        gf = new GpFrame("CodePieceTest");
		WorkspacePanel wp = new WorkspacePanel(gf);
		kClass         c  = new kClass(wp);
		String         s  = c.writeCode();
		assertTrue(s instanceof String);
	}
	
	@Test
	public void testSocketsForCodePieces() throws Exception
	{
		GpFrame        gf      = new GpFrame("CodePieceTest");
		WorkspacePanel wp      = new WorkspacePanel(gf);
		Socket         socket  = new Socket(wp);
		kClass         kclass  = new kClass(wp);
		kIf            kif     = new kIf(wp);
		socket.insert(kclass); kclass.getInside().insert(kif);
		
		assertTrue(kif.isCodeCompleted());
	}
}
