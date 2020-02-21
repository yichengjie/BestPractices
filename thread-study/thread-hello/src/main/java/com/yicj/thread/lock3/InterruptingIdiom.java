package com.yicj.thread.lock3;

import java.util.concurrent.TimeUnit;

public class InterruptingIdiom {

    public static void main(String[] args) throws InterruptedException {
        //test1() ;
        test2();
    }



    private static void test1() throws InterruptedException {
        Thread t = new Thread(new Blocked3()) ;
        t.start();
        TimeUnit.MICROSECONDS.sleep(100);
        t.interrupt();
    }

    private static void test2() throws InterruptedException {
        Thread t = new Thread(new Blocked3()) ;
        t.start();
        TimeUnit.MICROSECONDS.sleep(1020);
        t.interrupt();
    }

    private static class NeedsCleanup{
        private int id ;
        public NeedsCleanup(int ident){
            this.id = ident ;
            System.out.println("NeedsCleanup " + id);
        }
        public void cleanup(){
            System.out.println("Cleaning up " + this.id);
        }
    }

    private static class Blocked3 implements Runnable{
        private volatile double d = 0.0 ;
        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    //point1
                    NeedsCleanup n1 = new NeedsCleanup(1) ;
                    try {
                        System.out.println("Sleeping ");
                        TimeUnit.SECONDS.sleep(1) ;
                        //point2
                        NeedsCleanup n2 = new NeedsCleanup(2) ;
                        try {
                            System.out.println("Calculating");
                            for (int i = 0 ; i < 2500000 ;i ++){
                                d = d + (Math.PI + Math.E) /d ;
                            }
                            System.out.println("Finished time-consuming operation");
                        }finally {
                            n2.cleanup();
                        }
                    }finally {
                        n1.cleanup() ;
                    }
                }
                System.out.println("Exiting via while() test");
            }catch (Exception e){
                System.out.println("Exiting via InterruptedException");
            }
        }
    }
}
