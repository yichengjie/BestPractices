package com.yicj.study.chatroom;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

//客户端线程类，抓门接受服务器响应信息
public class NioClientHandler implements Runnable{

    private Selector selector ;
    public NioClientHandler(Selector selector){
        this.selector = selector ;
    }

    @Override
    public void run() {
        try {
            execute();
        } catch (IOException e) {
            throw new RuntimeException(e) ;
        }
    }


    public void execute() throws IOException {
        for (;;){
            int readChannels = selector.select();//阻塞方法
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
                //如果是 可读事件
                if (selectionKey.isReadable()){
                    readHandler(selectionKey,selector);
                }
            }
        }
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
        //循环读取服务器返回信息
        StringBuilder response = new StringBuilder("") ;
        while (channel.read(buffer) > 0){
            //切换buffer为读模式
            buffer.flip() ;
            //读取buffer中的内容
            response.append(charset.decode(buffer)) ;
        }
        //将channel再次注册到selector上，监听他的可读事件
        channel.register(selector,SelectionKey.OP_READ) ;
        //将服务器发送的信息打印到本地
        if(response.length() > 0){
            System.out.println(":: " + response.toString());
        }
    }
}
