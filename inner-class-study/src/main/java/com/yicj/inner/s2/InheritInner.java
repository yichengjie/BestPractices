package com.yicj.inner.s2;

class WithInner{
	class Inner{}
}


public class InheritInner  extends WithInner.Inner{
	
	public InheritInner(WithInner wi) {
		wi.super() ;
	}
	
	public static void main(String[] args) {
		WithInner wi = new WithInner() ;
		InheritInner ii = new InheritInner(wi) ;
	}
}
