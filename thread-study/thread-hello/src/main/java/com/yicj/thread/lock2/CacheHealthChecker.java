package com.yicj.thread.lock2;

import java.util.concurrent.CountDownLatch;

public class CacheHealthChecker extends BaseHealthChecker {


	public CacheHealthChecker(CountDownLatch _latch) {
		super("Cache Health Checker", _latch) ;
	}

	@Override
	public void verifyService() {
		System.out.println("Checking " + this.getServiceName());
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(this.getServiceName() + " is UP");
	}

}
