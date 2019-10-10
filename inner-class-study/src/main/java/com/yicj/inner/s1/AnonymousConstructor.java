package com.yicj.inner.s1;
import static com.yicj.common.util.CommonUtil.println;

abstract class Base{
	public Base(int i) {
		println("Base constructor. i = " + i);
	}
	public abstract void f() ;
}


public class AnonymousConstructor {
	public static Base getBase(int i) {
		return new Base(i) {
			{
				println("Inside instance initializer");
			}
			@Override
			public void f() {
				println("In anonymous f()");
			}
		};
	}
	
	public static void main(String[] args) {
		Base base = getBase(47) ;
		base.f();
	}
	
}
