package com.yicj.thread.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureCallableDemo2 {
	
	static long fibonacci(long n) {
		if(n <= 1)
			return n ;
		return fibonacci(n-1) + fibonacci(n-2) ;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService service = Executors.newCachedThreadPool() ;
		System.out.println("老板，我要滴30个费式数，待会来拿...");
		Future<Long> the30thFibFuture = service.submit(() -> fibonacci(30));
		while(!the30thFibFuture.isDone()) {
			System.out.println("忙别的事去....");
		}
		System.out.printf("第30个费式数：%d%n",the30thFibFuture.get());
		
	}

}
