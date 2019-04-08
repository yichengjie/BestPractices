package com.yicj.demo.generics1;

public class ArrayOfGeneric {

	static final int SIZE = 100 ;
	static Generic<Integer> [] gia ;
	public static void main(String[] args) {
		gia = (Generic<Integer> []) new Generic[SIZE] ;
		gia [0] = new Generic<Integer>() ;
		//Discovers type mismath at compile time
		//gia[1] = new Generic<Double>() ;
	}

}
