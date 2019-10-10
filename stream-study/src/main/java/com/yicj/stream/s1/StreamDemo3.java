package com.yicj.stream.s1;

import java.util.stream.Stream;

public class StreamDemo3 {


    public static void main(String[] args) {
        //test001() ;
        test002() ;

    }

    //，map和filter会对集合中的每个字符串调用五次，
    // 而forEach却只会调用一次，因为只有 "a2" 满足过滤条件。
    private static void test001(){
        Stream.of("d2","a2","b1","b3","c")
        .map(s->{
            System.out.println("map: " +s);
            return s.toUpperCase() ;
        }).filter(s -> {
            System.out.println("filter: " + s);
            return s.startsWith("A") ;//过滤出以A为前缀的元素
        }).forEach(s -> {
            System.out.println("forEach: " + s);
        });
    }
    //sorted 是一个有状态的操作，因为它需要在处理的过程中，
    // 保存状态以对集合中的元素进行排序。
    private static  void test002(){
        Stream.of("d2","a2","b1","b3","c")
        .sorted((s1,s2)->{
            System.out.printf("sort: %s ; %s\n",s1,s2);
            return s1.compareTo(s2) ; //排序
        }).filter(s->{
            System.out.println("filter: " + s);
            return s.startsWith("a") ;//过滤出以a为前缀的元素
        }).map(s->{
            System.out.println("map: " + s);
            return s.toUpperCase() ;//转大写
        }).forEach(s->{
            System.out.println("forEach: " + s) ;
        });
    }

    private static void test003(){
        Stream.of("d2", "a2", "b1", "b3", "c")
        .filter(s -> {
            System.out.println("filter: " + s);
            return s.startsWith("a");
        })
        .sorted((s1, s2) -> {
            System.out.printf("sort: %s; %s\n", s1, s2);
            return s1.compareTo(s2);
        })
        .map(s -> {
            System.out.println("map: " + s);
            return s.toUpperCase();
        })
        .forEach(s -> System.out.println("forEach: " + s));
    }

}
