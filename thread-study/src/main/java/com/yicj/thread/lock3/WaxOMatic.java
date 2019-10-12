package com.yicj.thread.lock3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WaxOMatic {

    public static void main(String[] args) throws InterruptedException {
        Car car = new Car() ;
        ExecutorService pool = Executors.newCachedThreadPool();
        pool.execute(new WaxOff(car));
        pool.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(5); //run for a while
        pool.shutdownNow();
    }

    private static class Car{
        private boolean waxOn = false ;
        public synchronized void waxed(){
            waxOn = true ;//read to buff
            notifyAll();
        }
        public synchronized void buffed() throws InterruptedException {
            waxOn = false ;//read for another coat of wax
            notifyAll();
        }

        public synchronized void waitForWaxing() throws InterruptedException {
            while (!waxOn){
                wait();
            }
        }
        public synchronized void waitForBuffing() throws InterruptedException {
            while (waxOn){
                wait();
            }
        }
    }

    private static class WaxOn implements Runnable{
        private Car car ;
        public WaxOn(Car car) {
            this.car = car ;
        }
        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    System.out.println("Wax on !");
                    TimeUnit.MICROSECONDS.sleep(200);
                    car.waxed() ;
                    car.waitForBuffing();
                }
            }catch (InterruptedException e){
                System.out.println("Exiting via interrupt");
            }
            System.out.println("Ending Wax on task");
        }
    }

    private static class WaxOff implements Runnable{
        private Car car;
        public WaxOff(Car car){
            this.car = car ;
        }
        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    car.waitForWaxing();
                    System.out.println("Wax off !");
                    TimeUnit.MICROSECONDS.sleep(200);
                    car.buffed();
                }
            }catch (InterruptedException e){
                System.out.println("Exiting via interrupt");
            }
            System.out.println("Ending wax off task");
        }
    }

}
