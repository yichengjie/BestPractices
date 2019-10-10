package com.yicj.generator;

import com.yicj.common.CountedObject;
import com.yicj.common.generator.Generator;
import com.yicj.common.util.BasicGenerator;

public class BasicGeneratorDemo {
	public static void main(String[] args) {
		Generator<CountedObject> gen = BasicGenerator.create(CountedObject.class) ;
		for(int i = 0 ; i < 5 ; i++) {
			System.out.println(gen.next());
		}
	}
}
