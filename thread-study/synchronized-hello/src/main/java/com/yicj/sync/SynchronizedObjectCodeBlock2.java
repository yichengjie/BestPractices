package com.yicj.sync;

public class SynchronizedObjectCodeBlock2 implements Runnable {
    static SynchronizedObjectCodeBlock2 instance =
            new SynchronizedObjectCodeBlock2() ;

    @Override
    public void run() {
        synchronized (this){
            System.out.println("我是对象锁的代码块形式，我叫"
                    + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()
                    +"运行结束。");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance) ;
        Thread t2 = new Thread(instance) ;
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()){}
        System.out.println("finished");
    }
}
