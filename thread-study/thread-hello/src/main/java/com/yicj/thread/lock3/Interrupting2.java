package com.yicj.thread.lock3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Interrupting2 {

    public static void main(String[] args) throws InterruptedException {
        Thread t  =new Thread(new Blocked2()) ;
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Issuing t.interrupt()");
        t.interrupt();
    }


    private static class Blocked2 implements Runnable {
        private BlockedMutex blocked = new BlockedMutex();
        @Override
        public void run() {
            System.out.println("Waiting for f() in BlockedMutex");
            blocked.f();
            System.out.println("Broken out of blocked call");
        }
    }

    private static class BlockedMutex {
        private Lock lock = new ReentrantLock();

        public BlockedMutex() {
            //Acquire it right away .to demonstrate interruption
            // of a task blocked on a ReentrantLock
            lock.lock();
        }

        public void f() {
            //this with never be available to second task.
            try {
                lock.lockInterruptibly(); //special call
                System.out.println("lock acquired in f()");
            } catch (InterruptedException e) {
                System.out.println("interrupted from lock acquisition in f()");
            }
        }
    }
}
