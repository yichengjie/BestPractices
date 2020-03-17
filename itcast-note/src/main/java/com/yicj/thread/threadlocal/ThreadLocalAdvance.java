package com.yicj.thread.threadlocal;

import java.util.Random;

//最佳实践，注意MyThreadScopeData类的设计
public class ThreadLocalAdvance {

    public static void main(String[] args) {
        for (int i = 0 ; i < 2 ; i ++){
            new Thread(()->{
                int data = new Random().nextInt() ;
                System.out.println(Thread.currentThread().getName() + " has put data : " + data);
                MyThreadScopeData.getThreadInstance().setName("name : " + data);
                MyThreadScopeData.getThreadInstance().setNumber(data);
                new A().get();
                new B().get();
            }).start();
        }
    }


    static class A {
        public void get() {
            Integer data = MyThreadScopeData.getThreadInstance().getNumber();
            System.out.println("A from " + Thread.currentThread().getName()
                    + " get data : " + data);
        }
    }


    static class B {
        public void get() {
            Integer data = MyThreadScopeData.getThreadInstance().getNumber();
            System.out.println("B from " + Thread.currentThread().getName()
                    + " get data : " + data);
        }
    }

}


//注意MyThreadScopeData类的设计
class MyThreadScopeData{

    private static ThreadLocal<MyThreadScopeData> x = new ThreadLocal<>() ;
    private static MyThreadScopeData instance ;

    //普通属性
    private String name ;
    private int number ;

    private MyThreadScopeData(){

    }

    public static MyThreadScopeData getThreadInstance(){
        instance = x.get() ;
        if(instance == null){
            instance = new MyThreadScopeData() ;
            x.set(instance);
        }
        return instance ;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
