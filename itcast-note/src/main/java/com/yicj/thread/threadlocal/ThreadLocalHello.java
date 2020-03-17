package com.yicj.thread.threadlocal;

import java.util.Random;

public class ThreadLocalHello {

    private static ThreadLocal<Integer> x = new ThreadLocal<>();

    public static void main(String[] args) {
        for (int i = 0 ; i < 2 ; i ++){
            new Thread(()->{
                int data = new Random().nextInt() ;
                System.out.println(Thread.currentThread().getName() + " has put data : " + data);
                x.set(data);
                new A().get();
                new B().get();
            }).start();
        }
    }


    static class A {
        public void get() {
            Integer data = x.get();
            System.out.println("A from " + Thread.currentThread().getName()
                    + " get data : " + data);
        }
    }


    static class B {
        public void get() {
            Integer data = x.get();
            System.out.println("B from " + Thread.currentThread().getName()
                    + " get data : " + data);
        }
    }

}
