package com.yicj.demo.io.file0;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class CharUtil2 {
	
	public static void dump(Reader src, Writer dest) 
			throws IOException{
		
		try {
			char [] data = new char[1024] ;
			int len; 
			while( (len = src.read(data)) != -1 )
				dest.write(data, 0, len);
		} finally {
			src.close();
			dest.close();
		}
	}
	
	public static void dump(InputStream src, OutputStream dest,
			String charset) throws IOException {
		dump(new InputStreamReader(src, charset), 
			new OutputStreamWriter(dest,charset));
	}
	
	public static void dump(InputStream src, OutputStream dest) throws IOException {
		dump(src, dest,System.getProperty("file1.encoding"));
	}
}
