package com.yicj.str.regex;

import static com.yicj.common.util.CommonUtil.println;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TheReplacements {
	
	public static void main(String[] args) {
		
		String s = TextFile.read("TheReplacements.java") ;
		// Match the specially commented block of text above:
		String regex = "/\\*!(.*)!\\*/" ;
		Matcher mInput = Pattern.compile(regex,Pattern.DOTALL).matcher(s) ;
		if(mInput.find()) {
			s = mInput.group(1) ;//Captured by parentheses
		}
		// Replace two or more spaces with a single space:
		// line with no spaces. Must enable Multiline mode:
		s = s.replaceAll("(?m)^ +", "") ;
		println(s) ;
		s = s.replaceFirst("[aeiou]", "(VOEWL1)") ;
		StringBuffer sbuf = new StringBuffer() ;
		Pattern p = Pattern.compile("[aeiou]") ;
		Matcher m = p.matcher(s) ;
		// Process the find information as you
		// perform the replacements:
		while(m.find()) {
			m.appendReplacement(sbuf, m.group().toUpperCase()) ;
		}
		// Put in the reminder of the text:
		m.appendTail(sbuf) ;
		println(sbuf) ;
		
	}
	
	
}
