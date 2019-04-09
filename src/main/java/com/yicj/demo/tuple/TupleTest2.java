package com.yicj.demo.tuple;
import static com.yicj.demo.tuple.Tuple.* ;

import com.yicj.demo.generator.generator1.Americano;
import com.yicj.demo.generator.generator1.Coffee;
import com.yicj.demo.generator.generator1.Mocha;

public class TupleTest2 {

	public TupleTest2() {
	}
	public static TwoTuple<String, Integer> f(){
		return tuple("hi", 47) ;
	}
	
	public static TwoTuple f2() {
		return tuple("hi", 47) ;
	}
	
	public static ThreeTuple<Coffee, String, Integer> g(){
		return tuple(new Coffee(), "hi", 47) ;
	}
	
	public static FourTuple<Coffee, Mocha, String, Integer> h(){
		return tuple(new Coffee(), new Mocha(), "hi", 47) ;
	}
	
	public static FiveTuple<Coffee, Mocha, Americano, String, Integer> k(){
		return tuple(new Coffee(), new Mocha(), new Americano(), "hi", 47) ;
	}
	
	public static void main(String[] args) {
		TwoTuple<String, Integer> ttsi = f() ;
		System.out.println(ttsi);
		System.out.println(f2());
		System.out.println(g());
		System.out.println(h());
		System.out.println(k());
	}
	
}
