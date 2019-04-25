package com.yicj.demo.str.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestReplacement {
	
	public static void main(String[] args) {
		
		//test1();
		test2() ;
	}
	
	
	public static void test1() {
		String input = "one cat two cats in the yard";
		Pattern p = Pattern.compile("cat") ;
		Matcher m = p.matcher(input) ;
		StringBuffer sb = new StringBuffer() ;
		while(m.find()) {
			//System.out.println(m.group());
			m.appendReplacement(sb, "dog") ;
		}
		m.appendTail(sb) ;
		System.out.println(sb.toString());
	}
	
	public static void test2() {
		
		String input = "i java jAva  java java java  java  you heate jAva asdfasdaf" ;
		Pattern pt = Pattern.compile("java",Pattern.CASE_INSENSITIVE) ;
		Matcher mc = pt.matcher(input);
		int i = 0 ;
		int num = 0 ;
		StringBuffer buf = new StringBuffer() ;
		while(mc.find()) {
			//mc.find() ;
			if(num ++ % 2 ==0) {
				mc.appendReplacement(buf, "JAVA") ;
			}
		}
		mc.appendTail(buf) ;
		System.out.println(buf);
		System.out.println(i);
		
		
	}

}
