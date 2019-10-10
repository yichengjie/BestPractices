package com.yicj.init.s1;
import static com.yicj.study.common.util.CommonUtil.println;

class Mug{
	Mug(int marker) {
		println("Mug("+marker+")") ;
	}
	void f(int marker) {
		println("f("+marker+")") ;
	}
}


public class Mugs {
	
	Mug mug1 ;
	Mug mug2 ;
	{
		mug1 = new Mug(1) ;
		mug2 = new Mug(2) ;
		println("mug1 & mug2 initialized") ;
	}
	Mugs() {
		println("Mugs()");
	}
	Mugs(int i){
		println("Mugs(int)") ;
	}
	public static void main(String[] args) {
		println("Inside main()") ;
		new Mugs() ;
		println("new Mugs() completed") ;
		new Mugs(1) ;
		println("new Mugs(1) completed") ;
	}

}
