package com.yicj.demo.inner2;

import static com.yicj.demo.CommonUtil.print; 

interface Incrementable {
	void increment() ;
}

class MyIncrement{
	public void increment() {
		print("Ohter operation");
	}
	static void f(MyIncrement mi) {
		mi.increment();
	}
}

//Very simple to just implment the interface:
class Callee1 implements Incrementable{
	private int i = 0 ;
	@Override
	public void increment() {
		i++ ;
		print(i);
	}
}

// If your class must implement increment() in 
// some other war. you must use an innser class:
class Callee2 extends MyIncrement{
	private int i = 0 ;
	@Override
	public void increment() {
		super.increment();
		i ++ ;
		print(i);
	}
	private class Closure implements Incrementable{
		@Override
		public void increment() {
			// Specify outer-class method, otherwise
			// you'd get an infinite recursion:
			Callee2.this.increment();
		}
	}
	Incrementable getCallbackReference() {
		return new Closure() ;
	}
}

class Caller{
	private Incrementable callbackReference ;
	Caller(Incrementable cbh) {
		callbackReference = cbh ;
	}
	void go() {
		callbackReference.increment();
	}
}

public class Callbacks {

	public static void main(String[] args) {
		Callee1 c1 = new Callee1() ;
		Callee2 c2 = new Callee2() ;
		MyIncrement.f(c2);
		Caller caller1 = new Caller(c1) ;
		Caller caller2 = new Caller(c2.getCallbackReference()) ;
		caller1.go(); 
		caller1.go(); 
		caller2.go();
		caller2.go();
	}
}
