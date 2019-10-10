package com.yicj.demo.socket.socket1;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioClient {

    //通道管理器
    private Selector selector ;

    public  NioClient init(String serverIp, int port) throws IOException {
        //获取socket通道
        SocketChannel channel = SocketChannel.open() ;
        channel.configureBlocking(false) ;
        //获取通道管理器
        selector = Selector.open() ;
        //客户端连接服务器，需要调用channel.finishConnect();才能实现完成连接
            channel.connect(new InetSocketAddress(serverIp,port)) ;
            //为该通道注册 SelectKey.OP_CONNECT事件
        channel.register(selector, SelectionKey.OP_CONNECT) ;
        return this ;
    }

    public void listen() throws IOException{
        System.out.println("客户端启动");
        //轮询访问selector
        while (true){
            //选择注册过的io操作事件(第一次为SelectionKey.OP_CONNECT)
            selector.select() ;


            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iter = selectionKeys.iterator();
            while (iter.hasNext()){
                SelectionKey key = iter.next();
                //删除已选择的key，防止重复处理
                iter.remove();
                if(key.isConnectable()){
                    SocketChannel channel = (SocketChannel)key.channel() ;
                    //如果正在连接，则完成连接
                    if(channel.isConnectionPending()){
                        channel.finishConnect() ;
                    }
                    channel.configureBlocking(false) ;
                    //向服务器发送消息
                    channel.write(ByteBuffer.wrap(
                        new String("send message to server.").getBytes())) ;
                    //连接成功后，注册接收服务器消息的事件
                    channel.register(selector,SelectionKey.OP_READ) ;
                    System.out.println("客户端连接成功！");
                }else if(key.isReadable()){
                    SocketChannel channel = (SocketChannel)key.channel() ;
                    ByteBuffer buffer = ByteBuffer.allocate(10) ;
                    channel.read(buffer) ;
                    byte [] data = buffer.array() ;
                    String message = new String(data) ;
                    System.out.println("recevie message from server:, size:" +
                        buffer.position() + "msg : " + message);

                }
            }

        }
    }


    public static void main(String [] args) throws IOException {

        new NioClient().init("127.0.0.1",9981).listen(); ;
    }



}
