package com.yicj.study.chatroom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO服务端
 */
public class NioServer {



    public void start() throws IOException {
        //1. 创建Selector
        Selector selector = Selector.open();
        //2. 通过ServerSocketChannel创建channel通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //3. 为channel通道绑定监听端口
        serverSocketChannel.bind(new InetSocketAddress(8000)) ;
        //4. 设置channel为非阻塞模式
        serverSocketChannel.configureBlocking(false) ;
        //5. 将channel注册到selector上，监听连接事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT) ;
        System.out.println("服务器启动成功");
        //6. 循环等待新接入的连接
        for (;;){
            /**
             * TODO 获取可用channel数量
             */
            int readChannels = selector.select();//阻塞方法
            /**
             * TODO 为什么要这样
             */
            if(readChannels == 0){
                continue;
            }
            //获取可用channel的集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectionKeys.iterator();
            while (iter.hasNext()){
                SelectionKey selectionKey = iter.next();
                //移除set中的当前selectionKey
                iter.remove();
                //7. 根据就绪状态，调用对应方法处理业务逻辑
                //如果是 接入事件
                if (selectionKey.isAcceptable()){
                    acceptHandler(serverSocketChannel,selector);
                }
                //如果是 可读事件
                if (selectionKey.isReadable()){
                    readHandler(selectionKey,selector);
                }
            }
        }

    }

    /**
     * 接入事件处理
     */
    private void acceptHandler(ServerSocketChannel serverSocketChannel, Selector selector) throws IOException {

        //如果是接入事件，创建socketChannel
        SocketChannel channel = serverSocketChannel.accept();
        //将socketChannel设置为非阻塞工作模式
        channel.configureBlocking(false) ;
        //将channel注册到selector上，监听可读事件
        channel.register(selector, SelectionKey.OP_READ) ;
        //回复客户端提示信息
        String tipContent = "你与聊天室里其他人都不是朋友关系，请注意隐私安全。" ;
        ByteBuffer buffer = Charset.forName("UTF-8").encode(tipContent);
        channel.write(buffer) ;
    }

    /**
     * 可读事件处理
     */
    private void readHandler(SelectionKey selectionKey, Selector selector) throws IOException {
        //要从selectionKey中获取已经就绪的channel
        SocketChannel channel = (SocketChannel)selectionKey.channel();
        //创建buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Charset charset = Charset.forName("UTF-8");
        //循环读取客户请求信息
        String request = "" ;
        while (channel.read(buffer) > 0){
            //切换buffer为读模式
            buffer.flip() ;
            //读取buffer中的内容
            request += charset.decode(buffer) ;
        }
        //将channel再次注册到selector上，监听他的可读事件
        channel.register(selector,SelectionKey.OP_READ) ;
        //将客户端发送的请求信息广播给其他客户端
        if(request.length() > 0){
            System.out.println(":: " + request);
            //广播给其他客户端
            this.broadCast(selector,channel, request);
        }
    }


    //广播给其他客户端
    private void broadCast(Selector selector, SocketChannel channel,
                           String request) throws IOException {
        //获取所有已经接入的客户端channel
        Charset charset = Charset.forName("UTF-8");

        Set<SelectionKey> keys = selector.keys();
        for (SelectionKey key: keys){
            System.out.println("key : " + key);
            SelectableChannel targetChannel = key.channel();
            //剔除发消息的客户端
            if(targetChannel instanceof SocketChannel
                    && targetChannel != channel){
                //注意这里不能提到for循环外面，因为write方法完成后buffer无法继续write
                ByteBuffer buffer = charset.encode(request);
                ((SocketChannel) targetChannel).write(buffer);
            }
        }
        //循环向所有接入channel广播信息
    }

    public static void main(String[] args) throws IOException {
        NioServer server = new NioServer() ;
        server.start();
    }

}
