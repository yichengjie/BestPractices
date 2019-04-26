package com.yicj.demo.stream;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class StreamDemo8 {
	
	private static List<Person> persons = Person.getDBPerson() ;
	
	public static void main(String[] args) {
		//test001();
		//test002();
		//test003();
		test004();
	}
	
	private static void test001() {
		ForkJoinPool commonPool = ForkJoinPool.commonPool();
		System.out.println(commonPool.getParallelism());    // 3
	}
	
	private static void test002() {
		Arrays.asList("a1", "a2", "b1", "c2", "c1")
		.parallelStream()
		.filter(s -> {
			System.out.printf("filter: %s [%s]\n",s,Thread.currentThread().getName());
			return true ;
		})
		.map(s->{
			System.out.printf("map:%s [%s]\n",s,Thread.currentThread().getName());
			return s.toUpperCase() ;
		})
		.forEach(s->{
			System.out.printf("forEach: %s [%s]\n",s,Thread.currentThread().getName());
		});
	}
	
	private static void test003() {
		Arrays.asList("a1", "a2", "b1", "c2", "c1")
		.parallelStream().filter(s->{
			System.out.printf("filter:%s [%s]\n" ,s ,Thread.currentThread().getName() );
			return true ;
		})
		.map(s->{
			System.out.printf("map: %s [%s]\n",s , Thread.currentThread().getName());
			return s.toUpperCase() ;
		})
		.sorted((s1,s2) ->{
			System.out.printf("sort: %s <> %s [%s]\n",s1,s2, Thread.currentThread().getName());
			return s1.compareTo(s2) ;
		})
		.forEach(s -> System.out.printf("forEach: %s [%s]\n",s,Thread.currentThread().getName()));
		
	}
	
	
	private static void test004() {
		persons
	    .parallelStream()
	    .reduce(0,
	        (sum, p) -> {
	            System.out.format("accumulator: sum=%s; person=%s [%s]\n",
	                sum, p, Thread.currentThread().getName());
	            return sum += p.age;
	        },
	        (sum1, sum2) -> {
	            System.out.format("combiner: sum1=%s; sum2=%s [%s]\n",
	                sum1, sum2, Thread.currentThread().getName());
	            return sum1 + sum2;
	        });

	}
	
	
}
