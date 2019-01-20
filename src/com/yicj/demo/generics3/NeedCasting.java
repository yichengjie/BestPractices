package com.yicj.demo.generics3;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

class Widget{}

public class NeedCasting {

	public void f(String [] args)throws Exception {
		ObjectInputStream in = new ObjectInputStream(
				new FileInputStream(args[0])) ;
		List <Widget> shapes = (List<Widget>)in.readObject() ;
		
	}
}
