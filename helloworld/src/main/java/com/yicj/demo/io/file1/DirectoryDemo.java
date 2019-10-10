package com.yicj.demo.io.file1;

import java.io.File;
import static com.yicj.demo.common.util.CommonUtil.println;

import com.yicj.demo.common.util.Directory;
import com.yicj.demo.common.util.PPrint;

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
