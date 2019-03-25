package com.yicj.demo.lock;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.concurrent.locks.ReentrantLock;

class Resource{
	ReentrantLock lock = new ReentrantLock() ;
	private String name ;
	public Resource(String name) {
		this.name = name ;
	}
	
	public void cooperate(Resource rs) {
		while(true) {
			try {
				if(lockMeAnd(rs)) {
					System.out.printf("%s整合%s的资源%n",this.name,rs.getName());
					break;
				}
			} finally {
				this.unlockMeAnd(rs);
			}
		}
	}
	
	private void unlockMeAnd(Resource rs) {
		if(this.lock.isHeldByCurrentThread()) {
			this.lock.unlock();
		}
		if(rs.lock.isHeldByCurrentThread()) {
			rs.lock.unlock();
		}
	}
	
	private boolean lockMeAnd(Resource rs) {
		return this.lock.tryLock() && rs.lock.tryLock() ;
	}

	public String getName() {
		return name;
	}
}


public class NoDeadLockDemo {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Resource rs1 = new Resource("resource1") ;
		Resource rs2 = new Resource("resource2") ;
		Thread t1 = new Thread(() -> {
			rs1.cooperate(rs2);
		}) ;
		Thread t2 = new Thread(() -> {
			rs2.cooperate(rs1);
		}) ;
		t1.start();
		t2.start();
		
	}
	
}
