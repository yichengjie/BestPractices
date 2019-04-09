package com.yicj.demo.generics.generics6;

import java.util.Iterator;
import java.util.LinkedList;

public class SimpleQueue<T>  implements Iterable<T>{
	
	private LinkedList<T> storage = new LinkedList<T>() ;
	public void add(T t) {
		storage.add(t) ;
	}
	public T get() {
		return storage.poll(); 
	}
	
	@Override
	public Iterator<T> iterator() {
		return storage.iterator();
	}


}
