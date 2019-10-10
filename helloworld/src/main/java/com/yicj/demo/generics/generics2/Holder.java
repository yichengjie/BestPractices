package com.yicj.demo.generics.generics2;

public class Holder <T>{
	
	private T value ;
	public Holder(){}
	public Holder(T val) {
		this.value = val ;
	}
	public void set(T val) {
		this.value = val ;
	}
	public T get() {
		return value ;
	}
	public boolean equals(Object obj) {
		return value.equals(obj) ;
	}
	
	public static void main(String[] args) {
		Holder<Apple> Apple = new Holder<Apple>(new Apple()) ;
		Apple d = Apple.get() ;
		Apple.set(d);
		//Holder<Fruit> Fruit = new Apple ;//Cannot upcast
		Holder<? extends Fruit> fruit = Apple ;
		Fruit p = fruit.get() ;
		d = (Apple)fruit.get() ;//Returns 'Object'
		try {
			Orange c = (Orange)fruit.get() ;
		} catch (Exception e) {
			System.err.println(e);
		}
		//fruit.set(new Apple()) ;//Cannot call set()
		//fruit.set(new Fruit()) ;//Cannot call set()
		System.out.println(fruit.equals(d));
	}
}
