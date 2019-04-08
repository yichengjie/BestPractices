package com.yicj.demo.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Redirecting {
	
	public static void main(String[] args) throws Exception {
		
		String path = "/Users/yicj/code/eclipse-workspace/BestPractices/src/com/yicj/demo/file/" ;
		
		PrintStream console = System.out ;
		BufferedInputStream in = new BufferedInputStream(
			new FileInputStream(path + "Redirecting.java")) ;
		PrintStream out = new PrintStream(
			new BufferedOutputStream(
				new FileOutputStream("test.out"))) ;
		System.setIn(in);
		System.setOut(out);
		System.setErr(out);
		
		BufferedReader br = new BufferedReader(
			new InputStreamReader(System.in)) ;
		String s ;
		while( (s = br.readLine()) != null )
			System.out.println(s);
		out.close();
		//开头处存储了了对最初的System.out对象的引用，并切在结尾处将系统输出恢复到了该对象上
		System.setOut(console);
		System.out.println("hello world");
		
	}
	
}
