package com.yicj.demo.socket.socket2;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import com.yicj.demo.socket.socket2.pool.NioSelectorRunnablePool;

/**
 * 启动函数
 * @author -琴兽-
 *
 */
public class Start {

	public static void main(String[] args) {

		
		//初始化线程
		NioSelectorRunnablePool nioSelectorRunnablePool = new NioSelectorRunnablePool(
				Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
		
		//获取服务类
		ServerBootstrap bootstrap = new ServerBootstrap(nioSelectorRunnablePool);
		
		//绑定端口
		bootstrap.bind(new InetSocketAddress(10101));
		
		System.out.println("start");
	}

}
