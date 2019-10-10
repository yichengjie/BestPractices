package com.yicj.file.file1;

import java.io.File;
import java.io.IOException;

import com.yicj.common.util.Directory;

public class ProcessFiles {
	
	public interface Strategy{
		void process(File file) ;
	}
	
	private Strategy strategy ;
	private String ext ;
	public ProcessFiles(Strategy strategy, String ext) {
		this.strategy = strategy ;
		this.ext = ext ;
	}
	public void start(String [] args) {
		try {
			
			if(args.length ==0) 
				processDirectoryTree(new File(".")) ;
			else 
				for(String arg : args) {
					File fileArg = new File(arg) ;
					if(fileArg.isDirectory()) {
						processDirectoryTree(fileArg);
					}else {
						if(!arg.endsWith("." + ext)) {
							arg += "." + ext ;
						}
						strategy.process(
							new File(arg).getCanonicalFile());
					}
				}
		}catch(Exception e) {
			throw new RuntimeException(e) ;
		}
	}
	private void processDirectoryTree(File root) throws IOException {
		for(File file : Directory.walk(
				root.getAbsolutePath(),".*\\." + ext)) {
			strategy.process(file.getCanonicalFile());
		}
	}
	public static void main(String[] args) {
		
		new ProcessFiles(new Strategy() {
			private int count = 0 ;
			@Override
			public void process(File file) {
				System.out.println( ++count  +" : " + file);
			}
		}, "java").start(args); ;
	}
	

}
