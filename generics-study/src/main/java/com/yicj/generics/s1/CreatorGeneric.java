package com.yicj.generics.s1;

abstract class GenericWithCreate<T>{
	final T element ;
	public GenericWithCreate() {
		element = create() ;
	}
	abstract T create() ;
}

class XXX{}

class Creator extends GenericWithCreate<XXX>{
	@Override
	XXX create() {
		return new XXX();
	}
	
	void f() {
		System.out.println(element.getClass().getSimpleName());
	}
}

public class CreatorGeneric {
	
	public static void main(String[] args) {
		Creator c = new Creator() ;
		c.f();
	}

}
