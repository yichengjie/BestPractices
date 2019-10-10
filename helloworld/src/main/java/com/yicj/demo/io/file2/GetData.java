package com.yicj.demo.io.file2;

import java.nio.ByteBuffer;

public class GetData {
	
	private static final int BSIZE = 1024 ;
	public static void main(String[] args) {
		
		ByteBuffer bb = ByteBuffer.allocate(BSIZE) ;
		// alloction automatically zeroes the byteBuffer:
		int i = 0 ;
		while(i++ < bb.limit())
			if(bb.get() != 0) {
				System.out.println("nonzero");
			}
		System.out.println("i = " + i);
		bb.rewind() ;
		// Store and read a char array
		bb.asCharBuffer().put("Howdy!") ;
		char c  ;
		while( (c=bb.getChar()) != 0 )
			System.out.print(c+ " ");
		System.out.println();
		bb.rewind() ;
		////////////////////////////////
		//Store and read a short
		bb.asShortBuffer().put( (short)471142 ) ;
		System.out.println(bb.getShort());
		bb.rewind() ;
		/////////////////////////////////
		//store and read long
		bb.asLongBuffer().put(99471142) ;
		System.out.println(bb.getLong());
		bb.rewind() ;
		/////////////////////////////////
		//store and read a float
		bb.asFloatBuffer().put(99471142) ;
		System.out.println(bb.getFloat());
		bb.rewind() ;
		/////////////////////////////////
		//store and read a double
		bb.asDoubleBuffer().put(99471142) ;
		System.out.println(bb.getDouble());
		bb.rewind();
	}
}
