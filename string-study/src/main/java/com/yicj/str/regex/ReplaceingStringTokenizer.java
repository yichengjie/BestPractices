package com.yicj.str.regex;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ReplaceingStringTokenizer {
	
	public static void main(String[] args) {
		
		String sp = "//////////////////////////////////////////////////" ;
		String input = "But I'm not dead yet! I feel happy!" ;
		StringTokenizer stoke = new StringTokenizer(input) ;
		while(stoke.hasMoreElements()) {
			System.out.println(stoke.nextToken() + " ");
		}
		System.out.println(sp);
		//////////////////////////////////////////////////
		System.out.println(Arrays.toString(input.split(" ")));
		System.out.println(sp);
		/////////////////////////////////////////////////
		Scanner scanner = new Scanner(input) ;
		while(scanner.hasNext()) {
			System.out.println(scanner.next() + " ");
		}
		
	}

}
