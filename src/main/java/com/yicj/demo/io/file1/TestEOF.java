package com.yicj.demo.io.file1;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

public class TestEOF {
	
	//如果从DataInputStream用readByte()一次一个字节的读取字符，那么任何字节的值都是合法的结果，
	//因此返回值不能用来检测是否结束，相反，我们可以使用avaliable方法查看还有多少可供存取的字符。
	//下面这个例子演示了怎么一次一个字节的读取文件
	public static void main(String[] args) throws Exception {
		
		DataInputStream in = new DataInputStream(
			new BufferedInputStream(
				new FileInputStream("TestEOF.java")) ) ;
		while(in.available() != 0)
			System.out.println( (char)in.readByte());
		
		//注意avaliable()的////
		
	}

}
