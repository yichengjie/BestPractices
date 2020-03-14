package com.yicj.study.test;

public class Client {

    public static void main(String[] args) {
        Worker worker = new MyWork() ;
        worker.execute();
    }

}
