package com.yicj.generics.lostinfomation;

class Manipulator<T> {
	private T obj ;
	public Manipulator(T x) {
		this.obj = x ;
	}
	//Error: cannot find symbol:method f();
	public void manipulate() {
		//obj.f() ;
	}
}

class Manipulator2<T extends HasF> {
	private T obj ;
	public Manipulator2(T x) {
		this.obj = x ;
	}
	public void manipulate() {
		obj.f();
	}
}

class Manipulator3{
	private HasF obj ;
	public Manipulator3(HasF x) {
		this.obj = x ;
	}
	public void manipulate() {
		obj.f();
	}
}


class ReturnGenericType<T extends HasF>{
	private T obj ;
	public ReturnGenericType(T x) {
		this.obj = x ;
	}
	public T get() {
		return obj ;
	}
}

public class Manipulation{
	public static void main(String[] args) {
		HasF hf = new HasF() ;
		int a =1 ;
		int b =2 ;
		Manipulator<HasF> manipulator =
				new Manipulator<HasF>(hf) ;
		manipulator.manipulate();
	}
}
