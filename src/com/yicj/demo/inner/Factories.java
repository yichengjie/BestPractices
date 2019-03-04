package com.yicj.demo.inner;

import static com.yicj.demo.util.CommonUtil.println;

interface Service{
	void method1() ;
	void method2() ;
}

interface ServiceFactory{
	Service getService() ;
}

class Implemention1 implements Service{

	private Implemention1() {
		
	}
	
	@Override
	public void method1() {
		println("Implementation1 method1");
	}

	@Override
	public void method2() {
		println("Implementation1 method2");
	}
	
	public static ServiceFactory factory = 
			new ServiceFactory() {
		@Override
		public Service getService() {
			return new Implemention1();
		}
	};
	
}


class Implemention2 implements Service{

	private Implemention2() {
		
	}
	
	@Override
	public void method1() {
		println("Implemention2 method1");
	}

	@Override
	public void method2() {
		println("Implemention2 method2");
	}
	
	public static ServiceFactory factory = 
			new ServiceFactory() {
		@Override
		public Service getService() {
			return new Implemention2();
		}
	};
	
}




public class Factories {
	
	public static void serviceConsumer(ServiceFactory fact) {
		Service s = fact.getService() ;
		s.method1(); 
		s.method2(); 
	}
	
	public static void main(String[] args) {
		serviceConsumer(Implemention1.factory);
		serviceConsumer(Implemention2.factory);
	}

}
