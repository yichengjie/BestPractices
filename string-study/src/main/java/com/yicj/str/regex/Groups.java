package com.yicj.str.regex;

import static com.yicj.study.common.util.CommonUtil.print;
import static com.yicj.study.common.util.CommonUtil.println;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Groups {
	static public final String POEM = 
		"Twas brillig, and the slithy toves\n"
		+ "Did gyre and gimble in the wabe.\n"
		+ "All mimsy were the borogoves,\n"
		+ "And the mome raths outgrabe.\n\n"
		+ "Beware the Jabberwock, my sonm,\n"
		+ "The jaws that bite, the claws that catch.\n"
		+ "Beware the Jubjub bird, and shun\n"
		+ "The frumious Bandersnatch." ;
	
	public static void main(String[] args) {
		test1() ;
		//test2() ;
	}
	
	public static void test2() {
		Pattern p = Pattern.compile("(\\d{3,5})([a-z]{2})");
		String input = " 123aa-34345bb-234cc-00" ;
		Matcher m = p.matcher(input) ;
		while(m.find()) {
			System.out.println("m.group():" + m.group()); //打印所有
			System.out.println("m.group(1):" + m.group(1));//打印数字的
			System.out.println("m.group(2):" + m.group(2));//打印字母的
			System.out.println();
		}
	}
	
	
	
	public static void test1() {
		String regex = "(?m)(\\S+)\\s+((\\S+)\\s+(\\S+))$" ;
		Pattern compile = Pattern.compile(regex);
		Matcher matcher = compile.matcher(POEM);
		while(matcher.find()) {
			for(int j = 0 ; j < matcher.groupCount() ; j++) {
				print("["+matcher.group(j)+"]");
			}
			println("");
		}
	}

}
