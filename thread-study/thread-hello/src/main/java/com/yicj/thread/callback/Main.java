package com.yicj.thread.callback;

public class Main {

    public static void main(String[] args) {
        System.out.println("main: BEGIN");
        //1. 设置未捕获的异常处理器
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
            @Override
            public void uncaughtException(Thread thread, Throwable exception) {
                System.out.println("************");
                System.out.println("UncaughtExceptionHandler: BEGIN");
                System.out.println("currentThread = " + Thread.currentThread());
                System.out.println("thread = " + thread);
                System.out.println("exception : " + exception);
                System.out.println("UncaughtExceptionHandler : END");
            }
        });
        //2. 设置退出钩子函数
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                System.out.println("**********");
                System.out.println("shutdown hook: BEGIN");
                System.out.println("currentThread = " + Thread.currentThread());
                System.out.println("shutdown hook: END");
            }
        });

        //3. 大约3秒钟后启动执行“整数除0计算” 的线程
        new Thread("MyThread"){
            @Override
            public void run() {
                System.out.println("MyThread: BEGIN");
                System.out.println("MyThread: SLEEP....");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {

                }
                System.out.println("MyThread: DIVIDE");
                //整数除0计算
                int 下= 1/0 ;
                //不会来到这里
                System.out.println("MyThread: END");
            }
        }.start(); ;

        System.out.println("main: END");
    }
}
