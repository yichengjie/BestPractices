package com.yicj.demo.generator;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import com.yicj.demo.generator1.Generator;
import com.yicj.demo.generator1.Generators;

class  Customer{
	
	private static long counter = 1; 
	private final long id = counter ++ ;
	private Customer() {
		
	}
	public String toString() {
		return "Customer " + id ;
	}
	
	public static Generator<Customer> generator(){
		return new Generator<Customer>() {
			@Override
			public Customer next() {
				return new Customer();
			}
		};
	}
}

class Teller{
	
	private static long counter = 1; 
	private final long id = counter ++ ;
	private Teller() {
		
	}
	
	public String toString() {
		return "Teller " + id ;
	}
	
	public static Generator<Teller> generator = 
		new Generator<Teller>() {
			@Override
			public Teller next() {
				return new Teller();
			}
		};
}


public class BankTeller{
	public static void serve(Teller t ,Customer c) {
		System.out.println(t + " serves " + c );
	}
	
	public static void main(String[] args) {
		
		Random rand = new Random(47) ;
		Queue<Customer> line = new LinkedList<Customer>() ;
		//Generators.fill() ;
		
	}
	
}













