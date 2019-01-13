package com.yicj.demo;

import com.yicj.demo.generator1.Generator;

public class BasicGeneratorDemo {
	public static void main(String[] args) {
		Generator<CountedObject> gen = BasicGenerator.create(CountedObject.class) ;
		for(int i = 0 ; i < 5 ; i++) {
			System.out.println(gen.next());
		}
	}
}
