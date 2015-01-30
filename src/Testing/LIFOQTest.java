package Testing;

import org.junit.Test;

import queues.EmptyQueueException;
import queues.FIFOQImpl;
import queues.LIFOQImpl;
import junit.framework.TestCase;


public class LIFOQTest extends TestCase {

	private int _value1;
	private int _value2;
	private LIFOQImpl<Integer> _ms;
	private int _capacity;

	public LIFOQTest(String testName) {
		super(testName);
	}

	protected void setUp() throws Exception {
		super.setUp();
		System.out.println("LIFOQTest::setUp ");
		_value1 = 3;
		_value2 = 5;
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		System.out.println("LIFOQTest::tearDown ");
		_value1 = 0;
		_value2 = 0;
	}

	@Test
	public void testPush() {
		_ms = new LIFOQImpl<Integer>();
		_ms.setCapacity(30);
		int capacity = _ms.getCapacity();
		assertEquals(30,capacity);
		boolean success = false;//this is not arbitrary, if the loop never runs violates assertion
		for (int p = 0; p<capacity; p++){
			success = _ms.push(p);
			assertEquals(success, true);
		}
		assertEquals(success, true);
	}

	@Test
	public void testFailedPush() {
		_ms = new LIFOQImpl<Integer>();
		_capacity = 30;
		_ms.setCapacity(_capacity);
		boolean success = true;//this is not arbitrary, if the loop never runs, violates assertion
		for (int p = 0; p<_capacity; p++){
			success = _ms.push(_value1);
			assertEquals(success, true);
		}
		success = _ms.push(_value1);
		assertEquals(success, false);
	}

	@Test
	public void testPop() throws EmptyQueueException {
		_ms = new LIFOQImpl<Integer>();
		_capacity = 30;
		_ms.setCapacity(_capacity);
		for (int p = 1; p<_capacity+1; p++){
			boolean success = _ms.push(p);//first fill it up
			assertEquals(success, true);
		}
		for (int p = _capacity; p>0; p--){
			int q = (int) _ms.pop(); //then empty it out
			assertEquals(q, p);
		}
	}

	@Test
	public void testFailedPop() throws EmptyQueueException {
		_ms = new LIFOQImpl<Integer>();
		int capacity = _ms.getCapacity();
		for (int p = 0; p<_capacity; p++){
			boolean success = _ms.push(p);//first fill it up
			assertEquals(success, true);
		}
		for (int p = _capacity-1; p>0; p--){
			try{
				int q = (int) _ms.pop();//then empty it out
				assertEquals(q, p);
			}catch(Exception e){
				System.out.println("LIFOQTest::testFailedPop: caught unexpected exception "+e + p);
			}
		}
		boolean empty = false;
		try{
			empty = (null ==_ms.pop());
		}catch(Exception e){
			System.out.println("LIFOQTest::testFailedPop: caught expected exception "+e);
		}
		
	}


}
