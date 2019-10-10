package com.yicj.study.common;

public class CountedObject {


	public static void main(String[] args) {

		String str = new String ("abc") ;
		String str2 = new String ("abc") ;
		System.out.println(str == str2);

		System.out.println(str.intern() == str2.intern());

	}
	
	private static long counter = 0 ;
	private final long id = counter ++ ;
	public long id() {
		return id ;
	}
	@Override
	public String toString() {
		return "CountedObject " + id ;
	}

}
