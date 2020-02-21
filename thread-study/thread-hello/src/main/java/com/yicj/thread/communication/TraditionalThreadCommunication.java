package com.yicj.thread.communication;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;

public class TraditionalThreadCommunication {
    public static void main(String[] args) {
//        Business busi = new Business() ;
//        new Thread(()->{
//            for (int i = 0; i <50 ; i++) {
//                busi.sub();
//            }
//        }).start();
//        new Thread(()->{
//            for (int i = 0; i < 50; i++) {
//                busi.main() ;
//            }
//        }).start();

    }
    static class Business {
        private boolean bShouldSub = true ;
        public synchronized void sub(){

            while(!bShouldSub){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < 10; i++) {
                System.out.println("sub "+ i);
            }
            this.bShouldSub = false;
            this.notify();
        }
        public synchronized void main(){
            while(this.bShouldSub){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i <10 ; i++) {
                System.out.println("main "+ i);
            }
            this.bShouldSub = true ;
            this.notify();
        }
    }
}
