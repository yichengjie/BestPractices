package com.yicj.demo.init;

class Book{
	boolean checkedOut = false ;
	Book(boolean checkedOut){
		checkedOut = checkedOut ;
	}
	
	void checkIn() {
		checkedOut = false;
	}
	
	@Override
	protected void finalize() {
		if(checkedOut) {
			System.out.println("Error : checked out");
			//Normally , you'll also do this:
			// super .finalize () //Call the base-class version
		}
	}
}


public class TerminationCondition {
	public static void main(String[] args) {
		Book novel = new Book(true) ;
		novel.checkIn(); 
		//Drop this referece .forget to clean up:
		new Book(true) ;
		//Force garbage collection & finallization:
		System.gc();
	}
}
