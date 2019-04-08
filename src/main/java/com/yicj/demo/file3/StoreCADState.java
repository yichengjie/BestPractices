package com.yicj.demo.file3;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


abstract class Shape implements Serializable{
	public static int RED = 1 ,BLUE =2 ,GREEN = 3 ;
	private int xPos , yPos ,dimension ;
	private static Random rand = new Random(47) ;
	private static int counter = 0 ;
	public abstract void setColor(int newColor) ;
	public abstract int getColor() ;
	public Shape(int xVal, int yVal, int dim) {
		xPos = xVal ;
		yPos = yVal ;
		dimension = dim ;
	}
	@Override
	public String toString() {
		return getClass() +" color["+getColor()+"] "
				+ "xPos["+xPos+"], yPos["+yPos+"], dim["+dimension+"]\n";
	}
	public static Shape randomFactory() {
		int xVal = rand.nextInt(100) ;
		int yVal = rand.nextInt(100) ;
		int dim = rand.nextInt(100) ;
		switch(counter ++ % 3) {
			default :
			case 0 : return new Circle(xVal, yVal, dim) ;
			case 1 : return new Square(xVal, yVal, dim) ;
			case 2 : return new Line(xVal, yVal, dim) ;
		}
	}
}

class Circle extends Shape{
	private static int color = RED ;

	public Circle(int xVal, int yVal, int dim) {
		super(xVal, yVal, dim);
	}
	@Override
	public void setColor(int newColor) {
		color = newColor ;
	}
	@Override
	public int getColor() {
		return color;
	}
}

class Square extends Shape{
	private static int color ;
	public Square(int xVal, int yVal, int dim) {
		super(xVal, yVal, dim);
		color = RED ;
	}
	@Override
	public void setColor(int newColor) {
		color = newColor ;
	}
	@Override
	public int getColor() {
		return color;
	}
}

class Line extends Shape{
	private static int color = RED ;
	
	public static void 
	serializeStaticState(ObjectOutputStream os) 
			throws IOException{
		os.writeInt(color);
	}
	
	public static void
	deserializeStaticState(ObjectInputStream os)
			throws IOException {
		color = os.readInt() ;
	}
	
	public Line(int xVal, int yVal, int dim) {
		super(xVal, yVal, dim);
	}

	@Override
	public void setColor(int newColor) {
		color = newColor ;
	}

	@Override
	public int getColor() {
		return color;
	}
	
}

public class StoreCADState {
	
	public static void main(String[] args) throws Exception {
		
		List<Class<? extends Shape>> shapeTypes = 
				new ArrayList<Class<? extends Shape>>() ;
		// add references to the class objects:
		shapeTypes.add(Circle.class) ;
		shapeTypes.add(Square.class) ;
		shapeTypes.add(Line.class) ;
		List<Shape> shapes = new ArrayList<Shape>() ;
		// make some shapes:
		for(int i  = 0 ; i< 10 ; i ++)
			shapes.add(Shape.randomFactory()) ;
		// Set all the static colors to GREEN:
		for(int i = 0 ; i < 10 ; i ++) 
			((Shape)shapes.get(i)).setColor(Shape.GREEN);
		//Save the state vector:
		ObjectOutputStream out = new ObjectOutputStream(
			new FileOutputStream("CADState.out")) ;
		out.writeObject(shapeTypes);
		Line.serializeStaticState(out);
		out.writeObject(shapes);
		//Display the shapes:
		System.out.println(shapes);
		
		
	}
	
	
}
