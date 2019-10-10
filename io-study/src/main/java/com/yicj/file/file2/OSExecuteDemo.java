package com.yicj.file.file2;

import java.io.IOException;
import java.net.URL;

public class OSExecuteDemo {
	
	public static void main(String[] args) throws IOException {
		
		URL resource = OSExecuteDemo.class.getResource("");
		String file = resource.getFile();
		String path = resource.getPath();
		Object content = resource.getContent();
		System.out.println("path : " + path);
		System.out.println("file1 : " + file);
		System.out.println("content : "+ content );
		OSExecute.command("javap "+path+"OSExecuteDemo.class");
	}
	
}
