package com.yicj.demo.stream.stage1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
//https://juejin.im/post/5cc124a95188252d891d00f2
public class StreamDemo1 {
	
	public static void main(String[] args) {
		//test001();
		//test002();
		//test003();
		//test004();
		test005() ;
	}
	
	public static void test005() {
		Stream.of(1.0,2.0,3.0)
		.mapToInt(Double::intValue)
		.mapToObj(i -> "a" + 1)
		.forEach(System.out::println);
	}
	
	public static void test004() {
		IntStream.range(1, 4)
		.mapToObj(i -> "a" + i)
		.forEach(System.out::println);
	}

	public static void test003() {
		Arrays.stream(new int[] {1,2,3})
		.map(n -> 2 * n +1)
		.average()
		.ifPresent(System.out::println);;
	}
	
	private static void test002() {
		IntStream.range(1, 4)
		.forEach(System.out::println);
	}
	

	private static void test001() {
		List<String> myList =
			    Arrays.asList("a1", "a2", "b1", "c2", "c1");
		myList.stream()
		.filter(s->s.startsWith("c"))
		.map(String::toUpperCase)
		.sorted()
		.forEach(System.out::println);
	}

}
