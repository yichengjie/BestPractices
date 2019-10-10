package com.yicj.demo.common;

import com.yicj.demo.CountedObject;
import com.yicj.demo.common.util.BasicGenerator;
import com.yicj.demo.generator.generator1.Generator;

public class BasicGeneratorDemo {
	public static void main(String[] args) {
		Generator<CountedObject> gen = BasicGenerator.create(CountedObject.class) ;
		for(int i = 0 ; i < 5 ; i++) {
			System.out.println(gen.next());
		}
	}
}
