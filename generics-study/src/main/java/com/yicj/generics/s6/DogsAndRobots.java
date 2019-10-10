package com.yicj.generics.s6;

class Dog {
	public void speak() {
		
	}
	public void sit() {
		
	}
	public void reproduce() {
		
	}
}

class Robot implements Performs{
	@Override
	public void speak() {
		System.out.println("Click");
	}
	@Override
	public void sit() {
		System.out.println("Clank!");
	}
	public void oilChange() {}
}

class PerformsingDog extends Dog implements Performs{
	@Override
	public void speak() {
		System.out.println("Woof!");
	}
	@Override
	public void sit() {
		System.out.println("Sitting");
	}
	public void reproduce() {
		
	}
}
class Communicate{
	public static <T extends Performs> 
	void perform(T performer) {
		performer.speak(); 
		performer.sit();
	}
}


public class DogsAndRobots {

	public static void main(String[] args) {
		
		PerformsingDog d = new PerformsingDog() ;
		Robot r = new Robot() ;
		Communicate.perform(d);
		Communicate.perform(r);
	}
}
