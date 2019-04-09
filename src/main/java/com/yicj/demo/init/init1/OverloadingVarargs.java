package com.yicj.demo.init.init1;

public class OverloadingVarargs {

	static void f(Character ...args) {
		System.out.println("first");
		for(Character c :args)
			System.out.print(" " + c);
		System.out.println();
	}
	static void f(Integer ...args) {
		System.out.println("second");
		for(Integer i : args)
			System.out.print(" " + i);
		System.out.println();
	}
	static void f(Long ...args) {
		System.out.println("third");
	}
	public static void main(String[] args) {
		f('a','b','c') ;
		f(1) ;
		f(2,1) ;
		f(0) ;
		f(0L) ;
		//f() ;//won't compile --
	}
}
