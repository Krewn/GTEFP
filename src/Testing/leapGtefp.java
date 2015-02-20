package Testing;

import gtefpMain.GpFrame;
import leap.Sample;

import org.junit.Test;
import junit.framework.TestCase;
import static org.junit.Assert.*;

public class leapGtefp extends TestCase {
	@Test
	public void leap(){
		GpFrame F1 = new GpFrame ("Gtefp");
		boolean b = true;
		try {
			Sample S1 = new Sample();
        } catch (Exception e) {
            e.printStackTrace();
          	b = false;
        }
		assertEquals(b, true);
	}
	
}
