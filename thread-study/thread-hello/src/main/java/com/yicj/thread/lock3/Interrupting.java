package com.yicj.thread.lock3;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


//Sleep可中断，sync和I/O不可中断
public class Interrupting {

    private static ExecutorService pool =
            Executors.newCachedThreadPool() ;

    static void test(Runnable r) throws InterruptedException {
        Future<?> f = pool.submit(r);
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("Interrupting " + r.getClass().getName());
        f.cancel(true) ;
        System.out.println("Interrupt send to " + r.getClass().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        //test(new IOBlocked(System.in));
        //test(new SynchronizedBlocked());
        test(new SleepBlocked());
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Aborting with System.exit(0)");
        System.exit(0);
    }

    private static class SleepBlocked implements Runnable{
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                System.err.println("InterruptedException.....");
            }
            System.out.println("Exiting SleepBlocked.run()");
        }
    }

    private static class SynchronizedBlocked implements Runnable{

        public SynchronizedBlocked(){
            new Thread(){
                @Override
                public void run() {
                    f() ;//Lock acquired by this thread
                }
            }.start();
        }

        public synchronized void f(){
            while (true){//Never release lock
                Thread.yield();
            }
        }

        @Override
        public void run() {
            System.out.println("Trying to call f()");
            f() ;
            System.out.println("Exiting SynchronizedBlocked.run()");
        }
    }

    public static class IOBlocked implements Runnable{
        private InputStream in ;
        public IOBlocked(InputStream in){
            this.in = in ;
        }
        @Override
        public void run() {
            try {
                System.out.println("Waiting for read() : ");
                in.read() ;
            } catch (IOException e) {
                if(Thread.currentThread().isInterrupted()){
                    System.err.println("Interrupted from blocked I/O");
                }else {
                    throw new RuntimeException(e) ;
                }
            }
        }
    }
}
