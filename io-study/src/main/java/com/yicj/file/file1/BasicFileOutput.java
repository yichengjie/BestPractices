package com.yicj.file.file1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringReader;

public class BasicFileOutput {
	static String path = "/Users/yicj/code/eclipse-workspace/BestPractices/src/com/yicj/demo/file1/" ;
	static String file = "BasicFileOutput.out" ;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(
			new StringReader(
				BufferedInputFile.read(path + "BasicFileOutput.java"))) ;
		PrintWriter out = new PrintWriter(
			new BufferedWriter(new FileWriter(file))) ;
		int lineCount = 1 ;
		String s ;
		while( (s = in.readLine()) != null )
			out.println(lineCount ++ +": " + s);
		out.close(); 
		System.out.println(BufferedInputFile.read(file));
	}
}
