package com.yicj.demo.generics.generics0;
import static java.lang.System.out;

class ClassAsFactory<T>{
	T x ;
	public ClassAsFactory(Class<T> kind){
		try {
			x = kind.newInstance() ;
		} catch (Exception e) {
			throw new RuntimeException(e) ;
		}
	}
}


class Employee{}

public class InstantiateGenericType {

	public static void main(String[] args) {
		ClassAsFactory<Employee> fe = 
				new ClassAsFactory<Employee>(Employee.class) ;
		out.println("ClassAsFactory <Employee> successed !");
		ClassAsFactory<Integer> f1 = 
				new ClassAsFactory<Integer>(Integer.class) ;
		
	}
}
