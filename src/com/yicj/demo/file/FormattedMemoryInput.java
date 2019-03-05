package com.yicj.demo.file;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class FormattedMemoryInput {
	public static void main(String[] args)  {
		
		try {
			DataInputStream in = new DataInputStream(
					new ByteArrayInputStream(
						BufferedInputFile.read("FormattedMemoryInput.java").getBytes())) ;
				while(true)
					System.out.println( (char)in.readByte() );
		} catch (Exception e) {
			System.err.println("End of stream");
		}
		
	}
}
