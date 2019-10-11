package com.yicj.thread.s2;

import java.util.concurrent.TimeUnit;

public class InnerRunnable1 {

    private int countDown =5 ;
    private Inner inner ;

    public InnerRunnable1(String name){
        inner = new Inner(name) ;
    }
    private class Inner implements Runnable{
        Thread t ;
        Inner(String name){
            t = new Thread(this,name) ;
            t.start();
        }
        @Override
        public void run() {
            try {
                while (true){
                    System.out.println(this);
                    if(--countDown == 0){
                        return;
                    }
                    TimeUnit.MICROSECONDS.sleep(10);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return t.getName() + ": " + countDown ;
        }
    }

}
