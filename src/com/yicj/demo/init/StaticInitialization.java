package com.yicj.demo.init;

import static com.yicj.demo.CommonUtil.println;

class Bowl{
	public Bowl(int marker) {
		println("Bowl("+marker+")");
	}
	void f1(int marker) {
		println("f1("+marker+")");
	}
}

class Table{
	static Bowl bowl1 = new Bowl(1) ;
	public Table() {
		println("Table()") ;
		bowl2.f1(1) ;
	}
	void f2(int marker) {
		println("f2("+marker+")");
	}
	static Bowl bowl2 = new Bowl(2) ;
}

class Cupboard{
	Bowl bowl3 = new Bowl(3) ;
	static Bowl bowl4 = new Bowl(4) ;
	public Cupboard() {
		println("Cupboard()");
		bowl4.f1(2);
	}
	void f3(int marker) {
		println("f3("+marker+")");
	}
	static Bowl bowl5 = new Bowl(5) ;
}

public class StaticInitialization {
	public static void main(String[] args) {
//		print("Createing new Cupboard() in main");
//		new Cupboard() ;
//		print("Creating new Cupboard() in main");
//		new Cupboard() ;
//		table.f2(1) ;
		Table.bowl1.f1(1);
	}
	//static Table table = new Table() ;
	//static Cupboard cupboard  = new Cupboard() ;
}
