package com.yicj.demo.init;
import static com.yicj.demo.CommonUtil.println;
class Window{
	public Window(int maker) {
		println("Window("+maker+")") ;
	}
}

class House{
	Window w1 = new Window(1) ; // Before constructor
	public House() {
		println("House");
		w3 = new Window(33) ;
	}
	Window w2 = new Window(2) ;
	void f() {
		println("f()");
	}
	Window w3 = new Window(3) ;
}



public class OrderOfInitialization {
	public static void main(String[] args) {
		House h = new House() ;
		h.f();
	}
}
