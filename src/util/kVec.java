package util;

import java.util.*;
//Add a different implementation of LIFOQ
public class kVec<T> extends Vector<T>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int front;int back;
	public kVec(){super();front=0;back=0;
	}
	public boolean que(T a){
		this.ensureCapacity(1);
		this.insertElementAt(a,back);back++;
		return(true);
	}
	public boolean cut(T a){
		this.ensureCapacity(1);
		this.insertElementAt(a,front);
		back++;
		return(true);
	}
	public T lifoPop(){
		T temp=this.elementAt(back-1);
		this.removeElementAt(back-1);
		back--;
		return(temp);
	}
	public T lifoPeek(){
		return(this.elementAt(back-1));
	}
	public T fifoPop(){
		T temp=this.elementAt(front);
		this.removeElementAt(front);
		return(temp);
	}
	public T fifoPeek(){
		return(this.elementAt(front));
	}
	public void flush(){
		this.removeAllElements();
	}
	@Override
	public boolean remove(Object o){
		boolean b = super.remove(o);
		back--;
		return(b);
	}
	public void setCapacity(int i){/*pass*/}
	public int size()
	{
		return back - front;
	}
}