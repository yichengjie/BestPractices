package com.yicj.thread.lock2;

public class YieldTest {
	
	public static class YieldThread extends Thread {
	    public YieldThread(String name) {  
	        super(name);  
	    } 
	    public void run() {
	        for (int i = 1; i <= 50; i++) {
	            System.out.println("" + this.getName() + "-----" + i);
	            // 当i为30时，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）
	            if (i == 30) {
	                Thread.yield();
	            }
	        }
	    }
	}
	
	public static void main(String[] args) {
        YieldThread ta=new YieldThread("hello1");
        YieldThread tb=new YieldThread("hello2");
        ta.start();
        tb.start();
    }

}
