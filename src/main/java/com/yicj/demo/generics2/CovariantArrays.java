package com.yicj.demo.generics2;

class Fruit{}
class Apple extends Fruit{}
class Jonathan extends Fruit{}
class Orange extends Fruit{}

public class CovariantArrays {
	
	public static void main(String[] args) {
		
		Fruit [] fruit = new Apple [10] ;
		fruit[0] = new Apple() ;
		//fruit[1] = new Jonathan() ;//java.lang.ArrayStoreException
		//Runtime type is Apple[] ,not Fruit[] or Orange[]:
		//Compiler allows you to add Fruit:
		//fruit[0] = new Fruit() ;//java.lang.ArrayStoreException
		//Compiler allows you to add Oranges:
		//fruit[0] = new Orange() ;////java.lang.ArrayStoreException
		String name = fruit.getClass().getName();
		System.out.println(name);
	}
}
