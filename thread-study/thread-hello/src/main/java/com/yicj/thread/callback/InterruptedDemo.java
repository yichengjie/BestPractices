package com.yicj.thread.callback;

public class InterruptedDemo {

    public static void main(String[] args) {

        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    while (true){
                        // boolean flag = Thread.interrupted() ;
                        boolean flag = Thread.currentThread().isInterrupted() ;
                        if(flag){
                            throw new InterruptedException() ;
                        }

                    }
                }catch (InterruptedException e){e.printStackTrace(); }
            }
        };
        t.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }

        t.interrupt();
    }
}
