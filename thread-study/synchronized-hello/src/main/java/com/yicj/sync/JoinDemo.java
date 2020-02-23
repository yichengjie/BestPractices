package com.yicj.sync;

//线程没有执行完之前，会一直阻塞在join方法处
/**
 * 简单的说就是 join方法内部的实现是synchronized+object.wait方法，
 * 主线程会拿到调用join方法的线程的锁，导致主线程进入waiting状态，
 * 然后调用join方法的线程继续执行，执行完成只会调用notifyAll方法，
 * 主线程从waiting状态中被调度出来，继续执行。
 */
public class JoinDemo extends Thread{
    int i;
    Thread previousThread; //上一个线程
    public JoinDemo(Thread previousThread,int i){
        this.previousThread=previousThread;
        this.i=i;
    }
    @Override
    public void run() {
        try {
            //调用上一个线程的join方法，大家可以自己演示的时候可以把这行代码注释掉
            previousThread.join(); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("num:"+i);
    }
    public static void main(String[] args) {
        Thread previousThread = Thread.currentThread();
        for(int i=0;i<10;i++){
            JoinDemo joinDemo=new JoinDemo(previousThread,i);
            joinDemo.start();
            previousThread = joinDemo;
        }
    }
}