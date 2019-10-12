package com.yicj.thread.lock3;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class HorseRace {


    private static class Horse implements Runnable{
        private static int counter = 0 ;
        private final int id = counter ++ ;
        private int strides = 0 ;
        private static Random rand = new Random(47) ;
        private static CyclicBarrier barrier ;
        public Horse(CyclicBarrier b){
            barrier = b;
        }
        public synchronized int getStrides(){
            return strides ;
        }
        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    synchronized (this){
                        strides += rand.nextInt(3) ;
                    }
                    barrier.await();
                }
            }catch (InterruptedException e){
                // A legitimate way to exit
            }catch (BrokenBarrierException e){
                // This one we want to know about
            }
        }
    }



}
