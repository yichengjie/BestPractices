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


public class NIOServer3 {

    //通道管理器
    private Selector boosSelector ;
    private Selector workSelector ;

    private NIOServer3 init(int port) throws IOException {
        //对应IO编程中服务端启动
        ServerSocketChannel listenerChannel= ServerSocketChannel.open() ;
        listenerChannel.socket().bind(new InetSocketAddress(port));
        listenerChannel.configureBlocking(false) ;

        boosSelector = Selector.open() ;
        workSelector = Selector.open() ;

        listenerChannel.register(boosSelector,SelectionKey.OP_ACCEPT) ;
        return this ;
    }


    public void listen(){
        System.out.println("服务器启动成功！");
        new Thread(new BoosProcessor(boosSelector,workSelector)).start();
        new Thread(new WorkProcessor(workSelector)).start();
    }


    public static void main(String [] args) throws IOException {

        new NIOServer3().init(8000).listen(); ;
    }


    static class WorkProcessor implements Runnable{

        private Selector workSelector;

        public WorkProcessor(Selector workSelector){
            this.workSelector = workSelector;
        }

        @Override
        public void run() {
            try{

                while (true){
                    //批量轮询是否有哪些连接有数据可读，这里的1指的是阻塞的时间为1ms
                    if(workSelector.select(1) > 0){
                        Set<SelectionKey> set = workSelector.selectedKeys();
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

        }
    }


   static class BoosProcessor implements Runnable{

        private  Selector bossSelector;
        private  Selector workSelector;

        public BoosProcessor(Selector bossSelector , Selector workSelector){
            this.bossSelector = bossSelector;
            this.workSelector = workSelector;
        }

        @Override
        public void run() {

            try{

                while (true){
                    //监听是否有新的连接，这里的1指的是阻塞的时间为1ms
                    if(bossSelector.select(1) > 0){
                        Set<SelectionKey> set = bossSelector.selectedKeys();
                        Iterator<SelectionKey> keyIter = set.iterator();
                        while (keyIter.hasNext()){
                            SelectionKey key = keyIter.next();
                            if(key.isAcceptable()){
                                try{
                                    //每来一个新连接，不需要创建一个线程，而是直接注册到clientSelector上
                                    ServerSocketChannel server = (ServerSocketChannel)key.channel() ;
                                    SocketChannel clientChannel = server.accept();
                                    clientChannel.configureBlocking(false) ;
                                    clientChannel.register(workSelector,SelectionKey.OP_READ) ;
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
        }
    }

}
