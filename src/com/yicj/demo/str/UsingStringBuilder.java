package com.yicj.demo.str;

import java.util.Random;

public class UsingStringBuilder {
	
	public static Random rand = new Random(47) ;
	public String toString() {
		StringBuilder result = new StringBuilder("[") ;
		for(int i = 0 ; i < 25 ; i++) {
			//result.append(rand.nextInt(100)) ;
			//result.append(", ") ;
			result.append(rand.nextInt(100) +", ") ;
		}
		result.delete(result.length()-2, result.length()) ;
		result.append("]") ;
		return result.toString() ;
	}
	
	public void test() {
		String xx = "bbb" ;
		String yy = "aa" ;
		String str = xx +yy ;
		System.out.println(str);
	}
	
	public static void main(String[] args) {
		UsingStringBuilder usb = new UsingStringBuilder() ;
		System.out.println(usb);
	}

}
