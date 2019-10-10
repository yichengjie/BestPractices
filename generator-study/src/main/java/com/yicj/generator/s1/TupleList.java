package com.yicj.generator.s1;

import java.util.ArrayList;

import com.yicj.generator.s2.Coffee;
import com.yicj.generator.s2.Mocha;
import com.yicj.study.common.tuple.FourTuple;

public class TupleList<A,B,C,D> 
	extends ArrayList<FourTuple<A, B, C, D>>{
	public static void main(String[] args) {
		TupleList<Coffee, Mocha, String, Integer> tl = 
				new TupleList<Coffee, Mocha, String, Integer>() ;
/*		tl.add(TupleTest2.h()) ;
		tl.add(TupleTest2.h()) ;
*/		for(FourTuple<Coffee, Mocha, String, Integer> i : tl)
			System.out.println(i);
	}
	
}
