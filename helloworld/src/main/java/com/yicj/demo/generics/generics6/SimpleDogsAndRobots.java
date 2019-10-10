package com.yicj.demo.generics.generics6;

class CommunicateSimply{
	static void perform(Performs performer) {
		performer.speak(); 
		performer.sit();
	}
	
}


public class SimpleDogsAndRobots {
	
	public static void main(String[] args) {
		CommunicateSimply.perform(new PerformsingDog());
		CommunicateSimply.perform(new Robot());
	}
	

}
