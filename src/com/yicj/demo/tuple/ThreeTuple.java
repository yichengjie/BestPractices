package com.yicj.demo.tuple;

public class ThreeTuple<A, B, C> extends TwoTuple<A, B> {
	public final C third ;
	public ThreeTuple(A a, B b,C c) {
		super(a, b);
		this.third = c ;
	}
	
	public String toString() {
		return String.format("(%s, %s ,%s)", this.first,this.second,this.third).toString();
	}

}
