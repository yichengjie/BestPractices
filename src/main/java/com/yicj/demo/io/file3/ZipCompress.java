package com.yicj.demo.io.file3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Enumeration;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipCompress {
	
	public static void main(String[] args) throws Exception {
		
		String [] arr = {"data2.txt","data3.txt","data4.txt"} ;
		
		writefile(arr);
		readfile(arr);
		readfile2();
		
		
	}
	
	private static void readfile(String[] args)  throws Exception{
		//New extract the files:
		System.out.println("Reading file1");
		FileInputStream fi = new FileInputStream("test.zip") ;
		CheckedInputStream csumi = 
			new CheckedInputStream(fi, new Adler32()) ;
		ZipInputStream in2 = new ZipInputStream(csumi) ;
		BufferedInputStream bis = new BufferedInputStream(in2) ;
		ZipEntry ze ;
		while( (ze = in2.getNextEntry()) != null ) {
			System.out.println("Read file1 " + ze);
			int x ;
			while( (x = bis.read()) != -1 )
				System.out.write(x);
		}
		if(args.length == 1) {
			System.out.println("Checksum : " + csumi.getChecksum().getValue());
		}
		bis.close(); 
	}
	
	private static void readfile2() throws Exception {
		//Alternative way to open and read zip files
		ZipFile zf = new ZipFile("test.zip") ;
		Enumeration e = zf.entries() ;
		while(e.hasMoreElements()) {
			ZipEntry ze2 = (ZipEntry)e.nextElement() ;
			System.out.println("File : " + ze2);
			// ... and extract the data as before
		}
		// if (args.length ==1)
	}

	private static void writefile(String[] args) throws Exception {
		FileOutputStream f = new FileOutputStream("test.zip") ;
		CheckedOutputStream csum = 
				new CheckedOutputStream(f, new Adler32()) ;
		ZipOutputStream zos = new ZipOutputStream(csum) ;
		BufferedOutputStream out = new BufferedOutputStream(zos) ;
		zos.setComment("A test of Java Zipping");
		// No corresponding getComment() ,though
		for(String arg : args) {
			System.out.println("Writing file1 " + arg) ;
			BufferedReader in = new BufferedReader(
					new FileReader(arg)) ;
			zos.putNextEntry(new ZipEntry(arg));
			int c ;
			while( (c = in.read())!= -1 )
				out.write(c);
			in.close();
			out.flush();
		}
		out.close();
		// checksum valid only after the file1 has bean closed!
		System.out.println("Checksum : " + csum.getChecksum().getValue());
	}

}
