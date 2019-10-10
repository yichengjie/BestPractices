package com.yicj.init.s3;

import static com.yicj.study.common.util.CommonUtil.println;

class Meal {
	Meal(){
		println("Meal()");
	}
}
class Bread {
	Bread(){
		println("Bread");
	}
}

class Cheese {
	Cheese(){
		println("Cheese()");
	}
}

class Lettuce {
	Lettuce() {
		println("Lettuce()");
	}
}

class Lunch extends Meal{
	Lunch(){
		println("Lunch()");
	}
}

class PortableLunce extends Lunch{
	PortableLunce() {
		println("ProtableLunch");
	}
}


public class Sandwich  extends PortableLunce {
	
	private Bread b = new Bread() ;
	private Cheese c = new Cheese() ;
	private Lettuce l = new Lettuce() ;
	
	
	//Sandwich-->PortableLunce-->Lunch-->Meal
	public Sandwich(){
		println("Sandwich()");
	}
	public static void main(String[] args) {
		new Sandwich() ;
	}
}
