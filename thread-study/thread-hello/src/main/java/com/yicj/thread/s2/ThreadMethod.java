package com.yicj.thread.s2;

public class ThreadMethod {

    private int countDown = 5 ;
    private Thread t ;
    private String name ;
    public ThreadMethod(String name){
        this.name = name ;
    }
    public void runTask(){
        if(t == null){
            t = new Thread(){
                @Override
                public void run() {
                    try {
                        while (true){
                            System.out.println(this);
                            if(-- countDown == 0){
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
                    return getName() +" : " + countDown;
                }
            } ;
            t.start();
        }
    }
}
