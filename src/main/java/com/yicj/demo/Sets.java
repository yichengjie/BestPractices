package com.yicj.demo;

import java.util.HashSet;
import java.util.Set;

public class Sets {
	//将两个集合 合并
	public static <T> Set<T> union(Set<T> a, Set<T> b){
		Set<T> result = new HashSet<T>(a) ;
		result.addAll(b) ;
		return result ;
	}
	//两个集合求交集
	public static <T> Set<T> intersection(Set<T> a ,Set<T> b){
		Set<T> result = new HashSet<T>(a) ;
		result.retainAll(b) ;
		return result ;
	}
	//从superset中移除subset包含的元素
	public static <T> Set<T> difference(Set<T> superset, Set<T> subset){
		Set<T> result = new HashSet<T>(superset) ;
		//System.out.println("superset: " + superset);
		//System.out.println("subset: " + subset);
		result.removeAll(subset) ;
		return result ;
	}
	//包含交集之外的所有元素
	public static <T> Set<T> complement(Set<T> a, Set<T> b){
		return difference(union(a, b), intersection(a, b)) ;
	}
}
