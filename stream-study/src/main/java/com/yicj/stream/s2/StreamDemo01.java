package com.yicj.stream.s2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//https://www.cnblogs.com/andywithu/p/7404101.html
public class StreamDemo01 {
	static List<String> wordList = init() ;
	
	public static void main(String[] args) {
		//test001();
		//test002();
		//test004();
		//test005();
		//test006();
		//test007();
		//test008() ;
		test009() ;
	}
	
	private static List<String> init(){
		List<String> workdList = new ArrayList<String>() {
			private static final long serialVersionUID = 1L;
			{
				add("a");
	            add("b");
	            add("c");
	            add("d");
	            add("e");
	            add("f");
	            add("g");
			}
		} ;
		return workdList ;
	}
	//延迟执行特性，在聚合操作之前都可以添加相应元素
	private static void test001() {
		Stream<String> words = wordList.stream() ;
		wordList.add("END") ;
		long n = words.distinct().count() ;
		System.out.println(n);
	}
	
	//结果报空指针异常
	private static void test002() {
		Stream<String> words = wordList.stream() ;
		words.forEach(s->{
			System.out.println("s-> " + s);
			if(s.length() < 4) {
				System.out.println("select->" + s);
				wordList.remove(s);
				System.out.println(wordList);
			}
		});
	}
	
	//通过数组创建流
	private static void test003() {
		int [] arr = {1,2,34,5} ;
		IntStream intStream = Arrays.stream(arr) ;
		//1.2通过Stream.of
		Stream<Integer> stream = Stream.of(1,2,34,5,65) ;
	}
	
	//flapMap：拆解流
	private static void test004() {
		String[] arr1 = {"a", "b", "c", "d"};
	    String[] arr2 = {"e", "f", "c", "d"};
	    String[] arr3 = {"h", "j", "c", "d"};
	    Stream.of(arr1,arr2,arr3)
	    .flatMap(Arrays::stream)
	    .forEach(System.out::println);
	}
	
	//sorted:对流进行排序
	private static void test005() {
		String[] arr1 = {"abc","a","bc","abcd"};
		//按照字符长度排序
		Arrays.stream(arr1).sorted((x,y)->{
			if(x.length() > y.length())
				return 1 ;
			else if (x.length() < y.length())
				return -1 ;
			else
				return 0 ;
		}).forEach(System.out::println); ;
		Arrays.stream(arr1).
		sorted(Comparator.comparing(String::length))
		.forEach(System.out::println);
	}
	
	private static void test006() {
		String[] arr1 = {"abc","a","bc","abcd"};
		Arrays.stream(arr1).sorted(Comparator.comparing(String::length).reversed()).forEach(System.out::println);
	    Arrays.stream(arr1).sorted(Comparator.reverseOrder()).forEach(System.out::println);
	    Arrays.stream(arr1).sorted(Comparator.naturalOrder()).forEach(System.out::println);
	}
	
	private static void test007() {
		Stream.iterate(1,x->x+2).limit(10).forEach(System.out::println);
	}
	
	private static void test008() {
		Stream.iterate(1, x-> x +2).skip(1).limit(5).forEach(System.out::println);
	}
	
	private static void test009() {
		String[] arr1 = new String[]{"a","b","c","d"};
		String[] arr2 = new String[]{"d","e","f","g"};
		//String[] arr3 = new String[]{"i","j","k","l"};
		Stream<String> stream1 = Stream.of(arr1) ;
		Stream<String> stream2 = Stream.of(arr2) ;
		Stream.concat(stream1, stream2).distinct().forEach(System.out::println);
	}
	

}
