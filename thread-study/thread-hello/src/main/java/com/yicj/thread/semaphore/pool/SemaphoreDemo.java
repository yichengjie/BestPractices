package com.yicj.thread.semaphore.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

    final static int SIZE = 25 ;

    public static void main(String[] args) throws InterruptedException {
        final Pool<Fat> pool = new Pool<>(Fat.class,SIZE) ;
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0 ; i < SIZE ;i ++){
            exec.execute(new CheckoutTask<>(pool));
        }
        System.out.println("All CheckoutTasks created");
        List<Fat> list = new ArrayList<>() ;
        for(int i = 0 ; i < SIZE ; i++){
            Fat fat = pool.checkout();
            System.out.println(i + ": main() thread checked out");
            fat.operation();
            list.add(fat) ;
        }
        //seaphore prevents additional checkout. so call is blocked
        Future<?> blocked = exec.submit(new Runnable() {
            @Override
            public void run() {
                pool.checkout() ;
            }
        });
        TimeUnit.SECONDS.sleep(2);
        blocked.cancel(true) ;
        System.out.println("Checking in objects in " + list);
        for(Fat f : list){
            pool.checkin(f);
        }
        for(Fat f : list){
            pool.checkin(f); //second checkin ignored
        }
        exec.shutdown();
    }
}
