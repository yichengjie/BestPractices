package com.yicj.demo.socket.socket1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServerM {

    public static void main(String [] args) throws IOException {

        int port = 7000 ;
        int clientNo = 1 ;
        ServerSocket serverSocket = new ServerSocket(port) ;
        ExecutorService exec = Executors.newCachedThreadPool() ;
        try {
            while(true){
                Socket socket = serverSocket.accept() ;
                exec.execute(new SingleServer(socket,clientNo));
                clientNo ++ ;
            }
        }finally {
            serverSocket.close();
        }

    }

}

class SingleServer implements Runnable{
    private Socket socket ;
    private int clientNo ;
    public  SingleServer(Socket socket, int clientNo){
        this.socket = socket ;
        this.clientNo = clientNo ;
    }

    @Override
    public void run() {
        DataInputStream dis = null;
        DataOutputStream dos = null;
        try {
            dis = new DataInputStream(
                new BufferedInputStream(socket.getInputStream())
            );
            dos = new DataOutputStream(
                new BufferedOutputStream(socket.getOutputStream())
            );
            do{
                double length = dis.readDouble() ;
                System.out.println(String.format("从客户端%d接收到边数据为:%.2f",this.clientNo,length));
                double result = length * length ;
                dos.writeDouble(result);
                dos.flush();
            }while (dis.readInt() !=0) ;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println(String.format("与客户端%d通信结束",this.clientNo));
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

