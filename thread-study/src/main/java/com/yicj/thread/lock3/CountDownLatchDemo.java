package com.yicj.thread.lock3;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {
    static final int SIZE = 100 ;

    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        // All must shar a single CountDownLatch object
        CountDownLatch latch = new CountDownLatch(SIZE) ;
        for(int i = 0 ; i < 10 ; i ++){
            pool.execute(new WaitingTask(latch));
        }
        for(int i = 0 ; i < SIZE ; i ++){
            pool.execute(new TaskPortion(latch));
        }
        System.out.println("Launched all tasks");
        pool.shutdown() ;//quit when all tasks complete
    }

    //performs some portion of a task
    private static class TaskPortion implements Runnable{
        private static int counter = 0 ;
        private final int id = counter ++ ;
        private static Random rand = new Random(47) ;
        private final CountDownLatch latch ;
        public TaskPortion(CountDownLatch latch){
            this.latch = latch ;
        }
        @Override
        public void run() {
            try {
                doWork() ;
                latch.countDown();
            }catch (InterruptedException e){
                System.err.println("acceptable way to exit");
            }
        }

        public void doWork() throws InterruptedException {
            TimeUnit.MICROSECONDS.sleep(rand.nextInt(2000));
            System.out.println(this  + " completed");
        }

        @Override
        public String toString() {
            return String.format("%1$-3d",id) ;
        }
    }

    //Waits on the CountDownLatch
    private static class WaitingTask implements Runnable{
        private static int counter = 0 ;
        private final int id = counter ++ ;
        private final CountDownLatch latch ;
        public WaitingTask(CountDownLatch latch){
            this.latch = latch ;
        }
        @Override
        public void run() {
            try{
                latch.await();
                System.out.println("Latch barrier passed for " + this);
            }catch (InterruptedException e){
                System.err.println(this + " interrupted");
            }
        }
        @Override
        public String toString() {
            return String.format("WaitingTask %1$-3d ", id) ;
        }
    }

}
