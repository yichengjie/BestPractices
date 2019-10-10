package com.yicj.file.file0;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class CharUtil {
	
	public static void dump(Reader src, Writer dest) throws IOException{
		
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

}
