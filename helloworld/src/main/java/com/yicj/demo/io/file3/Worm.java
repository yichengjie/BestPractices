package com.yicj.demo.io.file3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

class Data implements Serializable{
	private int n ;
	public Data(int n) {
		this.n = n ;
	}
	@Override
	public String toString() {
		return Integer.toString(n);
	}
}

public class Worm  implements Serializable{
	private static Random rand = new Random(47) ;
	private Data [] d = {
		new Data(rand.nextInt(10)) ,
		new Data(rand.nextInt(10)) ,
		new Data(rand.nextInt(10)) ,
	} ;
	private Worm next ;
	private char c ;
	
	public Worm(int i , char x) {
		System.out.println("Worm constructor : " + i);
		c = x ;
		if(--i > 0)
			next = new Worm(i, (char)(x + 1)) ;
	}
	public Worm () {
		System.out.println("Default constructor");
	}
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer(":") ;
		result.append(c) ;
		result.append("(") ;
		for(Data dat : d)
			result.append(dat) ;
		result.append(")") ;
		if(next != null)
			result.append(next.toString()) ;
		return result.toString();
	}
	
	public static void main(String[] args) throws Exception {
		Worm w = new Worm(6,'a') ;
		System.out.println("writeworm start....");
		writeworm(w);
		System.out.println("readworm start ....");
		readworm();
		System.out.println("----------------------------------");
		ByteArrayOutputStream bout = writeworm2(w);
		readwrom2(bout);
	}
	
	
	private static void writeworm(Worm w) throws  Exception {
		
		System.out.println("w = " + w);
		ObjectOutputStream out = new ObjectOutputStream(
			new FileOutputStream("worm.out")) ;
		out.writeObject("Worm storage\n");
		out.writeObject(w);
		out.close();
	}
	
	private static void readworm() throws  Exception{
		ObjectInputStream in = new ObjectInputStream(
			new FileInputStream("worm.out")) ;
		String s = (String)in.readObject() ;
		Worm w2 = (Worm)in.readObject() ;
		System.out.print("s : " + s);
		System.out.println("worm : " +w2);
	}
	
	///////////////////////////////////////////////////////////
	private static ByteArrayOutputStream writeworm2(Worm w) throws  Exception {
		ByteArrayOutputStream bout = new ByteArrayOutputStream() ;
		ObjectOutputStream out2 = new ObjectOutputStream(bout) ;
		out2.writeObject("Worm storage\n");
		out2.writeObject(w);
		out2.flush();
		return bout ;
	}
	
	private static void readwrom2(ByteArrayOutputStream bout) throws  Exception{
		ObjectInputStream in2 = new ObjectInputStream(
			new ByteArrayInputStream(bout.toByteArray())) ;
		String s = (String)in2.readObject() ;
		Worm w3 = (Worm) in2.readObject() ;
		System.out.println(s +"w3 = "+ w3);
	}
	
	
}
