package com.yicj.study.chatroom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

//Nio客户端
public class NioClient {

    //启动
    public void start(String nickName) throws IOException {
        //连接服务器端
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8000);
        SocketChannel channel = SocketChannel.open(address);
        channel.configureBlocking(false) ;
        System.out.println("客户端启动成功!");
        //接收服务器端响应
        //新开线程，专门负责来接收服务器端的响应数据
        //selector , socketChannel ,注册读取
        Selector selector = Selector.open();
        channel.register(selector, SelectionKey.OP_READ) ;
        new Thread(new NioClientHandler(selector)).start() ;

        //向服务器发送数据
        Charset charset = Charset.forName("UTF-8");
        Scanner scanner = new Scanner(System.in) ;
        System.out.println("请输入内容: ");
        while (scanner.hasNextLine()){
            System.out.println("请输入内容: ");
            String request = scanner.nextLine();
            if(request != null && request.length() > 0){
                channel.write(charset.encode(nickName + ": " + request)) ;
            }
        }

    }

}
