package com.yicj.stream.s1;

import java.util.List;

import com.yicj.stream.Person;


public class StreamDemo7 {
static List<Person> persons = Person.getDBPerson() ;
	
	public static void main(String[] args) {
		//test002();
		//test003();
		test004();
		System.out.println("-----------------------------");
		test005();
	}
	
	
	//筛选出年龄最大的那个人
	private static void test001() {
		persons.stream()
		.reduce((p1,p2)-> p1.age > p2.age?p1:p2)
		.ifPresent(System.out::println);
	}
	
	//含来自流中所有其他人的聚合名称和年龄
	private static void test002() {
		Person result = persons.stream()
		.reduce(new Person("", 0),(p1,p2)->{
			p1.age += p2.age ;
			p1.name += p2.name ;
			return p1 ;
		}) ;
		System.out.format("name=%s; age=%s", result.name,result.age);
	}
	
	private static void test003() {
		Integer ageSum = persons.stream()
		.reduce(0,(sum,p)->sum += p.age,(sum1,sum2) -> sum1+ sum2) ;
		System.out.println("age sum is : " + ageSum);
	}
	
	private static void test004() {
		Integer ageSum = persons.stream()
		.reduce(
			0,
			(sum,p)->{
				System.out.format("accumulator: sum=%s; person=%s\n",sum,p);
				return sum += p.age ;
			},
		    (sum1,sum2) ->{
		    	System.out.format("combiner : sum1=%s; sum2=%s\n",sum1,sum2);
		    	return sum1 + sum2 ;
		    });
	}
	
	
	private static void test005() {
		Integer ageSum = persons
	    .parallelStream()
	    .reduce(0,
	        (sum, p) -> {
	            System.out.format("accumulator: sum=%s; person=%s\n", sum, p);
	            return sum += p.age;
	        },
	        (sum1, sum2) -> {
	            System.out.format("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
	            return sum1 + sum2;
	        });
		System.out.println("ageSum : " + ageSum);
	}
	
}
