/**
 * 
 */
package queues;

/**
 * @author tms08012
 *
 */
public interface LIFOQ<E> {
	/**
	 * Returns the number of elements in the queue
	 * @return number of elements in the queue
	 */
	public int size();
	/** 
	 * Returns whether the queue is empty
	 * @return true if the queue is empty, false otherwise.
	 */
	public boolean isEmpty();
	/**
	 * Inspects the element at the top of the queue.
	 * @return elements at the top of the queue
	 * @exception EmptyQueueException if the queue is empty.
	 */
	public E top() throws EmptyQueueException;
	/**
	 * Inserts an elements at the top of the queue.
	 * @param element new element to be inserted/
	 */
	public boolean push(E element);
	/**
	 * Removes the element at the top of the queue.
	 * @return element removed.
	 * @exception EmptyQueueException if the queue is empty
	 */
	public E pop() throws EmptyQueueException;
}
