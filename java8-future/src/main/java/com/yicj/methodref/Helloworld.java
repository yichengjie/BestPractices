package com.yicj.methodref;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class Helloworld {

    public static void main(String[] args) {

        List<String> strList = Arrays.asList("a","c","b") ;
        String [] strs = new String[]{"a","c","b"} ;
        print2(strList, strs) ;

    }


    //普通lambda打印
    //lambda代替匿名类
    private static void print1(List<String> strList,String [] strs){
        strList.stream().sorted((s1,s2) -> s1.compareToIgnoreCase(s2)).forEach(s-> System.out.println(s));

        Supplier<List<String>> supplier = () -> new ArrayList<>() ;

        Arrays.sort(strs,(s1,s2)->s1.compareToIgnoreCase(s2));
    }


    //方法引用打印
    private static void print2(List<String> strList, String [] strs){
        strList.stream().sorted(String::compareToIgnoreCase).forEach(System.out::println);

        Supplier<List<String>> supplier = ArrayList:: new ;

        Arrays.sort(strs, String::compareToIgnoreCase);
    }

}
