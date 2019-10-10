package com.yicj.demo.io.file0;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class CharUtil3 {
	//FileReader/RileWriter则可以对文档做读取与写入，
	//读取或写入时默认会使用操作系统默认编码来做字符转化，
	//在启动jvm时，可以指定-Dfile.encoding来指定FileReader/FileWriter所使用的编码
	//FileReader/FileWriter没有可以指定编码的方法，如果在程序执行过程中想要指定比编码，
	//则必须使用InputStreamReader/OutputStreamWriter/者两个类实际上时作为装饰器。
	
	
	public static void dump(Reader src, Writer dest) 
			throws IOException{
		BufferedReader input = null ;
		BufferedWriter output = null ;
		try {
			input = new BufferedReader(src) ;
			output = new BufferedWriter(dest) ; 
			char [] data = new char[1024] ;
			int len; 
			while( (len = input.read(data)) != -1 )
				output.write(data, 0, len);
		} finally {
			input.close();
			output.close();
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
