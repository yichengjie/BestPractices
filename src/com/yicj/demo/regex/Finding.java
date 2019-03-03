package com.yicj.demo.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.yicj.demo.CommonUtil.print;
import static com.yicj.demo.CommonUtil.println;

public class Finding {
	
	public static void main(String[] args) {
		Matcher m = Pattern.compile("\\w+")
		 .matcher("Evening is full of the linnet's wings") ;
		while(m.find()) {
			print(m.group() +" ");
		}
		println("") ;
		int i = 0 ;
		while(m.find(i)) {
			print(m.group() +" ");
			i++ ;
		}
		
		
		
		
	}

}
