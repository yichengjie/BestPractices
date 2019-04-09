package com.yicj.demo.io.file1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class TextFile extends ArrayList<String>{
	
	public static String read(String filename) {
		StringBuilder sb = new StringBuilder() ;
		
		
		try {
			BufferedReader in = new BufferedReader(
					new FileReader(new File(filename).getAbsolutePath())) ;
			try {
				String s ;
				while( (s = in.readLine()) != null) {
					sb.append(s) ;
					sb.append("\n") ;
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e) ;
		}
		
		return sb.toString() ;
	}
	
	public static void write(String filename,String text) {
		
		try {
			PrintWriter out = new PrintWriter(
					new File(filename).getAbsolutePath()) ;
			try {
				out.print(text);
			} finally {
				out.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e) ;
		}
	}
	
	public TextFile(String filename,String splitter) {
		super(Arrays.asList(read(filename).split(splitter))) ;
		if(get(0).equals(""))
			remove(0) ;
	}
	
	public TextFile(String filename) {
		this(filename, "\n") ;
	}
	
	public void write(String filename) {
		try {
			PrintWriter out = new PrintWriter(
					new File(filename).getAbsolutePath()) ;
			try {
				for(String item : this)
					out.println(item);
			} finally {
				out.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e) ;
		}
	}
	
	public static void main(String[] args) {
		String file = read("TextFile.java") ;
		write("test.txt", file);
		TextFile text = new TextFile("test.txt") ;
		text.write("test2.txt");
		TreeSet<String> words = new TreeSet<String>(
			new TextFile("TextFile.java","\\W+")) ;
		System.out.println(words.headSet("a"));
		
		
		
	}
	
}
