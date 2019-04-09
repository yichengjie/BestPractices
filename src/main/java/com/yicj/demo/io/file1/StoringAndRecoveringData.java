package com.yicj.demo.io.file1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class StoringAndRecoveringData {
	
	public static void main(String[] args) throws Exception {
		
		DataOutputStream out = new DataOutputStream(
			new BufferedOutputStream(
				new FileOutputStream("Data.txt"))) ;
		out.writeDouble(3.14159);
		out.writeUTF("That was pi");
		out.writeDouble(1.41413);
		out.writeUTF("Square root of 2");
		out.close(); 
		//////////////////////////////////////////////
		DataInputStream in = new DataInputStream(
			new BufferedInputStream(
				new FileInputStream("Data.txt"))) ;
		System.out.println(in.readDouble());
		System.out.println(in.readUTF());
		System.out.println(in.readDouble());
		System.out.println(in.readUTF());
		in.close();
	}
}
