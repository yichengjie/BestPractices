package com.yicj.demo.generics3;

import com.yicj.demo.generics2.Holder;

public class Wildcards {
	//Raw arguments:
	static void rawArgs(Holder holder ,Object arg) {
		holder.set(arg); // Warning
		//Unchecked call to set(T) as a 
		//member of the raw type Holder
		//holder .set(new Wildcards()) ;//Same warning
		//Can't do this; dont't have any 'T'
		//T t = holder.get() ;
		//Ok ,but type information has been lost:
		Object obj = holder.get() ;
	}
	//Similar to rawArgs(). but errors instead of warning:
	static void unboundedArg(Holder<?> holder , Object arg) {
		//holder.set(obj);//Error:
		//set(capture of ?) in Holder<capture of ?>
		//cannot be applied to (Object)
		//holder.set(new Wildcards()) ;//Same error
		//Can't do this: dont't have any 'T'
		//T t = holder.get() ;
		//Ok ,but type information has been lost:
		Object obj = holder.get() ;
	}
	static <T> T exact1(Holder<T> holder) {
		T t  = holder.get() ;
		return t ;
	}
	static <T> T exact2(Holder<T> holder , T arg) {
		holder.set(arg) ;
		T t = holder.get() ;
		return t ;
	}
	
	static <T> T wildSubtype(Holder<? extends T> holder , T arg) {
		//holder.set(arg);//Error:
		//set(capture of ? extends T) in
		//Holder <capture of ? extends T>
		//cannot be applied to (T)
		T t = holder.get() ;
		return t ;
	}
	
	static <T> void wildSupertype(Holder<? super T> holder, T arg) {
		holder.set(arg);
		//T t = holder.get() ; //Error
		//Incompatible types:found Object .required T
		Object obj = holder.get() ;
	}
	
	public static void main(String[] args) {
		Holder raw = new Holder<Long>() ;
		raw = new Holder() ;
		Holder<Long> qualified = new Holder<Long>() ; 
		Holder<?> unbounded = new Holder<Long>() ;
		Holder<? extends Long> bounded = new Holder<Long>() ;
		Long lng = 1L ;
		rawArgs(raw, lng);
		rawArgs(qualified, lng);
		rawArgs(unbounded, lng);
		rawArgs(bounded, lng);
		//
		unboundedArg(raw, lng);
		unboundedArg(qualified, lng);
		unboundedArg(unbounded, lng);
		unboundedArg(bounded, lng);
		//
		Long r2 = exact1(qualified) ;
		Object r3 = exact1(unbounded) ;//Must return Object
		Long r4 = exact1(bounded) ;
		
		
		
	}
	
	
}
