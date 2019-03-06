package com.yicj.demo.file2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class BufferToText  {
	
	private static final int BSIZE = 1024 ;
	public static void main(String[] args) throws Exception {
		decode1();
		// Or, we could encode with something that will print:
		//decode2();
		//Use a CharBuffer to write through
		//decode3();
		
	}
	
	private static void decode1() throws FileNotFoundException, IOException {
		FileChannel fc = 
			new FileOutputStream("data2.txt").getChannel() ;
		fc.write(ByteBuffer.wrap("Some text".getBytes())) ;
		fc.close(); 
		fc = new FileInputStream("data2.txt").getChannel() ;
		ByteBuffer buff = ByteBuffer.allocate(BSIZE) ;
		fc.read(buff) ;
		buff.flip() ;
		//Doesn't work:
		System.out.println("1 : " + buff.asCharBuffer());
		//Decode using this system't default Charset:
		buff.rewind() ;
		//encoding = "UTF-16BE" ;
		//encoding = "GBK" ;
		//encoding = "GB2312" ;
		//encoding = "UTF-8" ;
		String encoding = System.getProperty("file.encoding") ;
		System.out.println("2 : Decoded using " + encoding +": " +
			Charset.forName(encoding).decode(buff));
	}
	private static void decode2()
			throws FileNotFoundException, IOException, UnsupportedEncodingException {
		ByteBuffer buff = ByteBuffer.allocate(BSIZE) ;
		FileChannel fc = new FileOutputStream("data2.txt").getChannel() ;
		fc.write(ByteBuffer.wrap("Some text".getBytes("UTF-16BE"))) ;
		fc.close(); 
		fc = new FileInputStream("data2.txt").getChannel() ;
		buff.clear() ;
		fc.read(buff) ;
		buff.flip() ;
		System.out.println("3 : "+ buff.asCharBuffer());
	}
	
	private static void decode3() throws FileNotFoundException, IOException {
		FileChannel fc = new FileOutputStream("data2.txt").getChannel() ;
		ByteBuffer buff = ByteBuffer.allocate(24) ;
		buff.asCharBuffer().put("Some text") ;
		fc.write(buff) ;
		fc.close(); 
		//Read and display
		fc = new FileInputStream("data2.txt").getChannel() ;
		buff.clear() ;
		fc.read(buff) ;
		buff.flip() ;
		System.out.println("4 : "+ buff.asCharBuffer());
	}
	
	
}
