package com.yicj.demo.thread.lock;

import java.util.concurrent.Semaphore;

public class SemaphoreTest1 {
	public static void main(String[] args) {
        int N = 8;            //工人数
        Semaphore semaphore = new Semaphore(5); //机器数目
        for(int i=0;i<N;i++)
            new Worker(i,semaphore).start();
    }
 
    static class Worker extends Thread{
        private int num;
        private Semaphore semaphore;
        public Worker(int num,Semaphore semaphore){
            this.num = num;
            this.semaphore = semaphore;
        }
        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("工人"+this.num+"占用一个机器在生产...");
                Thread.sleep(2000);
                System.out.println("工人"+this.num+"释放出机器");
                semaphore.release();           
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
