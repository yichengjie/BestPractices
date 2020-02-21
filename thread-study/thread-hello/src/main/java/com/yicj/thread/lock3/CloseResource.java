package com.yicj.thread.lock3;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CloseResource {

    public static void main(String[] args) throws IOException, InterruptedException {
        //test1() ;
        test2() ;
    }

    //SocketIO可中断
    private static void test1() throws IOException, InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();
        new ServerSocket(8080) ;
        Socket client = new Socket("localhost", 8080);
        InputStream socketInput = client.getInputStream();
        pool.submit(new Interrupting.IOBlocked(socketInput));
        TimeUnit.MILLISECONDS.sleep(100);
        pool.shutdownNow() ;
        TimeUnit.MILLISECONDS.sleep(100);
        //f1.cancel(true) ;
        socketInput.close();
    }

    //I/O无法停止...
    private static void  test2() throws InterruptedException, IOException {
        ExecutorService pool = Executors.newCachedThreadPool();
        pool.submit(new Interrupting.IOBlocked(System.in));
        TimeUnit.MILLISECONDS.sleep(100);
        pool.shutdownNow() ;
        TimeUnit.MILLISECONDS.sleep(100);
        //f.cancel(true) ;
        System.out.println("Closing " + System.in.getClass().getName());
        System.in.close();
    }


}
