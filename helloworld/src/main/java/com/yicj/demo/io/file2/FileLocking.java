package com.yicj.demo.io.file2;

import java.io.FileOutputStream;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

public class FileLocking {
	
	public static void main(String[] args) throws Exception {
		FileOutputStream fos = new FileOutputStream("file1.txt") ;
		FileLock fl = fos.getChannel().tryLock() ;
		if(fl != null) {
			System.out.println("Locked File");
			TimeUnit.MILLISECONDS.sleep(100);
			fl.release(); 
			System.out.println("Releaseed Lock");
		}
		fos.close(); 
	}

}
