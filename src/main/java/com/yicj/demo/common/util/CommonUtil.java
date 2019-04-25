package com.yicj.demo.common.util;

public class CommonUtil {

	public static void println(Object obj) {
		if(obj!=null) {
			System.out.println(obj.toString());
		}else {
			System.out.println("null");
		}
	}
	
	public static void print(Object obj) {
		if(obj!=null) {
			System.out.print(obj.toString());
		}else {
			System.out.print("null");
		}
	}

}
