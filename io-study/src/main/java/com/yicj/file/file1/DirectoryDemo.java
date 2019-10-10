package com.yicj.file.file1;

import static com.yicj.common.util.CommonUtil.println;

import java.io.File;

import com.yicj.common.util.Directory;
import com.yicj.common.util.PPrint;

public class DirectoryDemo {
	
	public static void main(String[] args) {
		
		//All directories
		PPrint.pprint(Directory.walk(".").dirs);
		//All files beginning with 'T'
		for(File file : Directory.local(".", "T.*")) {
			println(file) ;
		}
		println("----------------------------------") ;
		// All Java files beginning with 'T'
		for(File file : Directory.walk(".","T.*\\.java")) {
			println(file) ;
		}
		println("===================================") ;
		//Class files containing 'Z' or 'z'
		for(File file : Directory.walk(".",".*[Zz].*\\.class")) {
			println(file) ;
		}
	}

}
