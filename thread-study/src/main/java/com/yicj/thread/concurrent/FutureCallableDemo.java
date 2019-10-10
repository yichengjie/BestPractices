package com.yicj.thread.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureCallableDemo {
	
	static long fibonacci(long n) {
		if(n <= 1)
			return n ;
		return fibonacci(n-1) + fibonacci(n-2) ;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		FutureTask<Long> the30thFibture = 
				new FutureTask<>(()-> fibonacci(30)) ;
		System.out.println("老板，我要滴30个费式数，待会来拿...");
		new Thread(the30thFibture).start();
		while(!the30thFibture.isDone()) {
			System.out.println("忙别的事去....");
		}
		System.out.printf("第30个费式数:%d%n",the30thFibture.get());
		System.out.println("我去干别的事了.................");
		
	}

}
