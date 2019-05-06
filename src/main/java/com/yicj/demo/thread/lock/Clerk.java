package com.yicj.demo.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Clerk {

    private int product = -1 ;
    private Lock lock = new ReentrantLock() ;
    private Condition condition = lock.newCondition() ;

    public void setProduct(int product) throws InterruptedException {
        lock.lock();
        try{
            waitIfFull() ;
            this.product = product ;
            System.out.printf("生产者设定(%d)%n",this.product);
            condition.signal();//通知消费者线程，可以消费了
        }finally {
           lock.unlock();
        }
    }

    public int getProduct() throws InterruptedException {
        lock.lock();
        try{
            waitIfEmpty() ;
            int p = this.product ;
            this.product = -1 ;
            System.out.printf("消费者取走(%d)%n",p);
            condition.notify();
            return p ;
        }finally {
            lock.unlock();
        }
    }

    private void waitIfFull() throws InterruptedException {

        while (this.product != -1){
            this.condition.await();
        }
    }

    private void waitIfEmpty() throws InterruptedException {
        while(this.product == -1){
            this.condition.await();
        }
    }

}
