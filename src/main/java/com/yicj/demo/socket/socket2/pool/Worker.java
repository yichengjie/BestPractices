package com.yicj.demo.socket.socket2.pool;

import java.nio.channels.SocketChannel;
/**
 * worker接口
 * @author -琴兽-
 *
 */
public interface Worker {
	
	/**
	 * 加入一个新的客户端会话
	 * @param channel
	 */
	public void registerNewChannelTask(SocketChannel channel);

}
