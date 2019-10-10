package com.yicj.file.file3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class House implements Serializable{}

class Animal implements Serializable{
	private String name ;
	private House preferredHouse ;
	public Animal(String nm ,House h) {
		this.name = nm ;
		this.preferredHouse = h ;
	}
	
	@Override
	public String toString() {
		return name +"["+super.toString()+"], "
			+ preferredHouse +"\n";
	}
}


public class MyWorld {
	
	public static void main(String[] args) throws Exception {
		
		House house = new House() ;
		List<Animal> animals = new ArrayList<Animal>() ;
		animals.add(new Animal("Bosco the dog", house)) ;
		animals.add(new Animal("Ralph the hamster", house)) ;
		animals.add(new Animal("Molly the cat", house)) ;
		System.out.println("animals : " + animals);
		ByteArrayOutputStream buf1 = new ByteArrayOutputStream() ;
		ObjectOutputStream o1 = new ObjectOutputStream(buf1) ;
		o1.writeObject(animals);
		o1.writeObject(animals);//write a 2nd set
		// write to a different stream:
		ByteArrayOutputStream buf2 = new ByteArrayOutputStream() ;
		ObjectOutputStream o2 = new ObjectOutputStream(buf2) ;
		o2.writeObject(animals);
		//Now get them back
		ObjectInputStream in1 = new ObjectInputStream(
			new ByteArrayInputStream(buf1.toByteArray())) ;
		ObjectInputStream in2 = new ObjectInputStream(
			new ByteArrayInputStream(buf2.toByteArray())) ;
		List 
		    animals1 = (List)in1.readObject() ,
			animals2 = (List)in1.readObject() ,
			animals3 = (List)in2.readObject() ;
		System.out.println("-------------------------------------");
		System.out.println("animals1: " + animals1);
		System.out.println("animals2: " + animals2);
		System.out.println("animals3: " + animals3);
		
		
		
	}
	
	
	
}
