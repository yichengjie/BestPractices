package com.yicj.demo.thread;
/**
 * volatile表示变量是不稳定，易变的，也就是可能在多线程下存取，
 * 这保证变量的可见性，也就是若有线程动了变量，另一线程一定能看到变更，
 * 被标记为volatile的变量，不允许线程快取，变量的存取一定是在共享内存中进行的，
 * @author yicj
 */
class Variable2 {
	volatile static int i = 0 , j = 0 ;
	
	static void one() {
		i ++ ;
		j ++ ;
	}
	
	static void two() {
		System.out.printf("i = %d, j = %d%n" , i , j);
	}

}

public class Variable2Test {
	public static void main(String[] args) {
		Thread thread1 = new Thread(() ->  {
			while(true) {
				Variable2.one();
			}
		}) ;
		Thread thread2 = new Thread(() ->  {
			while(true) {
				Variable2.two();
			}
		}) ;
		thread1.start();
		thread2.start();
	}
}

/////////////////////////////////////////////////

/**
 * 如果thread1线程正在执行some实例run中的while循环，
 * 你不希望thread1因快取isContinue,使得thread2调用了stop()设置isContinue为false，
 * 而thread1无法在下次while条件检查时看到thread2对isContinue的变更，
 * 就可以将isContinue标记为volatile
 * @author yicj
 *
 */
class Some implements Runnable{
	private volatile boolean isContinue = true;
	public void stop() {
		isContinue = false;
	}
	@Override
	public void run() {
		
		while(isContinue) {
			//....业务处理
		}
	}
}
