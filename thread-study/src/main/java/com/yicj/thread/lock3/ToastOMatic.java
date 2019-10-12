package com.yicj.thread.lock3;

import lombok.Getter;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ToastOMatic {

    public static void main(String[] args) throws InterruptedException {
        ToastQueue dryQueue = new ToastQueue() ;
        ToastQueue butteredQueue = new ToastQueue() ;
        ToastQueue finishedQueue = new ToastQueue() ;
        ExecutorService pool = Executors.newCachedThreadPool() ;
        pool.execute(new Toaster(dryQueue));
        pool.execute(new Butterer(dryQueue,butteredQueue));
        pool.execute(new Jammer(butteredQueue,finishedQueue));
        pool.execute(new Eater(finishedQueue));
        TimeUnit.SECONDS.sleep(5);
        pool.shutdownNow();
    }

    private static class Toast{
        public enum Status{
            DRY,BUTTERED,JAMMED
        }
        @Getter
        private Status status = Status.DRY ;
        @Getter
        private final int id ;
        public Toast(int idn){
            this.id = idn ;
        }
        public void butter(){
            status = Status.BUTTERED ;
        }
        public void  jam(){
            status = Status.JAMMED ;
        }
        @Override
        public String toString() {
            return "Toast " + id +" : " + status ;
        }
    }

    private static class ToastQueue extends LinkedBlockingQueue<Toast>{

    }

    private static class Toaster implements Runnable{
        private ToastQueue toastQueue ;
        private int count = 0 ;
        private Random rand = new Random(47) ;

        public Toaster(ToastQueue tq){
            this.toastQueue = tq ;
        }
        @Override
        public void run() {
            try{
                while (!Thread.interrupted()){
                    TimeUnit.MICROSECONDS.sleep(100 + rand.nextInt(500));
                    Toast t = new Toast(count++) ;
                    System.out.println(t);
                    // insert into queue
                    toastQueue.put(t);
                }
            }catch (InterruptedException e){
                System.err.println("Toaster interrupted");
            }
            System.out.println("Toaster off");
        }
    }

    private static class Butterer implements Runnable{
        private ToastQueue dryQueue ;
        private ToastQueue butteredQueue ;
        public Butterer(ToastQueue dryQueue,ToastQueue butteredQueue){
            this.dryQueue = dryQueue ;
            this.butteredQueue = butteredQueue ;
        }
        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    // blocks until next piece of toast if available
                    Toast t = dryQueue.take() ;
                    t.butter();
                    System.out.println(t);
                    butteredQueue.put(t);
                }
            }catch (InterruptedException e){
                System.err.println("Butterer off");
            }
        }
    }

    private static class Jammer implements Runnable{
        private ToastQueue butteredQueue ;
        private ToastQueue finishedQueue ;
        public Jammer(ToastQueue butteredQueue, ToastQueue finishedQueue){
            this.butteredQueue = butteredQueue ;
            this.finishedQueue = finishedQueue ;
        }
        @Override
        public void run() {
            try {
                while (Thread.interrupted()){
                    //blocks until next piece of toast is available
                    Toast t = butteredQueue.take() ;
                    t.jam();
                    System.out.println(t);
                    finishedQueue.put(t);
                }
            }catch (InterruptedException e){
                System.err.println("Jammer interrupted");
            }
            System.out.println("Jammer off");
        }
    }

    //Consume the toast
    private static class Eater implements Runnable{
        private ToastQueue finishedQueue ;
        private int counter = 0 ;
        public Eater(ToastQueue finishedQueue){
            this.finishedQueue = finishedQueue ;
        }

        @Override
        public void run() {
            try {
                while (Thread.interrupted()){
                    Toast t = this.finishedQueue.take();
                    //Verify that the toast is coming in order
                    // and that all pieces are getting jammed
                    if(t.getId() != counter ++ || t.getStatus() != Toast.Status.JAMMED){
                        System.out.println(">>> Error : " + t);
                        System.exit(1);
                    }else {
                        System.out.println("Chomp ! " + t);
                    }
                }
            }catch (InterruptedException e){
                System.err.println("Eater interrupted");
            }
            System.out.println("Eater off");
        }
    }



}
