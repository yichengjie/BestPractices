package com.yicj.demo.thread;

public class InterruptedDemo {
	
	public static void main(String[] args) {
		
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					System.out.println("我醒了XD");
				}
				System.out.println("继续执行其他业务...");
			}
		};
		thread.start();
		System.out.println("main other busi");
		thread.interrupt();//主线程调用thread的interrupt()
	}

}
