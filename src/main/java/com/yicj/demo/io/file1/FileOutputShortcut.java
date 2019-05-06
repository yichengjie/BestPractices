package com.yicj.demo.io.file1;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;

public class FileOutputShortcut {
	
	static String file = "FileOutputShortcut.java" ;
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(
				new StringReader(BufferedInputFile.read(file))) ;
		PrintWriter out = new PrintWriter(file) ;
		int lineCount = 1; 
		String s ;
		while( (s = in.readLine()) != null )
			out.println(lineCount ++ + ": " + s);
		out.close(); 
		System.out.println(BufferedInputFile.read(file));
	}

}
