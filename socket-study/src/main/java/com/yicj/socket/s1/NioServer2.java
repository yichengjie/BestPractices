package com.yicj.socket.s1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

public class NioServer2 {

    //通道管理器
    private Selector boosSelector ;
    private Selector workSelector ;


    //获取一个ServerSocket通道，并初始化通道
    public NioServer2 init(int port) throws IOException {
        //获取一个ServerSocket通道
        ServerSocketChannel serverChannel = null ;
        serverChannel = ServerSocketChannel.open() ;
        serverChannel.configureBlocking(false) ;
        serverChannel.socket().bind(new InetSocketAddress(port));
        //获取通道管理器
        boosSelector = Selector.open() ;
        workSelector = Selector.open() ;
        //将通道管理器与通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件
        //只有当该事件到达时，Selector.select()会返回，否则一直阻塞
        serverChannel.register(boosSelector,SelectionKey.OP_ACCEPT) ;
        return this ;
    }





    public void listen()throws IOException{
        System.out.println("服务器端启动成功");
        //使用轮询访问selector
        new Thread(new BoosTask(this.boosSelector, this.workSelector)).start();
        new Thread(new WorkTask(this.workSelector)).start();

    }

    public static void main(String [] args) throws IOException {

        new NioServer2().init(9981).listen();


    }
}


class BoosTask implements Runnable{
    private Selector boosSelector ;
    private Selector workSelector ;

    public BoosTask(Selector boosSelector, Selector workSelector){
        this.boosSelector = boosSelector ;
        this.workSelector = workSelector ;
    }

    @Override
    public void run() {
        try{
            //使用轮询访问selector
            while (true){
                //System.out.println("轮询...");
                boosSelector.select(500) ;//每秒轮询
                //获取selector中的迭代器，选中项为注册的事件
                Iterator<SelectionKey> iter =
                        boosSelector.selectedKeys().iterator();
                while (iter.hasNext()){
                    SelectionKey key = iter.next();
                    //删除已选key，防止重复处理
                    iter.remove();
                    ServerSocketChannel server = null ;
                    SocketChannel channel = null ;
                    //客户端请求连接事件
                    if (key.isAcceptable()){
                        server =  (ServerSocketChannel)key.channel() ;
                        //获取客户端连接通道
                        channel =  server.accept() ;
                        channel.configureBlocking(false) ;
                        //向客户端发消息
                        ByteBuffer buf = ByteBuffer.wrap(new String("send message to client").getBytes()) ;
                        channel.write(buf) ;
                        //在与客户端连接成功后，为客户端注册，SelectionKey.OP_READ事件
                        channel.register(workSelector,SelectionKey.OP_READ) ;
                        System.out.println("客户端请求连接事件");
                    }
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

class WorkTask implements Runnable{
    private Selector workSelector ;

    public WorkTask(Selector workSelector){
        this.workSelector = workSelector ;
    }

    @Override
    public void run() {
        try{
            //使用轮询访问selector
            while (true){
                //System.out.println("轮询...");
                this.workSelector.select(1) ;//每秒轮询
                //获取selector中的迭代器，选中项为注册的事件
                Iterator<SelectionKey> iter =
                        this.workSelector.selectedKeys().iterator();
                while (iter.hasNext()){
                    SelectionKey key = iter.next();
                    //删除已选key，防止重复处理
                    iter.remove();
                    //客户端请求连接事件
                    if (key.isReadable()){
                        //获取客户端传输数据可读取消息通道
                        SocketChannel channel = (SocketChannel)key.channel() ;
                        //创建读取数据缓冲器
                        ByteBuffer buffer = ByteBuffer.allocate(10) ;
                        int read = channel.read(buffer) ;
                        if(read > 0){
                            buffer.flip();
                            System.out.println("limit : " + buffer.limit());
                            String msg = Charset.defaultCharset().newDecoder().decode(buffer).toString() ;
                            System.out.println("msg : " + msg);
                            //byte [] data = buffer.array() ;
                            //String message = new String(data) ;
                            //System.out.println("receive message from client size :"
                            //        +buffer.position() +", msg :" + message );
                            //ByteBuffer out_buffer = ByteBuffer.wrap("server.".concat(message).getBytes()) ;
                            //channel.write(out_buffer) ;
                        }else {
                            key.cancel();
                        }

                    }
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
