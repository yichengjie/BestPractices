package com.yicj.demo.generator;

import java.util.ArrayList;

import com.yicj.demo.generator1.Coffee;
import com.yicj.demo.generator1.Mocha;
import com.yicj.demo.tuple.FourTuple;
import com.yicj.demo.tuple.TupleTest2;

public class TupleList<A,B,C,D> 
	extends ArrayList<FourTuple<A, B, C, D>>{
	public static void main(String[] args) {
		TupleList<Coffee, Mocha, String, Integer> tl = 
				new TupleList<Coffee, Mocha, String, Integer>() ;
		tl.add(TupleTest2.h()) ;
		tl.add(TupleTest2.h()) ;
		for(FourTuple<Coffee, Mocha, String, Integer> i : tl)
			System.out.println(i);
	}
	
}
