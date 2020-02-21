package com.yicj.thread.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierTest {

    public static void main(String[] args) {
        int threadCount = 3;
        List<Worker> workers = new ArrayList<>() ;
        CyclicBarrier barrier = new CyclicBarrier(threadCount, new Runnable() {
            int count = 0 ;
            @Override
            public void run() {
                System.out.println("------------------------");
                count ++ ;
                if(count >= 4){
                    for(Worker worker: workers){
                        worker.interrupt();
                    }
                }
            }
        });
        for (int i = 0; i < threadCount; i++) {
            System.out.println("创建工程线程 " + i);
            Worker worker = new Worker(barrier);
            workers.add(worker) ;
            worker.start();
        }
    }

    private static class Worker extends Thread {
        private CyclicBarrier barrier;
        public Worker(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    String tname = Thread.currentThread().getName();
                    System.out.println(tname + "开始等待其他线程");
                    barrier.await();
                    System.out.println(tname + "开始执行!");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(tname + "执行完毕");
                }
            } catch (InterruptedException e) {
                System.err.println("exit via Interrupted ");
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e) ;
            }
        }
    }
}
