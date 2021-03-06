package com.yicj.str.regex;
import static com.yicj.common.util.CommonUtil.println;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StartEnd {
	public static String input = 
		"As long as there is injusticem whenever a\n"
		+ "Targathian baby cries out, wherever a distress\n"
		+ "signal sounds among the starts ... We'll be there.\n"
		+ "This fine ship, and this fine crew ...\n"
		+ "Never give up! Never surrender!" ;
	private static class Display{
		private boolean regexPrinted = false; 
		private String regex ;
		Display(String regex) {
			this.regex = regex ;
		}
		void display(String message) {
			if(!regexPrinted) {
				println(regex) ;
				regexPrinted = true ;
			}
			println(message) ;
		}
	}
	
	static void examine(String s, String regex) {
		Display d = new Display(regex) ;
		Pattern p = Pattern.compile(regex) ;
		Matcher m = p.matcher(s) ;
		while(m.find()) {
			d.display("find() '" + m.group()+
				"' start = " + m.start() + " end = " + m.end());
//			if(m.lookingAt()) {//No reset() necessary
//				d.display("lookingAt() start = " 
//				  +m.start() + " end = " + m.end() );
//			}
//			if(m.matches()) {//No reset() necessary
//				d.display("matches() start = "
//					+ m.start() + " end = " + m.end());
//			}
		}
	}
	
	public static void main(String[] args) {
		for(String in : input.split("\n")) {
			println("input : " + in) ;
			for(String regex : new String[] {"\\w*ere\\w*",
				"\\w*ever","T\\w+","Never.*?!"}) {
				examine(in, regex);
			}
		}
	}
	
	
	
}
