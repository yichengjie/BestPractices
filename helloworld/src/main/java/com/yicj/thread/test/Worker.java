package com.yicj.thread.test;

public class Worker {

    public void execute() {
        System.out.println("worker execute");
        Object retObj = this.handle();
        System.out.println("retObj : " + retObj);
    }


    public Object handle(){
        return null ;
    }
}
