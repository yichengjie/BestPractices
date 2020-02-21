package com.yicj.thread.semaphore.pool;

import java.util.concurrent.TimeUnit;

public class CheckoutTask <T> implements Runnable{
    private Pool<T> pool ;
    private static int counter = 0;
    private final int id = counter ++ ;
    public CheckoutTask(Pool<T> pool){
        this.pool = pool ;
    }
    @Override
    public void run() {
        T obj = pool.checkout() ;
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.checkin(obj); ;
    }

    @Override
    public String toString() {
        return "CheckoutTask id : " + id ;
    }
}
