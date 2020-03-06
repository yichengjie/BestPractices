package com.yicj.thread.threadlocal;

public class MyThreadLocalTest {
   /* private static MyThreadLocal<Long> c = new MyThreadLocal<>(){
        @Override
        protected Long initialValue() {
            return Thread.currentThread().getId();
        }
    } ;*/
    private static MyThreadLocal2<Long> c = new MyThreadLocal2<Long>(){
        @Override
        protected Long initialValue() {
            return Thread.currentThread().getId();
        }
    } ;

    public static void main(String[] args) {
        for (int i = 0 ; i < 100 ; i ++){
            new Thread(()->{
                System.out.println(c.get());
            }).start();
        }

    }
}
