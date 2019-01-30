package com.yicj.demo.init3;

import static com.yicj.demo.CommonUtil.print; 

class Meal {
	Meal(){
		print("Meal()");
	}
}
class Bread {
	Bread(){
		print("Bread");
	}
}

class Cheese {
	Cheese(){
		print("Cheese()");
	}
}

class Lettuce {
	Lettuce() {
		print("Lettuce()");
	}
}

class Lunch extends Meal{
	Lunch(){
		print("Lunch()");
	}
}

class PortableLunce extends Lunch{
	PortableLunce() {
		print("ProtableLunch");
	}
}


public class Sandwich  extends PortableLunce {
	
	private Bread b = new Bread() ;
	private Cheese c = new Cheese() ;
	private Lettuce l = new Lettuce() ;
	
	
	//Sandwich-->PortableLunce-->Lunch-->Meal
	public Sandwich(){
		print("Sandwich()");
	}
	public static void main(String[] args) {
		new Sandwich() ;
	}
}
