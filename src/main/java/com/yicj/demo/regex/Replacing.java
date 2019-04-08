package com.yicj.demo.regex;
import static com.yicj.demo.util.CommonUtil.println;

public class Replacing {
	
	static String s = Splitting.knights ;
	
	public static void main(String[] args) {
		println(s.replaceFirst("f\\w", "located"));
		println(s.replaceAll("shrubbery|tree|herring", "banana"));
		
	}
	
	
}
