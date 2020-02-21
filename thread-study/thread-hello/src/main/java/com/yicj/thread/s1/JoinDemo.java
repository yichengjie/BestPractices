package com.yicj.thread.s1;

public class JoinDemo {
	
	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("Main thread 开始...");
		Thread threadB = new Thread(()->{
			System.out.println("Thread B开始...");
			for(int i = 0 ; i< 5 ;i++) {
				System.out.println("Thread B执行...");
			}
			System.out.println("Tread B将结束");
		}) ;
		threadB.start();
		threadB.join();//将Thread B加入到Main thread流程
		System.out.println("Main thread将结束...");
	}

}
