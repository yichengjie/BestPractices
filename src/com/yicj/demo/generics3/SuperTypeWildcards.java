package com.yicj.demo.generics3;

import java.util.List;

class Fruit{}
class Apple extends Fruit{}
class Jonathan extends Fruit{}
class Orange extends Fruit{}



public class SuperTypeWildcards {

	static void writeTo(List<? super Apple> apples) {
		apples.add(new Apple()) ;
		//apples.add(new Jonathan()) ;
		//apples.add(new Fruit()) ;//Error
	}
}
