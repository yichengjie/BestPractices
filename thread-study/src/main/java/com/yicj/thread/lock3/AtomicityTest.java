package com.yicj.thread.lock3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AtomicityTest implements Runnable {
    private volatile int i = 0 ;
    public int getValue(){
        return i ;
    }
    private synchronized void eventIncrement(){
        i++ ;
        i++ ;
    }
    @Override
    public void run() {
        while (true){
            this.eventIncrement();
        }
    }

    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        AtomicityTest at = new AtomicityTest();
        pool.execute(at);
        while (true){
            int val = at.getValue() ;
            if(val % 2 != 0){
                System.out.println(val);
                System.exit(0);
            }
        }

    }
}
