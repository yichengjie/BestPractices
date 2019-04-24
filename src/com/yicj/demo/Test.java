package com.yicj.demo;

class A{
	static {
		System.out.print("1");
	}
	A(){
		System.out.print("2");
	}
}


class B extends A{
	static {
		System.out.print("A");
	}
	B(){
		System.out.print("B");
	}
}





public class Test {
	
	public static void main(String[] args) {
		
		/*String a = "Programming" ;
		//String b = new String("Programming") ;
		String c = "Program" + "ming" ;
		
		//System.out.println(a==b);
		System.out.println(a==c);
		System.out.println(a.equals(b));
		System.out.println(a.equals(c));
		System.out.println(a.intern()==b.intern());*/
		
		A ab = new B() ;
		ab = new B() ;
		
	}

}
