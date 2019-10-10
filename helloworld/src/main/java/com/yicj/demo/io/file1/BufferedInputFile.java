package com.yicj.demo.io.file1;

import java.io.BufferedReader;
import java.io.FileReader;

public class BufferedInputFile {
	
	public static String read(String filename) throws Exception {
		BufferedReader in = 
				new BufferedReader(new FileReader(filename)) ;
		String s ; 
		StringBuilder sb = new StringBuilder() ;
		while((s = in.readLine())!= null) {
			sb.append(s +"\n") ;
		}
		in.close(); 
		return sb.toString() ;
	}
	

}
