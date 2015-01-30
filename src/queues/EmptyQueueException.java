package queues;

public class EmptyQueueException extends Exception {

	public EmptyQueueException(){
		this("no message");
	}
	public EmptyQueueException(String s){
		System.err.println("EmptyQueueException "+s);
	}
}
