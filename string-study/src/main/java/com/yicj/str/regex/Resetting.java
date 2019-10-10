package com.yicj.str.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Resetting {
	
	public static void main(String[] args) {
		String input = "fix the rug with bags" ;
		Matcher m = Pattern.compile("[frb][aiu][gx]").matcher(input) ;
		while(m.find()) {
			System.out.print(m.group() + " ");
		}
		System.out.println();
		m.reset("fix the rig with rags") ;
		while(m.find()) {
			System.out.print(m.group() +" ");
		}
	}
}
