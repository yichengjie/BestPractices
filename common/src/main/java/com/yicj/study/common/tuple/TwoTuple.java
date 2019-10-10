package com.yicj.study.common.tuple;

public class TwoTuple<A, B> {
	public final A first ;
	public final B second ;
	public TwoTuple(A a ,B b) {
		first = a; 
		second = b ;
	}
	
	public String toString() {
		return String.format("(%s, %s)", first,second).toString(); 
	}
}
