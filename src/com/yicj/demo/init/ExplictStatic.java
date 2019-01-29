package com.yicj.demo.init;
import static com.yicj.demo.CommonUtil.print;

class Cup{
	public Cup(int marker) {
		print("Cup ("+marker+")");
	}
	void f(int marker) {
		print("f("+marker+")");
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
		print("Cups()");
	}
}


public class ExplictStatic {
	
	public static void main(String[] args) {
		print("Inside main()");
		//Cups.cup1.f(99);
	}
	static Cups cups1 = new Cups() ;
}
