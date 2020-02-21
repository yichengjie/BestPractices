package com.yicj.thread.lock3;

import lombok.Getter;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//任务间使用管道进行输入/输出
public class PipedIO {

    public static void main(String[] args) throws IOException, InterruptedException {
        Sender sender = new Sender() ;
        Receiver receiver = new Receiver(sender) ;
        ExecutorService pool = Executors.newCachedThreadPool();
        pool.execute(sender);
        pool.execute(receiver);
        TimeUnit.SECONDS.sleep(4);
        pool.shutdownNow();
    }

    private static class Sender implements Runnable{
        private Random rand = new Random(47) ;
        @Getter
        private PipedWriter out = new PipedWriter() ;
        @Override
        public void run() {
            try {
                while (true){
                    for(char c = 'A' ; c < 'z' ; c ++){
                        out.write(c);
                        TimeUnit.MICROSECONDS.sleep(rand.nextInt(500));
                    }
                }
            }catch (IOException e){
                System.err.println(e + " Sender write exception");
            }catch (InterruptedException e){
                System.err.println(e  + " Sender sleep interrupted");
            }
        }
    }

    private static class Receiver implements Runnable{
        private PipedReader in ;
        public Receiver(Sender sender) throws IOException {
            in = new PipedReader(sender.getOut()) ;
        }
        @Override
        public void run() {
            try{
                while (true){
                    //blocks until characters are there:
                    System.out.println("Read : " +(char)in.read());
                }
            }catch (IOException e){
                System.err.println(e + " Receiver read exception");
            }
        }
    }


}
