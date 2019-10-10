package com.yicj.demo.stream.stage1;

import java.util.stream.Stream;

public class StreamDemo2 {


    public static void main(String[] args) {
        //test001();
        //test002();
        test003();
    }

    //当且存在终端操作时，中间操作才会被执行，
    //这段代码将无任何输出
    private static void test001() {
        Stream.of("d2","a2","b1","b3","c")
        .filter(s->{
            System.out.println("filter: " + s);
            return true ;
        });
    }

    //filter: d2
    //foreEach: d2
    //filter: a2
    //foreEach: a2
    //filter: b1
    //foreEach: b1
    //filter: b3
    //foreEach: b3
    //filter: c
    //foreEach: c
    private static void test002(){
        Stream.of("d2","a2","b1","b3","c")
        .filter(s->{
            System.out.println("filter: " + s);
            return true ;
        })
        .forEach(s -> System.out.println("foreEach: " + s));
    }

    //map: d2
    //anyMatch: D2
    //map: a2
    //anyMatch: A2
    public static void test003(){
        Stream.of("d2","a2","b1","b3","c")
        .map(s->{
            System.out.println("map: " + s);
            return s.toUpperCase() ;//转大写
        }).anyMatch(s->{
            System.out.println("anyMatch: " + s);
            return s.startsWith("A") ; //过滤出以A为前缀的元素
        }) ;
    }

}
