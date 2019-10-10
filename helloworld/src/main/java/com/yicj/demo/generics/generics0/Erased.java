package com.yicj.demo.generics.generics0;

public class Erased<T> {
	
	private static final int SIZE = 100 ;
	public  void f(Object arg) {
		//if(arg instanceof T) {}	 //Error
		//T var = new T() ;			 //Error
		//T [] array = new T[SIZE] ; //Error
		T [] array = (T[])new Object[SIZE] ; // unchecked warning
	}
}
