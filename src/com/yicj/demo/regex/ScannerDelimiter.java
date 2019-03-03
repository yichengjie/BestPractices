package com.yicj.demo.regex;

import java.util.Scanner;

public class ScannerDelimiter {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner("12, 42, 78, 99, 42") ;
		//使用逗号（包括逗号前后任意的空白字符）作为定界符
		scanner.useDelimiter("\\s*,\\s*") ;
		while(scanner.hasNextInt()) {
			System.out.println(scanner.nextInt());
		}
	}
}
