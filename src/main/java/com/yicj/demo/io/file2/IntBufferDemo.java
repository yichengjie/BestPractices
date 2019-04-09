package com.yicj.demo.io.file2;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class IntBufferDemo {
	private static final int BSIZE = 1024 ;
	
	public static void main(String[] args) {
		ByteBuffer bb = ByteBuffer.allocate(BSIZE) ;
		IntBuffer ib = bb.asIntBuffer() ;
		//store an array of int :
		ib.put(new int[] {11,42,47,99,143,811,1016}) ;
		// absolute location read and write:
		System.out.println("index[3] : " + ib.get(3));
		System.out.println("----------------------");
		ib.put(3, 1811) ;
		//setting a new limit before rewinding the buffer.
		//将Buffer从写模式切换到读模式（必须调用这个方法）
		ib.flip() ;
		while(ib.hasRemaining()) {
			int i = ib.get() ;
			System.out.println(i);
		}
	}
}
