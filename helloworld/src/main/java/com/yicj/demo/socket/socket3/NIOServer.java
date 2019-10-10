package com.yicj.demo.socket.socket3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {

    public static void main(String [] args) throws IOException {

        Selector serverSelector = Selector.open() ;
        Selector clientSelector = Selector.open() ;
        new Thread(()->{

            try{
                //对应IO编程中服务端启动
                ServerSocketChannel listenerChannel= ServerSocketChannel.open() ;
                listenerChannel.socket().bind(new InetSocketAddress(8000));
                listenerChannel.configureBlocking(false) ;
                listenerChannel.register(serverSelector,SelectionKey.OP_ACCEPT) ;
                while (true){
                    //监听是否有新的连接，这里的1指的是阻塞的时间为1ms
                    if(serverSelector.select(1) > 0){
                        Set<SelectionKey> set = serverSelector.selectedKeys();
                        Iterator<SelectionKey> keyIter = set.iterator();
                        while (keyIter.hasNext()){
                            SelectionKey key = keyIter.next();
                            if(key.isAcceptable()){
                                try{
                                    //每来一个新连接，不需要创建一个线程，而是直接注册到clientSelector上
                                    ServerSocketChannel server = (ServerSocketChannel)key.channel() ;
                                    SocketChannel clientChannel = server.accept();
                                    clientChannel.configureBlocking(false) ;
                                    clientChannel.register(clientSelector,SelectionKey.OP_READ) ;
                                }finally {
                                    keyIter.remove();
                                }
                            }
                        }
                    }

                }

            }catch (IOException e){
                e.printStackTrace();
            }

        }).start();


        new Thread(()->{

            try{

                while (true){
                    //批量轮询是否有哪些连接有数据可读，这里的1指的是阻塞的时间为1ms
                    if(clientSelector.select(1) > 0){
                        Set<SelectionKey> set = clientSelector.selectedKeys();
                        Iterator<SelectionKey> keyIter = set.iterator();
                        while (keyIter.hasNext()){
                            SelectionKey key = keyIter.next();
                            if(key.isReadable()){
                                try{
                                    SocketChannel clientChannel = (SocketChannel)key.channel() ;
                                    ByteBuffer buf = ByteBuffer.allocate(1024) ;
                                    //读取数据以块为单位批量读取
                                    clientChannel.read(buf) ;
                                    buf.flip() ;
                                    System.out.println(
                                        Charset.defaultCharset().newDecoder().decode(buf).toString());

                                }finally {
                                    keyIter.remove();
                                    key.interestOps(SelectionKey.OP_READ) ;
                                }
                            }

                        }
                    }

                }

            }catch (IOException e){
                e.printStackTrace();
            }

        }).start();



    }



}
