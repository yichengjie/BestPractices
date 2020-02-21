package com.yicj.thread.s2;

public class InnerThread1 {
    private int countDown = 5;
    private Inner inner ;

    public InnerThread1(String name){
        this.inner = new Inner(name) ;
    }

    private  class Inner extends Thread{
        Inner(String name){
            super(name);
            start();
        }

        @Override
        public void run() {
            try {
                while (true){
                    System.out.println(this);
                    if(--countDown == 0){
                        return;
                    }
                    sleep(10);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return this.getName() + ": " + countDown ;
        }
    }
}
