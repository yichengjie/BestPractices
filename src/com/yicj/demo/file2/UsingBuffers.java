package com.yicj.demo.file2;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class UsingBuffers {
	
	//这个运行有报错没有查明原因
	private static void symmetricScramble(CharBuffer buffer) {
		while(buffer.hasRemaining()) {
			buffer.mark() ;
			char c1 = buffer.get() ;
			char c2 = buffer.get() ;
			buffer.reset() ;
			buffer.put(c2).put(c1) ;
		}
	}
	
	public static void main(String[] args) {
		char [] data = "ByteBuffers".toCharArray() ;
		ByteBuffer bb = ByteBuffer.allocate(data.length * 2) ;
		CharBuffer cb = bb.asCharBuffer() ;
		cb.put(data) ;
		System.out.println(cb.rewind());
		symmetricScramble(cb);
		System.out.println(cb.rewind());
		symmetricScramble(cb);
		System.out.println(cb.rewind());
	}

}
