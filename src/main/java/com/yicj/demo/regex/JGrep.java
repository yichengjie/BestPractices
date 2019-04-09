package com.yicj.demo.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JGrep {
	
	public static void main(String[] args) {
		
		if(args.length < 2) {
			System.out.println("Usage: java JGrep file1 regex");
			System.exit(0);
		}
		
		Pattern p = Pattern.compile(args[1]) ;
		// Iterate through the lines of the input file1:
		int index = 0 ;
		Matcher m = p.matcher("") ;
		for(String line : new TextFile(args[0]).lines()) {
			m.reset(line) ;
			while(m.find()) {
				System.out.println(index ++ + ": " + m.group() + ": " + m.start());
			}
		}
		
	}

}
