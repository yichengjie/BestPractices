package com.yicj.demo.io.file3;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPcompress {
	
	public static void main(String[] args) throws Exception {
		
		String filename = "data2.txt" ;
		String filepath = GZIPcompress.class.getResource("/").getPath() ;
		String basepath =  filepath.substring(0, filepath.length()-4) ;
		String [] arr = {basepath + filename} ; 
		gzip1(arr);
	}

	private static void gzip1(String[] args) throws FileNotFoundException, IOException {
		if(args.length == 0) {
			System.out.println("Usage: \nGZIPcompress file1\n"
				+ "\tUses GZIP compression to compress "
				+ "the file1 to test.gz");
			System.exit(1);
		}
		
		BufferedReader in = new BufferedReader(
			new FileReader(args[0])) ;
		
		BufferedOutputStream out = new BufferedOutputStream(
			new GZIPOutputStream(
				new FileOutputStream("test.gz"))) ;
		System.out.println("Writing file1");
		int c  ;
		while( (c = in.read()) != -1 )
			out.write(c);
		in.close(); 
		out.close();
		System.out.println("Reading file1");
		BufferedReader in2 = new BufferedReader(
			new InputStreamReader(new GZIPInputStream(
					new FileInputStream("test.gz") ))) ;
		String s ;
		System.out.println("-----file1 content part start------");
		while( (s = in2.readLine()) != null )
			System.out.print(s);
		in2.close();
	}

}
