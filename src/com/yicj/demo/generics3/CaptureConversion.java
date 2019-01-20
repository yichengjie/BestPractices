package com.yicj.demo.generics3;

import com.yicj.demo.generics2.Holder;

public class CaptureConversion {
	
	static <T> void f1(Holder<T> holder) {
		T t = holder.get() ;
		System.out.println(t.getClass().getSimpleName());
	}
	static void f2(Holder<?> holder) {
		f1(holder) ;//call with captured type
	}
	public static void main(String[] args) {
		Holder raw = new Holder<Integer>(1) ;
		f1(raw) ; // Produces warnings
		f2(raw) ;//No warnings
		Holder rawBasic = new Holder() ;
		rawBasic.set(new Object());
		f2(rawBasic) ;//No warnings
		//Upcast to Holder<?> , still figures it out
		Holder<?> wildcarded = new Holder<Double>(1.0) ;
		f2(wildcarded) ;
	}
}
