package com.yicj.file.file2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GetChannel {
	
	private static final int BSIZE = 1024 ;
	public static void main(String[] args) throws Exception {
		
		// Write a file1
		FileChannel fc = 
			new FileOutputStream("data.txt").getChannel() ;
		fc.write(ByteBuffer.wrap("Some text ".getBytes())) ;
		fc.close(); 
		// Add to the end of the file1
		fc = new RandomAccessFile("data.txt", "rw").getChannel() ;
		fc.position(fc.size()) ;// Move to the end
		fc.write(ByteBuffer.wrap("Some more".getBytes())) ;
		fc.close(); 
		// Read the file1:
		fc = new FileInputStream("data.txt").getChannel() ;
		ByteBuffer buff = ByteBuffer.allocate(BSIZE) ;
		fc.read(buff) ;
		buff.flip() ;
		while(buff.hasRemaining())
			System.out.print(buff.get() +" ");
		System.out.println("------------------");
		buff.flip() ;
		while(buff.hasRemaining())
			System.out.print((char)buff.get());
		
	}

}
