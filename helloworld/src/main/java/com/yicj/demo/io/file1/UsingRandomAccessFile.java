package com.yicj.demo.io.file1;

import java.io.IOException;
import java.io.RandomAccessFile;

public class UsingRandomAccessFile {
	
	static String file = "rtest.dat" ;
	static void display() throws IOException {
		
		RandomAccessFile rf = new RandomAccessFile(file, "r") ;
		for(int i = 0 ; i < 7 ;i ++) {
			System.out.println("Value " + i +": " + rf.readDouble());
		}
		System.out.println(rf.readUTF());
		rf.close();
	}
	
	public static void main(String[] args) throws IOException {
		RandomAccessFile rf = new RandomAccessFile(file, "rw") ;
		for(int i = 0 ; i < 7 ;i++)
			rf.writeDouble(i*1.414);
		rf.writeUTF("The end of the file1");
		rf.close(); 
		System.out.println("-------------------------");
		display();
		rf = new RandomAccessFile(file, "rw") ;
		rf.seek(5*8);
		rf.writeDouble(47.0001);
		rf.close();
		System.out.println("-------------------------");
		display();
	}
	

}
