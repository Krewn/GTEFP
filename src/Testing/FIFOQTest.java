package Testing;

import org.junit.Test;

import queues.EmptyQueueException;
import queues.FIFOQImpl;
import queues.LIFOQImpl;
import junit.framework.TestCase;


public class FIFOQTest extends TestCase {

	private int _value1;
	private int _value2;
	private FIFOQImpl<Integer> _mq;
	private int _capacity;

	public FIFOQTest(String testName) {
		super(testName);
	}

	protected void setUp() throws Exception {
		super.setUp();
		System.out.println("FIFOQTest::setUp ");
		_value1 = 3;
		_value2 = 5;
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		System.out.println("FIFOQTest::tearDown ");
		_value1 = 0;
		_value2 = 0;
	}
	
	@Test
	public void testEnqueue() {
		_mq = new FIFOQImpl<Integer>();
		_mq.setCapacity(30);
		int capacity = _mq.getCapacity();
		assertEquals(30,capacity);
		boolean success = false;//this is not arbitrary, if the loop never runs violates assertion
		for (int p = 1; p<capacity; p++){
			success = _mq.enqueue(_value1);
			assertEquals(success, true);
		}
		assertEquals(success, true);
	}

	@Test
	public void testFailedEnqueue() {
		_mq = new FIFOQImpl<Integer>();
		_capacity = 30;
		_mq.setCapacity(_capacity);
		boolean success = true;//this is not arbitrary, if the loop never runs, violates assertion
		for (int p = 0; p<(_capacity+1); p++){
			success = _mq.enqueue(_value1);
			if (p<_capacity){
				assertEquals(success, true);
			}
			else {
				assertEquals(success, false);
			}
		}
		
	}
	
	@Test
	public void testDequeue() throws EmptyQueueException {
		_mq = new FIFOQImpl<Integer>();
		_capacity = 30;
		_mq.setCapacity(_capacity);
		System.out.println("FIFOQTest::testDequeue ");
		for (int p = 0; p<_capacity; p++){
			boolean success = _mq.enqueue(p);//first fill it up
			System.out.println("FIFOQTest::testDequeue "+ p);
			assertEquals(success, true);
		}
		for (int p = _capacity; p>0; p--){
			int q = (int) _mq.dequeue(); //then empty it out
			q=q+1;
			System.out.println("FIFOQTest::testDequeue "+ p + q);
			assertEquals(q, p);
		}
		 
	}
	
	@Test
	public void testFailedDequeue() throws EmptyQueueException {
		_mq = new FIFOQImpl<Integer>();
		_mq.flush();
		boolean empty = false;
		try{
			empty = (null ==_mq.dequeue());
		}catch(Exception e){
			System.out.println("FIFOQTest::testFailedDequeue: caught expected exception "+e);
		}
		 
	}


}
