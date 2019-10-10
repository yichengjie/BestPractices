package com.yicj.thread.lock2;

public class VolatileTest {
	public static volatile int race = 0;
	public static void increase() {
		race ++ ;
	}
	
/*	public final int getAndAddInt(Object o ,long offset ,int delta) {
		int v ; 
		do {
			v = getIntVolatile(o,offset) ;//拿到内存位置的最新值
		}while(!compareAndSwapInt())
	}*/
	
	
	private static final int THREADS_COUNT = 20 ;
	//主方法
	public static void main(String[] args) {
		Thread[] threads = new Thread[THREADS_COUNT] ;
		for(int i = 0 ; i< THREADS_COUNT ; i++) {
			threads[i] = new Thread(()-> {
				for(int j = 0 ; j < 10000 ; j++) {
					increase();
				}
			}) ;
			threads[i].start();
		}
		//等待所有的累加线程都结算
		while(Thread.activeCount() > 1) {
			System.out.println("等待......");
			Thread.yield();
		}
		System.out.println(race);
	}
	
}
