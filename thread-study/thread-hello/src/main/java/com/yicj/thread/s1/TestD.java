package com.yicj.thread.s1;

/**
 * java中sleep与wait的区别
 * @author yicj
 */
public class TestD {
	
	public static void main(String[] args) {
		 new Thread(new Thread1()).start();
		 try {
			 Thread.sleep(5000);
		 } catch (Exception e) {
		     e.printStackTrace();
		 }
		 new Thread(new Thread2()).start();
	}
	
	private static class Thread1 implements Runnable{

		@Override
		public void run() {
			synchronized (TestD.class) {
				System.out.println("enter thread1...");
				System.out.println("thread1 is waiting ...");
				try {
					//调用wait方法，线程会放弃对象锁，进入等待此对象的等待锁池
					TestD.class.wait(); 
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("thread1 is going on ....");
				System.out.println("thread is over!!!!");
			}
		}
		
	}
	
	private static class Thread2 implements Runnable{

		@Override
		public void run() {
			
			synchronized (TestD.class) {
				System.out.println("enter thread2...");
				System.out.println("thread2 is sleep ...");
				//只有针对此对象调用notify方法后本线程才进入对象锁定池准备获取对象锁进行运行状态
				TestD.class.notify();
				//区别
				//如果我们把TestD.class.notify注释掉，即TestD.class调用了wait方法，当时没有调用notify
				//则线程永远处于刮起状态
				
				try {
					//sleep()方法导致了程序暂停执行指定的时间，让出cpu该其他线程，
					//但是他的监控状态依然保持者，当指定的时间到了又会自动恢复运行状态。
					//在调用sleep()方法的过程中，线程不会释放对象锁。
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("thread2 is going on....");
				System.out.println("thread2 is over!!!");
				
			}
		}
		
	}
	/**
	 * 运行结果
	 *  enter thread1...
	 *	thread1 is waiting...
	 *	enter thread2....
	 *	thread2 is sleep....
	 *	thread2 is going on....
	 *	thread2 is over!!!
	 *	thread1 is going on ....
	 *	thread1 is over!!!
	 */
}
