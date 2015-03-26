package queues;

public class EmptyQueueException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public EmptyQueueException(){
		this("no message");
	}
	public EmptyQueueException(String s){
		System.err.println("EmptyQueueException "+s);
	}
}
