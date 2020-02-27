package com.yicj.sync;

public class DisappearRequest1 implements Runnable {

    static DisappearRequest1 instance = new DisappearRequest1() ;
    static int count = 0 ;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance) ;
        Thread t2 = new Thread(instance) ;
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(count);
    }

    @Override
    public void run() {
        for (int i = 0 ; i < 100000 ;i ++ ){
            count++ ;
        }
    }
//    private synchronized static void busi(){
//        add();
//    }
//
//    private static void add(){
//        count++ ;
//    }
}
