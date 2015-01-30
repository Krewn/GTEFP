package queues;

import java.util.LinkedList;

public class LIFOQImpl<E> implements LIFOQ<E>{//This instance of a queue is implemented with a java.util.LinkedList

	LinkedList<E> _llist;
	int _capacity;
	int _nContents;

	public LIFOQImpl(){
		_llist = new LinkedList<E>();
		_capacity = 10;
		_nContents = 0;
	}

	public boolean push(E item){
		if (_nContents<_capacity){
			_llist.add(item);
			_nContents++;
			return true;  
		}
		else {
			return false;
		}
	}
	public E pop() throws EmptyQueueException{
		if(_nContents>0){
			_nContents--;
			return(_llist.get(_nContents));
		}
		else {
			throw  new EmptyQueueException("LIFOQImpl::pop from empty");
			 
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
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E top() throws EmptyQueueException {
		// TODO Auto-generated method stub
		return null;
	}
}
