package com.yicj.demo.util;

import com.yicj.demo.CountedObject;
import com.yicj.demo.generator.generator1.Generator;
import com.yicj.demo.util.BasicGenerator;

public class BasicGeneratorDemo {
	public static void main(String[] args) {
		Generator<CountedObject> gen = BasicGenerator.create(CountedObject.class) ;
		for(int i = 0 ; i < 5 ; i++) {
			System.out.println(gen.next());
		}
	}
}
