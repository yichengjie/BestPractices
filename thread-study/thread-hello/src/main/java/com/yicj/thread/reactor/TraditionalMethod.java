package com.yicj.thread.reactor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//https://www.cnblogs.com/davidwang456/p/4589439.html
public class TraditionalMethod {
	private ExecutorService  threadPool = Executors.newFixedThreadPool(8);
	
	
	public void test() throws Exception {
		final List<String> batches = new ArrayList<String>();
		Callable<String> t = ()->{
			synchronized(batches) {  //2
	            String result = callDatabase(); //3
	            batches.add(result);
	            return result;
	        }
		};
		Future<String> f = threadPool.submit(t); //4
		String result = f.get() ; //5
		System.out.println("result : " + result);
		threadPool.shutdown();
	}
	
	private String callDatabase() {
		try {
			System.out.println("模拟查询数据中...");
			System.out.println("请等待...");
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "查询数据库返回数据";
	}  
	public static void main(String[] args) throws Exception {
		TraditionalMethod method = new TraditionalMethod() ;
		method.test();
	}
}
