package com.yicj.thread.threadlocal;

public class HelloWorld {

    private static ThreadLocal<Long> x = new ThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            System.out.println("init value ....");
            return Thread.currentThread().getId();
            //return 100L ;
        }
    };

    public static void main(String[] args) {
        new Thread("thread2"){
            @Override
            public void run() {
                System.out.println(this.getName() +": " + x.get());
            }
        }.start();
        //x.set(101L);
        System.out.println("main : " + x.get());
        /*x.remove();
        System.out.println("after remove main : " + x.get());*/
    }
}
