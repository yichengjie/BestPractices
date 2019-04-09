package com.yicj.demo.init.init1;
import static com.yicj.demo.util.CommonUtil.println;

class Cup{
	public Cup(int marker) {
		println("Cup ("+marker+")");
	}
	void f(int marker) {
		println("f("+marker+")");
	}
}

class Cups{
	static Cup cup1 ;
	static Cup cup2 ;
	static {
		cup1 = new Cup(1) ;
		cup2 = new Cup(2) ;
	}
	Cups() {
		println("Cups()");
	}
}


public class ExplictStatic {
	
	public static void main(String[] args) {
		println("Inside main()");
		//Cups.cup1.f(99);
	}
	static Cups cups1 = new Cups() ;
}
