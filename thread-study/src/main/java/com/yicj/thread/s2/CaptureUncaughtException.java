package com.yicj.thread.s2;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class CaptureUncaughtException {

    public static void main(String[] args) {
        new CaptureUncaughtException().execute();
    }

    public void execute(){
        ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory()) ;
        exec.execute(new ExceptionThread2());
    }

    private class ExceptionThread2 implements Runnable{
        @Override
        public void run() {
            Thread t = Thread.currentThread() ;
            System.out.println("run() by " + t);
            System.out.println("eh = " + t.getUncaughtExceptionHandler());
            throw new RuntimeException() ;
        }
    }

    private class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("caught " + e);
        }
    }

    private class HandlerThreadFactory implements ThreadFactory{
        @Override
        public Thread newThread(Runnable r) {
            System.out.println(this + "creating new Thread");
            Thread t = new Thread(r) ;
            System.out.println("created " +t);
            t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
            System.out.println("eh = " + t.getUncaughtExceptionHandler());
            return t;
        }
    }
}
