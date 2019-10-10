package com.yicj.generics.s3;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class ClassCasting {

	public void f(String [] args) throws Exception {
		
		ObjectInputStream in = new ObjectInputStream(
				new FileInputStream(args[0])) ;
		//Won't Compile:
		//List<Widget> lw1 =
		//List<Widget>.class.catt(in.readObject())
		List<Widget> lw2  = List.class.cast(in.readObject()); 
		
	}
}
