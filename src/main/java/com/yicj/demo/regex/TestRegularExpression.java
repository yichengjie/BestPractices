package com.yicj.demo.regex;
import static com.yicj.demo.util.CommonUtil.println;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class TestRegularExpression {
	
	public static void main(String[] args) {
		test("abcabcabcdefabc","abc+","(abc)+","(abc){2,}");
		
	}
	
	public static void test(String ...args) {
		if(args.length < 2) {
			println("Usage:\njava TestRegularExpression "
			  + "characterSequence regularExpression+");
			System.exit(0);
		}
		println("Input: \"" + args[0] + "\"");
		for(String arg : args) {
			println("Regular expression: \"" + arg + "\"");
			Pattern p = Pattern.compile(arg) ;
			Matcher m = p.matcher(args[0]);
			while(m.find()) {
				println("Match \"" + m.group() + "\" at positions " +
						m.start() + "-" + (m.end() -1));
			}
			
		}
	}

}
