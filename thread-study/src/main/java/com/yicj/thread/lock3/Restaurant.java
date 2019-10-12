package com.yicj.thread.lock3;

import lombok.Getter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Restaurant {
    Meal meal ;
    WaitPerson waitPerson = new WaitPerson(this);
    Chef chef = new Chef(this) ;
    ExecutorService pool =  Executors.newCachedThreadPool() ;

    public Restaurant(){
        pool.execute(chef);
        pool.execute(waitPerson);
    }

    public static void main(String[] args) {
        new Restaurant() ;
    }

    private static class Meal{
        private final int orderNum ;
        public Meal(int orderNum){
            this.orderNum = orderNum ;
        }

        @Override
        public String toString() {
            return "Meal " + orderNum ;
        }
    }

    private static class WaitPerson implements Runnable{
        private Restaurant restaurant ;
        public WaitPerson(Restaurant restaurant){
            this.restaurant = restaurant ;
        }
        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){

                    synchronized (this){
                        while (restaurant.meal ==null){
                            wait(); //for the chef to produce a meal
                        }
                    }
                    System.out.println("Waitperson got " + restaurant.meal);
                    synchronized (restaurant.chef){
                        restaurant.meal = null ;
                        restaurant.chef.notifyAll(); //ready for another
                    }
                }
            }catch (InterruptedException e){
                System.err.println("WaitPerson interrupted ...");
            }
        }
    }

    private static class Chef implements Runnable{
        private Restaurant restaurant ;
        private int count = 0 ;
        public Chef(Restaurant restaurant){
            this.restaurant = restaurant ;
        }
        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    synchronized (this){
                        while (restaurant.meal != null){
                            wait();
                        }
                    }
                    if(++count == 10){
                        System.out.println("Out of food . closing");
                        restaurant.pool.shutdownNow() ;
                    }
                    System.out.println("Order up!");
                    synchronized (restaurant.waitPerson){
                        restaurant.meal = new Meal(count) ;
                        restaurant.waitPerson.notifyAll();
                    }
                    TimeUnit.MICROSECONDS.sleep(100);
                }
            }catch (InterruptedException e){
                System.err.println("Chef interrupted ");
            }
        }
    }



}
