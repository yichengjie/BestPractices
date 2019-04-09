package com.yicj.demo.jiekou;


class Person{
	public Person() {
		System.out.println("test1");
	}
}

public class Teacher {
	private String name = "ttt" ;
	public Teacher() {
		System.out.println("test2");
		//super() ;//报错
	}
	
	public static void main(String[] args) {
		Teacher t = new Teacher() ;
		//System.out.println(this.name); 报错
	}

}
