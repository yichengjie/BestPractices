package com.yicj.generator.s4;

import java.lang.reflect.Array;

public class ArrayMaker<T> {
	
	private Class<T> kind ;
	public ArrayMaker(Class<T> kind) {
		this.kind = kind ;
	}
	
	T[] create(int size) {
		return (T []) Array.newInstance(kind, size) ;
	}

}
