package com.yicj.demo.file;

import java.io.StringReader;

public class MemoryInput {
	
	public static void main(String[] args) throws Exception {
		StringReader in = new StringReader(
				BufferedInputFile.read("MemoryInput.java")) ;
		int c ;
		while( (c = in.read()) != -1 )
			System.out.println((char)c);
	}
}
