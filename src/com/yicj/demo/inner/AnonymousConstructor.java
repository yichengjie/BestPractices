package com.yicj.demo.inner;
import static com.yicj.demo.CommonUtil.print; 

abstract class Base{
	public Base(int i) {
		print("Base constructor. i = " + i);
	}
	public abstract void f() ;
}


public class AnonymousConstructor {
	public static Base getBase(int i) {
		return new Base(i) {
			{
				print("Inside instance initializer");
			}
			@Override
			public void f() {
				print("In anonymous f()");
			}
		};
	}
	
	public static void main(String[] args) {
		Base base = getBase(47) ;
		base.f();
	}
	
}
