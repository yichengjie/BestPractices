package com.yicj.demo.str;
import static com.yicj.demo.util.CommonUtil.println; 


public class Immutable {

	public static String upcase(String s) {
		return s.toUpperCase() ;
	}
	
	public static void main(String[] args) {
		String q = "howdy" ;
		println(q);
		String qq = upcase(q) ;
		println(qq);
		println(q);
	}
}
