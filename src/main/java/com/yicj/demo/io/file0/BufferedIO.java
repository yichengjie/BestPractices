package com.yicj.demo.io.file0;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BufferedIO {
	
	public static void dump(InputStream src, OutputStream dest) throws IOException {
		InputStream input = null ;
		OutputStream output = null ;
		//如果InputStream第一次read()时，可以尽量读取足够的数据至内存的缓存区，后续调用read()时
		//先看看缓存区是不是还有数据，如果有就从缓存区读取，没有在从源读取数据至缓存区，
		//这样减少从源直接读取数据的次数，对读取效率会有帮助
		try {
			input = new BufferedInputStream(src) ;
			output = new BufferedOutputStream(dest) ;
			byte [] data = new byte[1204] ;
			int len ;
			while((len = input.read(data)) != -1)
				output.write(data, 0, len);
		}finally {
			input.close(); 
			output.close();
		}
		
	}
}
