package com.yicj.demo.str.regex;
import static com.yicj.demo.common.util.CommonUtil.println;

public class Replacing {
	
	static String s = Splitting.knights ;
	
	public static void main(String[] args) {
		println(s.replaceFirst("f\\w", "located"));
		println(s.replaceAll("shrubbery|tree|herring", "banana"));
		
	}
	
	
}
