package com.yicj.demo;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

class MyMathMethod{
	public int add(Integer a ,Integer b) {
		
		int [] aa = {1,2} ;
		Class [] bb = {Integer.class,Integer.class} ;
		Class [] cc = new Class []{Integer.class,Integer.class} ;
		
		div(bb) ;
		div(cc) ;
		div(new Class []{Integer.class,Integer.class}) ;
		//div({Integer.class,Integer.class}) ;
		return a + b;
	}
	
	public int div(Class [] arg) {
		
		
		return 0 ;
	}
	
}


class MyBind {
	private Object target ;
	
	private List<Object> params  = new ArrayList<Object>();
	
	public MyBind(Object target) {
		this.target = target ;
	}
	
	public void bind(Object param) {
		this.params.add(param) ;
	}

	public Object execute(Method m,Object ...tailParams) {
		Object retObj =  null ;
		int len1 = this.params.size() ;
		int len =  len1 + tailParams.length ;
		Object [] args = new Object[len] ; 
		for(int i = 0 ;i < len1 ; i ++ ) {
			args[i] = this.params.get(i) ;
		}
		//第二段
		for(int i = 0 ; i < tailParams.length ;i ++) {
			args[i+len1] = tailParams[i] ;
		}
		try {
			retObj = m.invoke(this.target, args) ;
		}  catch (Exception e) {
			throw new RuntimeException(e) ;
		}
		return retObj ;
	}
}


public class Bind1 {

	public static void main(String[] args) throws Exception {
		MyMathMethod obj = new MyMathMethod() ;
		Method m =  obj.getClass().getMethod("add", 
				new Class []{Integer.class,Integer.class}) ;
		//Method m =  obj.getClass().getMethod("add", 
		//{Integer.class,Integer.class}) ;
		MyBind myBind = new MyBind(obj) ;
		test2(m, myBind);
		
	}
	
	

	private static void test1(Method m, MyBind myBind) {
		myBind.bind(1);
		myBind.bind(2);
		Object retObj = myBind.execute(m) ;
		System.out.println(retObj);
	}
	
	private static void test2(Method m, MyBind myBind) {
		myBind.bind(1);
		Object retObj = myBind.execute(m,3) ;
		System.out.println(retObj);
	}


}
