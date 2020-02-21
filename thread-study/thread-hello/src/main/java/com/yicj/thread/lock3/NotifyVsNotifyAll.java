package com.yicj.thread.lock3;

import java.util.*;
import java.util.concurrent.*;

public class NotifyVsNotifyAll {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService pool = Executors.newCachedThreadPool();
        for(int i = 0 ; i < 5 ; i++){
            pool.execute(new Task());
        }
        pool.execute(new Task2());
        Timer timer = new Timer() ;
        timer.scheduleAtFixedRate(new TimerTask() {
            boolean prod = true ;
            @Override
            public void run() {
                if(prod){
                    System.out.println("notify()");
                    Task.blocker.prod();
                    prod = false ;
                }else {
                    System.out.println("notifyAll()");
                    Task.blocker.prodAll();
                    prod = true;
                }
            }
        },400,400);//run every .4 second

        TimeUnit.SECONDS.sleep(5);
        timer.cancel();
        System.out.println("Timer canceled");
        TimeUnit.MICROSECONDS.sleep(500);
        System.out.println("Task2.blocker.prodAll()");
        Task2.blocker.prodAll();
        TimeUnit.MICROSECONDS.sleep(500);
        System.out.println("shuting down");
        pool.shutdownNow();
    }

    private static class Blocker{
        synchronized void waitingCall(){
            try {
                while (!Thread.interrupted()){
                    wait();
                    System.out.println(Thread.currentThread().getName());
                }
            }catch (InterruptedException e){
                // ok to exit this way
                System.err.println("Exited via InterruptedException");
            }
        }
        synchronized void prod(){
            notify();
        }
        synchronized void prodAll(){
            notifyAll();
        }
    }

    private static class Task implements Runnable{
        static Blocker blocker = new Blocker() ;

        @Override
        public void run() {
            blocker.waitingCall();
        }
    }
    private static class Task2 implements Runnable{
        static Blocker blocker = new Blocker() ;
        @Override
        public void run() {
            blocker.waitingCall();
        }
    }
}
