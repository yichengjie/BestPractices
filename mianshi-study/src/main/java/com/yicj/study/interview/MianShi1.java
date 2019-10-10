package com.yicj.study.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MianShi1 {
	
	public static void sortIntergerAsc(){
		List<Integer> list = Arrays.asList(4,5,1,3,9,6) ;
		Collections.sort(list, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
	}
	
	public static void print1000() {
		List<String> list = new  ArrayList<String>() ;
		list.add("") ;
		list.add("0") ;
		list.add("00") ;
		list.add("000") ;
		list.add("0000") ;
		list.add("00000") ;
		list.add("000000") ;
		list.add("0000000") ;
		list.add("00000000") ;
		
		for(int i = 1 ; i <= 1000 ; i++) {
			String tmp = String.valueOf(i) ;
			int len = 9 - tmp.length() ;
			StringBuilder sb = new StringBuilder("") ;
			sb.append("A").append(list.get(len)).append(i) ;
			System.out.println(sb.toString());
		}
	}
	
	public static void main(String[] args) {
		
	}
}
