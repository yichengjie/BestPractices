package com.yicj.demo.thread.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Producer3 implements Runnable{
	private BlockingQueue<Integer> productQueue ;
	
	public Producer3(BlockingQueue<Integer> productQueue) {
		this.productQueue = productQueue ;
	}
	@Override
	public void run() {
		System.out.println("生产者开始生产整数...");
		for(int product = 0 ; product< 10 ;product ++) {
			try {
				productQueue.put(product);
				System.out.printf("生产者提供整数:(%d)%n",product);
			} catch (InterruptedException e) {
				throw new RuntimeException(e) ;
			}
		}
		
	}
}


class Consumer3 implements Runnable{
	private BlockingQueue<Integer> productQueue ;
	public Consumer3(BlockingQueue<Integer> productQueue) {
		this.productQueue = productQueue ;
	}
	@Override
	public void run() {
		System.out.println("消费者开始消费整数....");
		for(int i = 0 ; i < 10 ; i++) {
			try {
				Integer product = productQueue.take();
				System.out.printf("消费者消费整数(%d)%n",product);
			} catch (InterruptedException e) {
				throw new RuntimeException(e) ;
			}
		}
	}
	
}



public class ProducerConsumerDemo3 {
	
	public static void main(String[] args) {
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1) ;
		new Thread(new Producer3(queue)).start(); ;
		new Thread(new Consumer3(queue)).start();
	}
}
