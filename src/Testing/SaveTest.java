package Testing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import util.App;
import gtefpMain.*;
public class SaveTest {
	//Do not use any classes in junit.framework or junit.extensions
	GpFrame _TestFrame;
	App _TestApp;
	/**
	 * Tests for {@link Foo}.
	 *
	 * @author user@example.com (John Doe)
	 */
	//Usually, tests with JUnit4 do not need to extend anything
		
	// tests that a GpFrame is saved to a file
	@Test
	public void save() {
	_TestFrame = new GpFrame("This is just a Test.");
	boolean t = _TestApp.saveToFile();
	assertEquals(t , true);
	}

	@Test
	@Ignore
	public void thisIsIgnored() {
	}
}