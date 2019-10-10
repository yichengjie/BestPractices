package com.yicj.demo.io.file0;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IO {
	public static void dump(InputStream src, OutputStream dest) throws IOException {
		//InputStream的read()方法，每次会尝试读入byte数组长度的数据，
		//并返回实际读入的字节，只要不是-1，就表示读取到数据
		try {
			byte [] data = new byte[1024] ;
			int len ;
			while( (len = src.read(data)) != -1 )
				dest.write(data, 0, len);
		}finally {
			src.close(); 
			dest.close();
		}
	}
}
