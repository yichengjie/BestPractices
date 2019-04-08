package com.yicj.demo.file3;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class RecoverCADState {
	
	public static void main(String[] args) throws Exception   {
		  
		ObjectInputStream in = new ObjectInputStream(
			new FileInputStream("CADState.out")) ;
		//Read in the shme order they were written:
		List<Class<? extends Shape>> shapesTypes = 
				(List<Class<? extends Shape>>)in.readObject() ;
		Line.deserializeStaticState(in);
		List<Shape> shapes = (List<Shape>) in.readObject() ;
		System.out.println(shapes);
	}
}
