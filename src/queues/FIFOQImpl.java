package queues;

import java.util.LinkedList;

public class FIFOQImpl<E> {//This instance of a queue is implemented with a java.util.LinkedList

	LinkedList<E> _llist;
	int _capacity;
	int _nContents;

	public FIFOQImpl(){
		_llist = new LinkedList<E>();
		_capacity = 10;
		_nContents = 0;
	}

	public boolean enqueue(E item){
		if (_nContents<_capacity){
			_llist.add(item);
			_nContents++;
			return true;  
		}
		else {
			return false;
		}
	}
	public E dequeue() throws EmptyQueueException{
		if(_nContents>0){
			_nContents--;
			return(_llist.get(_nContents));
		}
		else {
			throw  new EmptyQueueException("FIFOQImpl::dequeue from empty");	 
		}	
	}
	public int getCapacity(){
		return _capacity;
	}
	public void setCapacity(int c){
		_capacity = c;
	}
	public void flush(){
		_llist.clear();
	    _nContents = 0;	
	}
}
